package com.zhy.external.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.zhy.exception.ApiException;
import com.zhy.external.MailService;
import com.zhy.config.prop.MailProperties;
import com.zhy.result.SysCode;
import com.zhy.types.MailAddress;
import com.zhy.types.MailContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * @Author: jobury
 * @Date: 2024/9/11 18:38
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailProperties mailProperties;

    @Override
    public Boolean sendMail(MailAddress toMail, MailContent mailContent, Map<String, Object> paramMap) {
        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", mailProperties.getSmtpHost());
        //设置端口：
        //props.put("mail.smtp.port", "80");//或"25", 如果使用ssl，则去掉使用80或25端口的配置，进行如下配置：
        //加密方式：
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.port", "465");

        props.put("mail.smtp.from", mailProperties.getSmtpFrom());    //mailfrom 参数
        props.put("mail.user", mailProperties.getUser());// 发件人的账号（在控制台创建的发信地址）
        props.put("mail.password", mailProperties.getPassword());// 发信地址的smtp密码（在控制台选择发信地址进行设置）
        props.setProperty("mail.smtp.ssl.enable", "true");  //请配合465端口使用
        System.setProperty("mail.mime.splitlongparameters", "false");//用于解决附件名过长导致的显示异常
        //props.put("mail.smtp.connectiontimeout", 1000);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };

        //使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        //mailSession.setDebug(true);//开启debug模式
        final String messageIDValue = genMessageID(props.getProperty("mail.user"));
        //创建邮件消息
        MimeMessage message = new MimeMessage(mailSession) {
            @Override
            protected void updateMessageID() throws MessagingException {
                //设置自定义Message-ID值
                setHeader("Message-ID", messageIDValue);//创建Message-ID
            }
        };

        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址。和上面的mail.user保持一致。名称用户可以自定义填写。
            InternetAddress from = new InternetAddress(mailProperties.getUser(), mailProperties.getName());//from 参数,可实现代发，注意：代发容易被收信方拒信或进入垃圾箱。
            message.setFrom(from);

            //可选。设置回信地址
//            Address[] a = new Address[1];
//            a[0] = new InternetAddress("收信地址");
//            message.setReplyTo(a);

            // 设置收件人邮件地址
            InternetAddress to = new InternetAddress(toMail.getAddress());
            message.setRecipient(MimeMessage.RecipientType.TO, to);
            //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数，具体参考规格清单）：
            //InternetAddress[] adds = new InternetAddress[2];
            //adds[0] = new InternetAddress("收信地址");
            //adds[1] = new InternetAddress("收信地址");
            //message.setRecipients(Message.RecipientType.TO, adds);

            message.setSentDate(new Date()); //设置时间
            //set mail title
            message.setSubject(mailContent.getSubject());
            // 替换邮件内容中的占位符号，比如模板内容为：Hello, my name is {name}. I am {age} years old.
            String realContent = mailContent.getContent();
            if(CollUtil.isNotEmpty(paramMap)){
                realContent = StrUtil.format(realContent, paramMap);
            }
            // text/html超文本;"text/plain;charset=UTF-8" //纯文本。
            message.setContent(realContent, "text/html;charset=UTF-8");

//            发送附件和内容：
//            BodyPart messageBodyPart = new MimeBodyPart();
//            //messageBodyPart.setText("消息<br>Text");//设置邮件的内容，文本
//            messageBodyPart.setContent("测试<br> 内容", "text/html;charset=UTF-8");// 纯文本："text/plain;charset=UTF-8" //设置邮件的内容
//            //创建多重消息
//            Multipart multipart = new MimeMultipart();
//            //设置文本消息部分
//            multipart.addBodyPart(messageBodyPart);

//            //附件部分
//            //发送附件，总的邮件大小不超过15M，创建消息部分。
              //发送本地附件
//            String[] fileList = new String[2];
//            fileList[0] = "C:\\Users\\test1.txt";
//            fileList[1] = "C:\\Users\\test2.txt";
//            for (int index = 0; index < fileList.length; index++) {
//                MimeBodyPart mimeBodyPart = new MimeBodyPart();
//                //            //设置要发送附件的文件路径
//                FileDataSource filesdata = new FileDataSource(fileList[index]);
//                mimeBodyPart.setDataHandler(new DataHandler(filesdata));
//                //处理附件名称中文（附带文件路径）乱码问题
//                mimeBodyPart.setFileName(MimeUtility.encodeWord("自定义附件名.xlsx"));
//                mimeBodyPart.addHeader("Content-Transfer-Encoding", "base64");
//                multipart.addBodyPart(mimeBodyPart);
//            }


            //发送URL附件
//            String[] fileList = new String[2];
//            fileList[0] = "https://example.oss-cn-shanghai.aliyuncs.com/xxxxxxxxxxx1.png";
//            fileList[1] = "https://example.oss-cn-shanghai.aliyuncs.com/xxxxxxxxxxx2.png";
//            for  (int index = 0; index < fileList.length; index++) {
//                String encode = URLEncoder.encode(fileList[index], "UTF-8");
//                MimeBodyPart mimeBodyPart = new MimeBodyPart();
//                mimeBodyPart.setDataHandler(new DataHandler(new URLDataSource(new URL(encode.replace("%3A",":").replace("%2F","/")))));
//                mimeBodyPart.setFileName(MimeUtility.encodeText("自定义附件名.xlsx"));
//                multipart.addBodyPart(mimeBodyPart);
//            }

//            //发送含有附件的完整消息
//            message.setContent(multipart);
//            // 发送附件代码，结束

            // 发送邮件
            Transport.send(message);
        } catch (MessagingException|UnsupportedEncodingException e) {
            throw new ApiException(SysCode.SMTP_ERROR, e.getMessage());
        }
        return true;
    }

    private static String genMessageID(String mailFrom) {
        // message-id 必须符合 first-part@last-part
        String[] mailInfo = mailFrom.split("@");
        String domain = mailFrom;
        int index = mailInfo.length - 1;
        if (index >= 0) {
            domain = mailInfo[index];
        }
        UUID uuid = UUID.randomUUID();
        StringBuffer messageId = new StringBuffer();
        messageId.append('<').append(uuid.toString()).append('@').append(domain).append('>');
        return messageId.toString();
    }

}
