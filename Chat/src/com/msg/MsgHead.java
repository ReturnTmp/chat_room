package com.msg;

import java.awt.image.BufferedImage;
import java.io.*;

public class MsgHead {
    public int totalLen;
    public byte type;
    public int destId;
    public int srcId;

    public MsgHead(int totalLen) {
        this.totalLen = totalLen;
    }

    public MsgHead(int totalLen, byte type, int destId, int srcId) {
        this.totalLen = totalLen;
        this.type = type;
        this.destId = destId;
        this.srcId = srcId;
    }

    /**
     * 解包过程，即将一个data字节数组转换一个MsgHead
     * @param data:是不含totalLen的字节数组
     * @return
     */
    public static MsgHead parseMsg(byte[]data) {
        try {
            int totalLen = data.length + 4;
            MsgHead msgHead = new MsgHead(totalLen);
            ByteArrayInputStream bins=new ByteArrayInputStream(data);
            DataInputStream dins=new DataInputStream(bins);
            byte type = dins.readByte();
            int destId=dins.readInt();
            int srcId=dins.readInt();
            if(type==MsgType.TEXT){
                byte[]info=new byte[1024];
                dins.readFully(info);
                String word=MsgTools.byte2String(info);
                MsgText msgText = new MsgText(totalLen, type, destId, srcId, word);
                return msgText;
            } else if(type==MsgType.FILE){
                byte[]info=new byte[1024];
                dins.readFully(info);
                String filePath=MsgTools.byte2String(info);
                info=new byte[totalLen-4-1-4-4-1024];
                dins.readFully(info);
                File file=MsgFile.byte2File(info,filePath);
                MsgFile msgFile = new MsgFile(totalLen, type, destId, srcId, filePath, file);
                return msgFile;
            } else if(type==MsgType.EMOJI){
                byte[]info=new byte[totalLen-4-1-4-4];
                dins.readFully(info);
                BufferedImage bufferedImage = MsgEmoji.byte2image(info);
                MsgEmoji msgEmoji = new MsgEmoji(totalLen, type, destId, srcId, bufferedImage);
                return msgEmoji;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *将MsgHead打包成byte数组，便于传输
     * @param msgHead:
     * @return：含totalLen的byte数组
     */
    public static byte[]packMsg(MsgHead msgHead){
        ByteArrayOutputStream bous=null;
        try {
            bous=new ByteArrayOutputStream();
            DataOutputStream dous=new DataOutputStream(bous);
            writeMsgHead(msgHead, dous);
            int type=msgHead.type;
            if(type==MsgType.TEXT){
                MsgText msgText= (MsgText) msgHead;
                byte[]data=MsgTools.String2byte(msgText.word,1024);
                dous.write(data);
            } else if(type==MsgType.FILE){
                MsgFile msgFile = (MsgFile) msgHead;
                byte[]data=MsgTools.String2byte(msgFile.filePath,1024);
                dous.write(data);
                data=msgFile.File2byte(msgFile.file);
                dous.write(data);
            } else if(type==MsgType.EMOJI){
                MsgEmoji msgEmoji= (MsgEmoji) msgHead;
                BufferedImage bufferedImage = msgEmoji.bufferedImage;
                byte[]data= msgEmoji.image2byte(bufferedImage);
                dous.write(data);
            }
            dous.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bous.toByteArray();
    }

    /**
     * 写入消息头，所有消息都需要有的的步骤
     * @param msgHead
     * @param dous
     */
    public static void writeMsgHead(MsgHead msgHead, DataOutputStream dous){
        try {
            dous.writeInt(msgHead.totalLen);
            dous.writeByte(msgHead.type);
            dous.writeInt(msgHead.destId);
            dous.writeInt(msgHead.srcId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
