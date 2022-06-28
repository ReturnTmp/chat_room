package com.msg;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MsgEmoji extends MsgHead{
    public BufferedImage bufferedImage;

    public MsgEmoji(int totalLen) {
        super(totalLen);
    }

    public MsgEmoji(int totalLen, byte type, int destId, int srcId, BufferedImage bufferedImage) {
        super(totalLen, type, destId, srcId);
        this.bufferedImage = bufferedImage;
    }

    public static byte[]image2byte(BufferedImage bufferedImage){
        ByteArrayOutputStream bous=new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage,"png",bous);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bous.toByteArray();
    }
    public static BufferedImage byte2image(byte[]datas){
        ByteArrayInputStream bins = new ByteArrayInputStream(datas);
        BufferedImage img=null;
        try {
            img = ImageIO.read(bins);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
    public static BufferedImage imageToBufferedImage(Image image){
        int width=image.getHeight(null);
        int height=image.getWidth(null);
        BufferedImage bufferedImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics buffG = bufferedImage.getGraphics();
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                bufferedImage.setRGB(j,i,new Color(238,238,238).getRGB());
            }
        }
        //以下为设置bufferedImagew为透明色
//        Graphics2D graphics2D=bufferedImage.createGraphics();
//        bufferedImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
//        graphics2D.dispose();
        //
        buffG.drawImage(image,0,0,bufferedImage.getWidth(),
                bufferedImage.getHeight(), null);
        return bufferedImage;
    }
}
