package net.kuper.tz.storage.service.impl;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.filters.ImageFilter;
import net.kuper.tz.core.properties.StorageProperties;
import net.kuper.tz.core.utils.DateUtils;
import net.kuper.tz.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.UUID;

@Component
public class OssTool {


    @Autowired
    private StorageProperties storageProperties;

//    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    private SimpleDateFormat localDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    /**
     * 获取文件后缀
     *
     * @param file
     * @return
     */
    public String getSuffix(MultipartFile file) {
        String suffix = "non";
        if (file.getOriginalFilename().lastIndexOf(".") > 0) {
            suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        }
        suffix = suffix.toUpperCase();
        return suffix;
    }


    /**
     * 生成存储Key
     *
     * @param suffix 后缀
     * @return 返回保存的key
     */
    public String generationKey(String prefix, String suffix) {
        //生成uuid
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //文件路径
        String path = DateUtils.getNowString(localDateFormat) + "-" + uuid;
        if (!StringUtils.isEmpty(prefix)) {
            path = prefix + "/" + path;
        }
        return path + "." + suffix;
    }


    /**
     * 生成缓存地址
     *
     * @param suffix
     * @return
     */
    public String generationCachePath(String suffix) {
        String path;
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String name = DateUtils.getNowString(localDateFormat) + "-" + uuid + "." + suffix;
        File file = new File(storageProperties.getTmpFilePath());
        if (!file.exists()) {
            file.mkdirs();
        }
        if (!storageProperties.getTmpFilePath().endsWith("/")) {
            path = storageProperties.getTmpFilePath() + File.separator + name;
        } else {
            path = storageProperties.getTmpFilePath() + name;
        }
        return path;
    }


    /**
     * 图片压缩
     *
     * @param sourceFile 源文件
     * @param outputFile 输出文件
     * @param quality    压缩质量0-1
     * @param scale      尺寸缩放 0-1
     * @param format     输出格式
     * @throws IOException
     */
    public void compressImage(String sourceFile, String outputFile, float quality, float scale, String format) throws IOException {
        Thumbnails.Builder builder = Thumbnails.of(sourceFile)
                .scale(scale)
                .outputFormat(format)
                .outputQuality(quality);
        if ("PNG".equalsIgnoreCase(format)) {
            builder.addFilter(new ThumbnailsImgFilter())
                    .imageType(BufferedImage.TYPE_INT_ARGB);
        }
        builder.toFile(outputFile);
    }


    private class ThumbnailsImgFilter implements ImageFilter {
        @Override
        public BufferedImage apply(BufferedImage img) {
            int w = img.getWidth();
            int h = img.getHeight();
            BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = newImage.createGraphics();
            graphic.setColor(Color.white);//背景设置为白色
            graphic.fillRect(0, 0, w, h);
            graphic.drawRenderedImage(img, null);
            graphic.dispose();
            return newImage;
        }
    }
}
