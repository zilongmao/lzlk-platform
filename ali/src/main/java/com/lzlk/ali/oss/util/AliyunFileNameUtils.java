package com.lzlk.ali.oss.util;

import com.lzlk.ali.oss.enums.AliyunOssDirEnums;
import com.lzlk.base.utils.file.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;

/**
 * @Classname 邻座旅客
 * @Description TODO
 * @Date 2019/3/15 15:30
 * @Created by 湖南达联
 */
public class AliyunFileNameUtils {


    public static String builderUserFaceImgAliyunOssUrl(String ossEndpointUrl, AliyunOssDirEnums aliyunOssDirEnums,
                                                        Long userId, String suffix) {
        StringBuilder sb = new StringBuilder();
        sb.append(ossEndpointUrl).append("/").append(aliyunOssDirEnums.getDirName()).
                append(userId).append(".").append(suffix);
        return sb.toString();
    }




    public static InputStream converter(String imgUrl) throws IOException {

        ByteArrayInputStream arrayInputStream = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        String Imgtype = FileUtils.getExtensionName(imgUrl);
        try {
            URL url = new URL(imgUrl);
            BufferedImage bufferedImage  = ImageIO.read(url);
            //高度
            int height = 250;
            //宽度
            int width = 250;

            //等比压缩计算
            BigDecimal resizeTimes = BigDecimal.valueOf(0.4f) ;  /*这个参数是要转化成的倍数,如果是1就是转化成1倍*/
            /* 调整后的图片的宽度和高度 */
            int toWidth = BigDecimal.valueOf(bufferedImage.getWidth()).multiply(resizeTimes).intValue();
            int toHeight = BigDecimal.valueOf(bufferedImage.getHeight()).multiply( resizeTimes).intValue() ;

            //获取图像
            Image image;
            boolean b = (bufferedImage.getHeight() < height && bufferedImage.getWidth() < width);

            if (b) {
                ImageIO.write(bufferedImage,Imgtype,bos);
                return new ByteArrayInputStream(bos.toByteArray());
            }
            image = bufferedImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
            // 计算比例
            if ((bufferedImage.getHeight() > height) || (bufferedImage.getWidth() > width)) {
                //sx - 坐标沿X轴方向缩放的因子
                double sx = div(toWidth, bufferedImage.getWidth());
                //sy - 坐标沿着Y轴方向缩放的因子
                double sy = div(toHeight, bufferedImage.getHeight());
                //设置指定尺寸
                AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(sx, sy), null);
                //重新构建图像
                image = op.filter(bufferedImage, null);
            }
            ImageIO.write((BufferedImage) image, Imgtype, bos);
            arrayInputStream = new ByteArrayInputStream(bos.toByteArray());
        }finally {
            try {
                if (arrayInputStream != null) {
                    arrayInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return arrayInputStream;
    }

    /**
     *
     * @param imgUrl
     * @return
     * @throws Exception
     */
    BufferedImage img(String imgUrl)throws Exception{
        URL url = new URL(imgUrl);
        BufferedImage bufferedImage = ImageIO.read(url);
        //构建指定长宽
        int width =250;
        int height = 250;
        int scaleType;
        //如长和宽与指定的一致,返回原图
        int imageWidth = bufferedImage.getWidth();
        int imageHeight = bufferedImage.getHeight();
        if (imageWidth == width && imageHeight == height) {
            return bufferedImage;
            //如果长或者宽小于要求进行放大平滑处理
        }else if(imageWidth < width || imageHeight < height){
            scaleType = Image.SCALE_SMOOTH;
        }else {
            scaleType = Image.SCALE_DEFAULT;
        }
        //sx - 坐标沿X轴方向缩放的因子
        double sx = div(width, imageWidth);
        //sy - 坐标沿着Y轴方向缩放的因子
        double sy = div(height, imageHeight);
        String imgType = FileUtils.getExtensionName(imgUrl);
        if ("png".equals(imgType)){
            final AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(sx, sy), null);
            return op.filter(bufferedImage, null);
        }else {
            return (BufferedImage) bufferedImage.getScaledInstance(width, height, scaleType);
        }
    }


    //计算div
    private static double div(int val,int imgValue){
        //除数为0
        if (imgValue == 0) {
            return BigDecimal.ZERO.doubleValue();
        }
        //利用精确度为10，保留小数模式为4位进行计算
       return new BigDecimal(val).divide(new BigDecimal(imgValue),10, RoundingMode.HALF_UP).doubleValue();
    }

  public static void main(String[] args) throws Exception {
    //
      URL url = new URL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560935916494&di=25e0a62f12fa7fcca2f733196616e915&imgtype=0&src=http%3A%2F%2Ff.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F8d5494eef01f3a29f863534d9725bc315d607c8e.jpg");
              BufferedImage bufferedImage=new BufferedImage(250,250,BufferedImage.TYPE_INT_RGB);
      Graphics graphics=bufferedImage.getGraphics();
     Image image= ImageIO.read(url);
      BufferedImage image1 = ImageIO.read(url);
      ImageIO.write(image1,"jpg",new File("F:\\3.jpg"));
      graphics.drawImage(image,0,0,250,250,null);
      ImageIO.write(bufferedImage,"jpg",new File("F:\\2.jpg"));
  /*    imageIcon.getImage()
    System.out.println(bufferedImage);*/
  }
}
