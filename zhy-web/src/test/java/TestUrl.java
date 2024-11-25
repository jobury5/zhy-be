import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: jobury
 * @Date: 2024/9/11 09:53
 */

public class TestUrl {

    // 定义一个正则表达式来匹配URL
    private static final String URL_REGEX =
            "^(https?|ftp)://" + // 协议
                    "(?:(?:[A-Z0-9](?:[A-Z0-9-]{0,61}[A-Z0-9])?\\.)+" + // 域名
                    "[A-Z]{2,6}\\.?|" + // 顶级域名
                    "localhost|" + // 本地主机
                    "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}|" + // IP地址
                    "\\[[A-F0-9]*:[A-F0-9:]+\\])" + // IPv6地址
                    "(?::\\d+)?" + // 可选端口
                    "(?:/?|[/?]\\S+)$"; // 路径和查询字符串

    // 编译正则表达式
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX, Pattern.CASE_INSENSITIVE);

    /**
     * 验证给定的字符串是否为有效的URL。
     *
     * @param url 要验证的URL字符串
     * @return 如果URL有效则返回true，否则返回false
     */
    public static boolean isValidURL(String url) {
        Matcher matcher = URL_PATTERN.matcher(url);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String[] testUrls = {
                "https://aisailings-uat.oss-cn-shanghai.aliyuncs.com/work/cert/2024/09/202409020TGPMVMY.pdf?x-oss-date=20240911T015223Z&x-oss-expires=3599&x-oss-signature-version=OSS4-HMAC-SHA256&x-oss-credential=LTAI5tCmMitgvVds2w9YdFuw%2F20240911%2Fcn-shanghai%2Foss%2Faliyun_v4_request&x-oss-signature=3b39bbbd3733ba38ac6296d999c81673fd1dd8d920aecdb85ca21ad2502e030c"
        };

        for (String testUrl : testUrls) {
            System.out.println(testUrl + ": " + isValidURL(testUrl));
        }
    }

}
