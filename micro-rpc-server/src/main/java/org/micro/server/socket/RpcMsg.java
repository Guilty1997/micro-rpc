package org.micro.server.socket;

/**
 * @author：HeHongyi
 * @date: 2023/9/12
 * @description: 自定义协议对象
 */
public class RpcMsg {
    // 区分是请求还是响应  0：请求  1：响应
    private short msgType;

    private int sequenceId;

    public short getMsgType() {
        return msgType;
    }

    public void setMsgType(short msgType) {
        this.msgType = msgType;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }
}
