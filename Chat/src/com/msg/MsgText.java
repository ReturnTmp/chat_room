package com.msg;

public class MsgText extends MsgHead{
    //word固定1024字节
    public String word;
//    public MsgText() {
//        super(totalLen);
//    }

    public MsgText(int totalLen, byte type, int destId, int srcId, String word) {
        super(totalLen, type, destId, srcId);
        this.word = word;
    }
}
