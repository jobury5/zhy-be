import com.zhy.util.HttpUtil;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Author: jobury
 * @Date: 2024/9/14 15:04
 */

public class TestPdfConvert {

    public static void main(String[] args) {
        // 示例PDF文件路径
        String pdfFilePath = "/Users/qiaobusi/Downloads/20240911AaKbqGdR.pdf";

        // 指定要转换的PDF页面编号（从0开始）
        int pageNumber = 0; // 第一页

        // 新的JPG文件的路径
        String jpgFilePath = "identity_image.jpg";

        // 调用方法转换PDF页面到JPG
        convertPdfPageToJpg(pdfFilePath, pageNumber, jpgFilePath);
    }

    /**
     * 将PDF文件中的指定页面转换为JPG格式的图像，并写入新的文件中。
     *
     * @param pdfFilePath PDF文件的路径
     * @param pageNumber 页码（从0开始）
     * @param jpgFilePath 输出的JPG文件路径
     */
    public static void convertPdfPageToJpg(String pdfFilePath, int pageNumber, String jpgFilePath) {
        try  {
            byte[] pdfContent = HttpUtil.getBytes("https://aisailings-uat.oss-cn-shanghai.aliyuncs.com/profile/identity/2024/09/20240911AaKbqGdR.pdf?OSSAccessKeyId=LTAI5tLZ2qvxYc35ZVy563Uq&Expires=1726301319&Signature=S7byAB%2BWxBfrEOJ8rEwu6YpTH5o%3D");
            // 使用PDFBox解析PDF文件
            PDDocument document = Loader.loadPDF(pdfContent);
            // 创建PDF渲染器
            PDFRenderer renderer = new PDFRenderer(document);
            // 获取指定页面的图像
            BufferedImage image = renderer.renderImage(pageNumber); // 页码从0开始
            // 将图像保存为JPG格式的新文件
            ImageIO.write(image, "jpg", new File(jpgFilePath));

            System.out.println("PDF页面转换为JPG成功！");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("PDF页面转换为JPG失败：" + e.getMessage());
        }
    }



}
