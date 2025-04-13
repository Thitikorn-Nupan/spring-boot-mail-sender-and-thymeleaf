package com.ttknp.understandspringmailsender.service;

import ch.qos.logback.classic.Logger;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 if you wanna use this Service (Bean) you have to inject first
 The EmailSender class has a constructor that takes an instance of JavaMailSender as a parameter.
 It also has a sendEmail method that sends an email with the specified email address, subject, and content.
 We utilize the MimeMessageHelper class to set up the email message
 <br>
 <h3>About class</h3>
 MailSender interface: the top-level interface that provides basic functionality for sending simple emails
 JavaMailSender interface: the subinterface of the above MailSender. It supports MIME messages and is mostly used in conjunction with the MimeMessageHelper class for the creation of a MimeMessage. It’s recommended to use the MimeMessagePreparator mechanism with this interface.
 JavaMailSenderImpl class provides an implementation of the JavaMailSender interface. It supports the MimeMessage and SimpleMailMessage.
 SimpleMailMessage class: used to create a simple mail message including the from, to, cc, subject and text fields
 MimeMessagePreparator interface provides a callback interface for the preparation of MIME messages.
 MimeMessageHelper class: helper class for the creation of MIME messages. It offers support for images, typical mail attachments and text content in an HTML layout.
*/
@Service
public class GmailService {

    private final JavaMailSender mailSender; // this dependency have to inject before uses
    private final TemplateEngine templateEngine; // for convert html page to text
    // private final MimeMessage mimeMessage;

    private final Logger logger;
    private final String FROM_SMTP = "thitikorn-n@rmutp.ac.th";
    private final String FROM_PERSONAL = "THITIKORN NUPAN (OWN BOT)";


    @Autowired
    public GmailService(JavaMailSender mailSender, TemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        // this.mimeMessage = this.mailSender.createMimeMessage(); // one time use only work
        logger = (Logger) LoggerFactory.getLogger(this.getClass());
    }

    public Boolean sendEmailContentAsHTMLString(String subject, String email, String content) {
        try {
            // work with JavaMailSender
            MimeMessage mimeMessage = mailSender.createMimeMessage(); // for create instant of mineMessage from mailSender
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            // ** set up email below
            // ** setFrom(...,...) it will follow from your SMTP you got
            mimeMessageHelper.setFrom(FROM_SMTP, FROM_PERSONAL+" ** HTML String"); // ** header mail
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            // connect as texts but you can use html syntax with it
            mimeMessageHelper.setText(content, true);
            // req it.
            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return false;
        }
    }

    /*
     All html files should be on src/main/resources/templates if you use a default thymeleaf spring boot
     Thymeleaf can't process the template only texts , So i have to use Template Resovlver help it
     ขึ้นมาเองและใช้ Text Mode ซึ่งจะไม่ต้องยุ่งยากกับ tag HTML แต่ก็มีข้อจำกัดมาก ซึ่งการใช้ HTML Mode จะยืดหยุ่นที่สุด
    */
    public Boolean sendEmailContentAsHTMLFile(String subject, String email,String p0,String p1, String p2,String p3, String p4) {
        try {
            // Context class work as thymeleaf html page , So you can set variable that you provided on html page on this object
            // ** smart mapping
            Context context = new Context();
            context.setVariable("p0", p0);
            context.setVariable("date", getLocalDatetime());
            context.setVariable("p1", p1);
            context.setVariable("p2", p2);
            context.setVariable("p3", p3);
            context.setVariable("p4", p4);
            // ** work with JavaMailSender
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // ** set up email below
            messageHelper.setFrom(FROM_SMTP, FROM_PERSONAL+" ** HTML File");
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            // ** convert html file to string
            String content = templateEngine.process("email_receipt.html", context); // convert html to string
            messageHelper.setText(content, true);
            // req it.
            mailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
            return false;
        }
    }


    private String getLocalDatetime () {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return myDateObj.format(myFormatObj); // 14-01-2025 16:27:54
    }
}
