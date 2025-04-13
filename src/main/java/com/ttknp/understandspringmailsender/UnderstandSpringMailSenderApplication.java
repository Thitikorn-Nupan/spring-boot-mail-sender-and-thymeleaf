package com.ttknp.understandspringmailsender;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UnderstandSpringMailSenderApplication {

    /*
    Create an instance of JavaMailSender
    private static GmailService gmailService;
    private static Logger logger;
    // Then inject it
    @Autowired
    public UnderstandSpringMailSenderApplication(GmailService service) {
        this.gmailService = service;
        logger = (Logger) LoggerFactory.getLogger(this.getClass());
    }
    private static void testJavaMailSender() {
        String recipientEmail = "thitikorn_nupan@outlook.co.th";
        String subject = "Greetings from Spring Boot Application";
        String content = "<p>Dear Mr/Ms &#128578;</p>" +
                "<br>Allow me to introduce both myself and our esteemed company. I am Bot, serving as Backend at TTN." +
                "<br>We specialize in providing state-of-the-art technology solutions tailored to meet all your email signature requirements. At TTN," +
                "<br>we extend a range of services, Our team members are deeply committed and ready to assist you with any inquiries or requests you may have. " +
                "<br>I am eager to arrange a discussion or meeting to discussdelve deeper into your specific needs and elaborate on how TTN can contribute to your success. " +
                "<br>Feel free to reach out to me at 0646760613 with any queries you may have. " +
                "<p><br>Thank you for considering us,Thitikorn Nupan &#129309;<p/>";
        // recipient (n. ผู้รับ)
        // Call the sendEmail method to send an email
        gmailService.sendEmail(subject, recipientEmail, content);
        logger.info("Email sent to {} is successfully!", recipientEmail);
    }
    */

    public static void main(String[] args) {
        SpringApplication.run(UnderstandSpringMailSenderApplication.class, args);
    }

}
