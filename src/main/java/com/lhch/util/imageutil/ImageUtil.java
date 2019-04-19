package com.lhch.util.imageutil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Description: 图片工具类
 * @Date: 12:01 2019/4/13
 */
@Slf4j
public class ImageUtil {

    /**
     * 根据图片纵横比对图片进行分类
     *
     * @param outPath
     *         必须有路径的结束符
     */
    public static void classificationByHV(File pathFile, String outPath) throws IOException {
        if (pathFile.isFile()) {
            throw new RuntimeException("请选择一个路径!");
        }
        File[] listFiles = pathFile.listFiles();

        //"C:\\python_project\\
        //创建输出路径
        String hOutPath = outPath + "h\\";
        String wOutPath = outPath + "w\\";
        createNewFile(hOutPath);
        createNewFile(wOutPath);
        for (File f : listFiles) {
            if (f.isFile()) {
                BufferedImage image = ImageIO.read(f);
                if (image == null) {
                    continue;
                }
                double height = (image.getHeight());
                double width = image.getWidth();
                log.info("待处理的图片名称:{}", f.getName());
                if ((height / width) > 1) {
                    File destFile = new File(hOutPath + System.currentTimeMillis() + f.getName());
                    FileUtils.copyFile(f, destFile);
                } else if ((height / width) <= 1) {
                    File destFile = new File(wOutPath + System.currentTimeMillis() + f.getName());
                    FileUtils.copyFile(f, destFile);
                }
            } else {
                classificationByHV(f, outPath);
            }
        }
        System.out.println("done");
    }

    /**
     * @param file
     * @param degree
     * @return void
     * @Description: 对图片进行旋转, 按照指定的角度
     * @Date: 12:21 2019/4/13
     */
    public static void spin(File file, int degree, String outPath) throws Exception {
        if (file.isFile()) {
            throw new RuntimeException("请选择一个路径!");
        }
        File[] listFiles = file.listFiles();
        for (File f : listFiles) {
            if (f.isFile()) {
                BufferedImage image = ImageIO.read(f);
                if (image == null) {
                    continue;
                }
                spinImage(degree, f, outPath);

            } else {
                spin(f, degree, outPath);
            }
        }
        System.out.println("done");
    }

    /**
     * @param degree
     * @param file
     * @return void
     * @Description: 执行旋转
     * @Date: 12:25 2019/4/13
     */
    private static void spinImage(int degree, File file, String outPath) throws Exception {
        int swidth = 0;
        int sheight = 0;
        int x;
        int y;

        if (!file.isFile()) {
            throw new Exception("ImageDeal>>>" + file + " 不是一个文件!");
        }
        BufferedImage bi = ImageIO.read(file);

        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;
        double theta = Math.toRadians(degree);

        if (degree == 180 || degree == 0 || degree == 360) {
            swidth = bi.getWidth();
            sheight = bi.getHeight();
        } else if (degree == 90 || degree == 270) {
            sheight = bi.getWidth();
            swidth = bi.getHeight();
        } else {
            swidth = (int) (Math.sqrt(bi.getWidth() * bi.getWidth() + bi.getHeight() * bi.getHeight()));
            sheight = (int) (Math.sqrt(bi.getWidth() * bi.getWidth() + bi.getHeight() * bi.getHeight()));
        }

        x = (swidth / 2) - (bi.getWidth() / 2);
        y = (sheight / 2) - (bi.getHeight() / 2);

        BufferedImage spinImage = new BufferedImage(swidth, sheight, bi.getType());
        Graphics2D gs = (Graphics2D) spinImage.getGraphics();
        gs.setColor(Color.white);
        // 以给定颜色绘制旋转后图片的背景
        gs.fillRect(0, 0, swidth, sheight);

        AffineTransform at = new AffineTransform();
        // 旋转图象
        at.rotate(theta, swidth / 2, sheight / 2);
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        spinImage = op.filter(bi, spinImage);
        File sf = new File(outPath, file.getName());
        String name = file.getName();
        ImageIO.write(spinImage, name.substring(name.lastIndexOf('.') + 1), sf);

    }

    /**
     * @param path
     * @return void
     * @Description: 创建路径
     * @Date: 12:20 2019/4/13
     */
    private static void createNewFile(String path) {
        File wOutPathFile = new File(path);
        if (!wOutPathFile.exists()) {
            try {
                wOutPathFile.createNewFile();
            } catch (IOException e) {
                log.error("创建输出路径异常:{}", e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
