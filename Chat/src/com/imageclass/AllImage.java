package com.imageclass;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AllImage {
//    public static Image[] privateImage=getImage("src/com/image/privateImage.jpg",70,70);
    public static Image[] privateImage=getImageArr("src/com/image/privateimage",70,70);
    public static Image[] emoji1Image=getImageArr("src/com/image/emoji1",40,40);
    public static Image[] emoji2Image=getImageArr("src/com/image/emoji2",40,40);
    public static Image down_arrow=getSingleImage("src/com/image/down_arrow.png",20,20);
    public static Image left_arrow=getSingleImage("src/com/image/left_arrow.png",20,20);
//    public static Image down_background=new ImageIcon("src/com/image/down_background.jpg").getImage();
    public static Image up_background=new ImageIcon("src/com/image/up_background.jpg").getImage();
    public static Image main_background=new ImageIcon("src/com/image/main_background.jpg").getImage();

    public static Image[] getImageArr(String fileListName,int width,int height){
        File[]fileArr=new File(fileListName).listFiles();
        Image[] resImage=new Image[fileArr.length];
        for (int i = 0; i < fileArr.length; i++) {
            Image image= null;
            try {
                image = new ImageIcon(fileArr[i].getCanonicalPath()).getImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //此段代码是为了将bufferedImage设置为透明
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D=bufferedImage.createGraphics();
            bufferedImage = graphics2D.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            graphics2D.dispose();
            bufferedImage.getGraphics().drawImage(image,0,0,width, height,null);
            resImage[i]=bufferedImage;
        }
        return resImage;
    }
    public static Image getSingleImage(String fileName,int width,int height){
        Image image = new ImageIcon(fileName).getImage();
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                bufferedImage.setRGB(j,i,Color.white.getRGB());
            }
        }
        bufferedImage.getGraphics().drawImage(image,0,0,width, height,null);
        return bufferedImage;
       
    }

}
