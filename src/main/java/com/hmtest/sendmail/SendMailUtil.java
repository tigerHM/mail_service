package com.hmtest.sendmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @
 */

@RestController
public class SendMailUtil {
    @Autowired
    private  JavaMailSender javaMailSender;
    //读取配置文件中的参数
    @Value("${spring.mail.username}")
    private String sender;

    //发送验证码邮件
    @RequestMapping("/send")
    public String sendSimpleMail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo("197480460@qq.com");
            message.setSubject("主题邮件：验证码");
            String str = "";
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                str += random.nextInt(10);
            }
            message.setText("你的验证码是：" + str + "请妥善保管！如不是本人操作，请忽略！");
            javaMailSender.send(message);
            System.out.println(str);
            return "success";
        } catch (RuntimeException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
