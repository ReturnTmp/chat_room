package com.msg;

import java.io.*;

public class MsgFile extends MsgHead{
    //固定1024个字节储存filePath
    public String filePath;
    public File file;
    public MsgFile(int totalLen) {
        super(totalLen);
    }

    public MsgFile(int totalLen, byte type, int destId, int srcId, String filePath, File file) {
        super(totalLen, type, destId, srcId);
        this.filePath = filePath;
        this.file = file;
    }

    public static File byte2File(byte[]data, String filePath){
        File file=null;
        try {
            file = new File(filePath);
            FileOutputStream fous=new FileOutputStream(file);
            fous.write(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    public static byte[]File2byte(File file){
        ByteArrayOutputStream bos=null;
        try {
            FileInputStream fis=new FileInputStream(file);
            bos=new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }
}
