package com.zhy.util;

import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author: jobury
 * @Date: 2024/9/13 17:02
 */

public class HttpUrlUtil {

    public static String getFileExtensionFromURL(String urlString) {
        try {
            // 解析URL
            URL url = new URL(urlString);
            // 获取路径部分
            String path = url.getPath();
            // 解码路径，以防止编码问题导致解析错误
            String decodedPath = URLDecoder.decode(path, StandardCharsets.UTF_8);
            // 分割路径和文件名
            String[] pathComponents = decodedPath.split("/");
            String fileName = pathComponents[pathComponents.length - 1];
            // 移除查询参数
            String cleanFileName = fileName.split("\\?")[0];
            // 获取文件的后缀名
            int dotIndex = cleanFileName.lastIndexOf('.');
            if (dotIndex == -1) {
                return ""; // 没有后缀名
            }
            return cleanFileName.substring(dotIndex + 1);
        } catch (Exception e) {
            return ""; // 错误时返回空字符串
        }
    }

}
