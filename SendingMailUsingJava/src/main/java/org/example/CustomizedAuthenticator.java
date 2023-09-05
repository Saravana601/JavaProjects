package org.example;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class CustomizedAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(MailConstantValues.SENDER,"***********");
    }
}
