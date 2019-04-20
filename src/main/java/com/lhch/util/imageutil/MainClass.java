package com.lhch.util.imageutil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainClass {
    public static final String FULL_PATH = "D:\\360极速浏览器下载\\e-hentai helper\\w\\";

    public static void main(String[] args) {

        fun1(FULL_PATH);
        //fun3();
        //fun4();
        //fun5();
    }

    /**
     * 测试区分黑白照片的方法
     */
    private static void fun5() {
        try {
            BufferedImage image = ImageIO.read(new File("D:\\360极速浏览器下载\\e-hentai helper\\h\\1555683470264Image00001.jpg"));
            BufferedImage image1 = ImageIO.read(new File("D:\\360极速浏览器下载\\e-hentai helper\\h\\1555683470286Image00002.jpg"));
            System.out.println("彩色照片：" + ImageUtil.execote(image));
            System.out.println("彩色照片：" + ImageUtil.execote(image1));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 测试File对象的创建
     */
    private static void fun4() {
        File file = new File("D:\\");
        File[] files = file.listFiles();
        File file1 = new File("D:\\360极速浏览器下载\\e-hentai helper");
        File[] files1 = file.listFiles();
        File file2 = new File("D:/360极速浏览器下载/e-hentai helper/");
        File[] files2 = file.listFiles();
        File file3 = new File("D:/360极速浏览器下载/e-hentai helper");
        File[] files3 = file.listFiles();
        /*

22:22:02.391 [main] INFO com.lhch.util.imageutil.ImageUtil - 待处理的图片名称:45744194_p0_Yang.jpg
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 4096
	at com.sun.imageio.plugins.gif.GIFImageReader.read(GIFImageReader.java:984)
	at javax.imageio.ImageIO.read(ImageIO.java:1448)
	at javax.imageio.ImageIO.read(ImageIO.java:1308)
	at com.lhch.util.imageutil.ImageUtil.classificationByHV(ImageUtil.java:81)
	at com.lhch.util.imageutil.ImageUtil.classificationByHV(ImageUtil.java:96)
	at com.lhch.util.imageutil.MainClass.fun1(MainClass.java:51)
	at com.lhch.util.imageutil.MainClass.main(MainClass.java:11)
         */
    }

    /**
     * @Description: 按照宽高比对图片进行分类
     * @Date: 11:51 2019/4/13
     */
    public static void fun1(String fullPath) {
        /**
         * 肯定是我的问题
         * 无法从给出的路径,获取路径下的所有的文件=>File对象创建有问题
         * File对象和用ListFiles获取的对象全部为空串
         */
        try {
            File file = new File(fullPath);
            if (file.exists())
                ImageUtil.classificationByHV(file, fullPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param fullPth
     * @Description: 将指定路径下的图片全部旋转
     * @Date: 11:50 2019/4/13
     */
    public static void fun2(String fullPth) {
        //"L:/dxy/44heigt"
        File file = new File(fullPth);
    }
}
