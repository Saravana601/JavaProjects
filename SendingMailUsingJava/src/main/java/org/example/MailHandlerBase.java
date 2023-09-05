package org.example;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import static javax.mail.Session.*;

public class MailHandlerBase {
    static String reciever = "xyz123@gmail.com";
    public void sendMail(){
        // Add Some Properties related to mailing

        Properties SystemProperties = System.getProperties();

        // We Need to add these four Properties in order to Send Mail

        SystemProperties.put("mail.smtp.host","smtp.gmail.com"); // Smtp Server
        SystemProperties.put("mail.smtp.port","465"); // Server Port
        SystemProperties.put("mail.smtp.ssl.enable","true"); // SSL - Secure Socket layer
        SystemProperties.put("mail.smtp.auth","true"); // Authentication(Auth)

        // Authentication


        Authenticator mailAuthenticator = new CustomizedAuthenticator();

        // Session

        Session mailSession = getInstance(SystemProperties, mailAuthenticator);

        // Add Subject and Body

        MimeMessage mailMessage = new MimeMessage(mailSession);

        try {
            mailMessage.setFrom(MailConstantValues.SENDER);
            mailMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(reciever));
            mailMessage.setSubject("Sending Mail Using Java");
            mailMessage.setText("Hello there! I'm Saravanan, this message is sent via Java Code, Keep practising, Drink Coffee, Have Fun!!!");

            Transport.send(mailMessage);
        }
        catch(Exception e){
            System.out.println("Failed to send Mail due to some Exception.");
        }
    }
}
