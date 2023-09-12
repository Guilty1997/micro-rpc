# micro-rpc

一个轻量级rpc框架

## RPC简介

RPC（Remote Procedure Call）远程过程调用，是一种进程间通信方式。
它允许一个进程调用另一个进程中的过程，而像似调用本地过程一样。

RPC 的整体系统架构图如下：

![img.png](imags/系统架构.png)

图中服务端启动时将自己的服务节点信息注册到注册中心， 客户端调用远
程方法时会订阅注册中心中的可用服务节点信息， 拿到可用服务节点之后
远程调用方法，当注册中心中的可用服务节点发生变化时会通知客户端，避
免客户端继续调用已经失效的节点；客户端远程调用示意图：
![img.png](imags/远程调用.png)

1.将目标服务、目标方法、调用目标方法的参数等必要信息序列化
2.序列化之后的数据包进一步压缩，压缩后的数据包通过网络通信传输到目标服务节点
3.服务节点将接受到的数据包进行解压
4.解压后的数据包反序列化成目标服务、目标方法、目标方法的调用参数
5.通过服务端代理调用目标方法获取结果，结果同样需要序列化、压缩然后回传给客户端

## RPC实现细节

### 服务注册与发现

网上有很多的注册中心，比如zookeeper、consul、eureka等，此框架学习中采用Zookeeper作为注册中心，
ZooKeeper 将数据保存在内存中，性能很高。 在读多写少的场景中尤其适用，因为写操作会导致所有的服务器
间同步状态。服务注册与发现是典型的读多写少的协调服务场景。 Zookeeper 是一个典型的CP系统，在服务选
举或者集群半数机器宕机时是不可用状态，相对于服务发现中主流的AP系统来说，可用性稍低，但是用于理解RPC
的实现，也是绰绰有余。为了方便操作我们采用zkclient来注册服务

#### 服务注册

zkclient是zookeeper的java客户端，它提供了一个高级别的抽象来简化zookeeper的操作。
zkclient的API设计非常简单，它提供了ZkClient、ZkConnection、ZkClientConfig、ZkNodeProps等类，
ZkClient是客户端的核心类，ZkConnection是连接zookeeper的底层类，ZkClientConfig是配置类，
ZkNodeProps是zookeeper节点属性类。

ZkClientConfig是配置类，它提供了一个构造器，可以指定zookeeper的连接地址和超时时间。

```xml

<dependency>
    <groupId>com.101tec</groupId>
    <artifactId>zkclient</artifactId>
    <version>0.10</version>
</dependency>
```

注册流程分为
1.注册服务提供者
2.本地服务缓存
3.注册服务调用者信息
4.监听节点变化，刷新服务提供者信息

## 自定义协议

注意：如果不知道自定义协议标准可以通过encode反推原始数据

- 魔数：用来在第一时间判定是否是无效数据包
    - 例如：Java Class文件都是以0x CAFEBABE开头的。Java这么做的原因就是为了快速判断一个文件是不是有可能为class文件，以及这个class文件有没有受损。
- 版本号：可以支持协议的升级
- 序列化算法：消息正文到底采用哪种序列化反序列化方式
- 指令类型：针对业务类型指定
- 请求序号：为了双工通信，提供异步能力，序号用于回调
- 正文长度：没有长度会导致浏览器持续加载
- 消息正文：具体消息内容

## 设计思路

1. 基于netty实现rpc框架
2. 基于注解实现rpc调用
3. 基于注解实现rpc服务注册
4. 基于注解实现rpc服务发现
5. 基于注解实现rpc服务监控

## 快速开始

### 引入依赖

```xml

<dependency>
    <groupId>com.github.houbb</groupId>
    <artifactId>micro-rpc</artifactId>
    <version>0.0.1</version>
</dependency>
```

### 定义接口

```java

@RpcService
public interface HelloService {

    /**
     * 测试方法
     * @param name 名称
     * @return 结果
     */
    String hello(String name);

}
```

### 实现接口

```java

@RpcService
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "hello " + name;
    }

}
```

### 启动服务

```java
public class RpcServer {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        server.start(8080);
    }

}
```

### 调用服务

```java
public class RpcClient {

    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        HelloService helloService = client.getProxy(HelloService.class);
        String result = helloService.hello("world");
        System.out.println(result);
    }

}
```

## 注解说明

### @RpcService

用于接口上，表示该接口为 rpc 服务。

### @RpcReference

用于接口上，表示该接口为 rpc 引用。

### @RpcServer

用于接口上，表示该接口为 rpc 服务端。

### @RpcClient

用于接口上，表示该接口为 rpc 客户端。

