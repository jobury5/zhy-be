package com.zhy.util;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.codec.Base64;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: jobury
 * @Date: 2024/9/13 17:20
 */

public class DocUtil {



    public static int countPdfFromPdfUrl(String url) {
        try {
            byte[] bytes = HttpUtil.getBytes(url);
            if(null != bytes) {
                PDDocument document = Loader.loadPDF(bytes);
                return document.getNumberOfPages();
            }
        } catch (IOException e) {
            return -1; // 错误时返回-1
        }
        return -1;
    }

    public static InputStream convertToJpgFromPdfUrl(String url, int page) {
        try {
            // 从URL下载PDF文件
            byte[] bytes = HttpUtil.getBytes(url);
            // 使用PDFBox解析PDF文件
            PDDocument document = Loader.loadPDF(bytes);
            // 创建PDF渲染器
            PDFRenderer renderer = new PDFRenderer(document);
            // 获取第page页PDF页面的图像
            BufferedImage image = renderer.renderImage(page); // 页码从0开始
            // 将图像保存为JPG格式的字节数组
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            byte[] imageData = baos.toByteArray();
            baos.close();
            // 创建包含图像数据的InputStream
            return new ByteArrayInputStream(imageData);
        } catch (Exception e) {
            return null; // 错误时返回-1
        }
    }

    public static String convertImageToBase64(InputStream inputStream) {
        try {
            // 从输入流中读取图片数据到字节数组
            byte[] imageData = IoUtil.readBytes(inputStream);

            // 将字节数组转换为Base64编码的字符串
            return Base64.encode(imageData);
        } catch (Exception e) {
            return null;
        } finally {
            // 关闭输入流
            IoUtil.close(inputStream);
        }
    }

}
