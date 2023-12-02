package ntukhpi.kn221a.kro.webappsyrlab3.service;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public final class EmailSenderService {
    //region AuntificationData

    private static final Properties PROPERTIES = new Properties();
    private static final String USERNAME = "emailsender288@gmail.com";
    private static final String PASSWORD = "ahgo vfsy uarm dxaq";
    private static final String HOST = "smtp.gmail.com";

    //endregion

    static {
        PROPERTIES.put("mail.smtp.host", "smtp.gmail.com");
        PROPERTIES.put("mail.smtp.port", "587");
        PROPERTIES.put("mail.smtp.auth", "true");
        PROPERTIES.put("mail.smtp.starttls.enable", "true");
    }

    public static boolean sendEmailWithAttachment(String from, String to, String subject, File fileToSend, boolean debug) {
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        };

        Session session = Session.getInstance(PROPERTIES, authenticator);
        session.setDebug(debug);

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("The attached file.");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            MimeBodyPart attachmentPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileToSend);
            attachmentPart.setDataHandler(new DataHandler(source));
            attachmentPart.setFileName(fileToSend.getName());
            multipart.addBodyPart(attachmentPart);

            msg.setContent(multipart);

            Transport.send(msg);

            return true;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            Exception ex = null;
            if ((ex = mex.getNextException()) != null) {
                ex.printStackTrace();
            }

            return false;
        }
    }
}
