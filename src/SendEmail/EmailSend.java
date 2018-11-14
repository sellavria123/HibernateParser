/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * https://www.google.com/settings/security/lesssecureapps
 */
package SendEmail;

/**
 *
 * @author Naveen
 */
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;

public class EmailSend {

    public static void main(String args[]) {
        /**
         * https://www.youtube.com/watch?v=UMfjndwGwnM
         */
        try {
            String host = "smtp.gmail.com";
            String user = "marsellavaleria19@gmail.com";
            String pass = "123haella!";
            String to = "dogsegies@gmail.com";
            String from = "marsellavaleria19@gmail.com";
            String subject = "This is confirmation number for your expertprogramming account. Please insert this number to activate your account.";
            String messageText = 
"<body>\n" +
"<blockquote>\n" +
"  <p style=\"color:#000000; font-family:Arial; font-size:12px\">Dear XXX,</p>\n" +
"  <h4 style=\"color:#000000; font-family:Arial; font-size:14px\">Warm greetings from Lenora Hotel..</h4> \n" +
"  <p style=\"color:#000000; font-family:Arial; font-size:12px\">Thank you for your trust to stay in Lenora Hotel.<br>\n" +
"     Here, we pleased to attach reservation invoice as your reference. <br><br>\n" +
"     Please send payment receipt latest on  xxxxx.<br><br> 	\n" +
"	 Thank you for your attention. If you have any question, don't hesitate to contact us.<br>\n" +
"	 <br><br>\n" +
"	 Regrads, <br><br>\n" +
"  	 Asep<br>\n" +
"	 Reservation</p>\n" +
"  <p style=\"color:#000000; font-family:Arial; font-size:12px\">_________________________________________________________</p>\n" +
"  <pre style=\"font-family:Courier New; font-size:12px; color:#000000\"><b style=\"color:#000000; font-size:16px; font-family:Courier New;\">LENORA HOTEL</b>\n" +
"JL. KOPO NO 20, BANDUNG<br>OFFICE : <br>EMAIL  : </pre>\n" +
"</blockquote>\n" +
"</body>";
          Multipart multipart = new MimeMultipart();
         BodyPart messageBodyPart = new MimeBodyPart();
         messageBodyPart.setContent(messageText,"text/html; charset=utf-8");
        
         multipart.addBodyPart(messageBodyPart);

         // Part two is attachment
         BodyPart attachBodyPart = new MimeBodyPart();
         String filename = "src/SendEmail/coba.pdf";
         DataSource source = new FileDataSource(filename);
         attachBodyPart.setDataHandler(new DataHandler(source));
         attachBodyPart.setFileName(filename);   
         multipart.addBodyPart(attachBodyPart);
            boolean sessionDebug = false;
            
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
            props.put("mail.smtp.ssl.trust", host);

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setContent(multipart);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
