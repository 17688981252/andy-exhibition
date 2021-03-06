package com.zel.business.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import static com.zel.common.config.datasource.DynamicDataSourceContextHolder.log;

public class ImageUtil {

//    private Logger log = LoggerFactory.getLogger(getClass());

    private static String DEFAULT_PREVFIX = "thumb_";
    private static Boolean DEFAULT_FORCE = false;//建议该值为false

    /**
     * <p>Title: thumbnailImage</p>
     * <p>Description: 根据图片路径生成缩略图 </p>
     * @param imagePath    原图片路径
     * @param w            缩略图宽
     * @param h            缩略图高
     * @param prevfix    生成缩略图的前缀
     * @param force        是否强制按照宽高生成缩略图(如果为false，则生成最佳比例缩略图)
     */
    public static String thumbnailImage(String imagePath, int w, int h, String prevfix, boolean force){
//        File file = null;
        File imgFile = new File(imagePath);
        if(imgFile.exists()){
            try {
                // ImageIO 支持的图片类型 : [BMP, bmp, jpg, JPG, wbmp, jpeg, png, PNG, JPEG, WBMP, GIF, gif]
                String types = Arrays.toString(ImageIO.getReaderFormatNames());
                String suffix = null;
                // 获取图片后缀
                if(imgFile.getName().indexOf(".") > -1) {
                    suffix = imgFile.getName().substring(imgFile.getName().lastIndexOf(".") + 1);
                }// 类型和图片后缀全部小写，然后判断后缀是否合法
                if(suffix == null || types.toLowerCase().indexOf(suffix.toLowerCase()) < 0){
                    log.error("Sorry, the image suffix is illegal. the standard image suffix is {}." + types);
//                    return file;
                }
                log.debug("target image's size, width:{}, height:{}.",w,h);
                Image img = ImageIO.read(imgFile);
                if(!force){
                    // 根据原图与要求的缩略图比例，找到最合适的缩略图比例
                    int width = img.getWidth(null);
                    int height = img.getHeight(null);
                    if((width*1.0)/w < (height*1.0)/h){
                        if(width > w){
                            h = Integer.parseInt(new java.text.DecimalFormat("0").format(height * w/(width*1.0)));
                            log.debug("change image's height, width:{}, height:{}.",w,h);
                        }
                    } else {
                        if(height > h){
                            w = Integer.parseInt(new java.text.DecimalFormat("0").format(width * h/(height*1.0)));
                            log.debug("change image's width, width:{}, height:{}.",w,h);
                        }
                    }
                }
                BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(img, 0, 0, w, h, Color.LIGHT_GRAY, null);
                g.dispose();
                String p = imgFile.getPath();
                // 将图片保存在原目录并加上前缀

                File file = new File(p.substring(0, p.lastIndexOf(File.separator)) + File.separator + prevfix + imgFile.getName());
                ImageIO.write(bi, suffix, file);
                  log.debug("缩略图在原路径下生成成功");
                String path = file.getPath();
                String pre = "F:\\zel_exhibition\\uploadPath";
                String path1 = path.replace(pre,"\\profile");
                String path2 = path1.replace("\\", "/");
                return  path2 ;
            } catch (Exception e) {
                log.error("generate thumbnail image failed.",e);
                return  null;
            }
        }else{
            log.warn("the image is not exist.");
            return  null;
        }
    }

   /* public static void main(String[] args) {
        new ImageUtil().thumbnailImage("F:/zel_exhibition/uploadPath/prospectUrl/2021/02/07/584e7efc-ecfb-47ee-b0f1-ab4e04bea9ba.jpeg", 100, 150,DEFAULT_PREVFIX,DEFAULT_FORCE);
    }*/
}
