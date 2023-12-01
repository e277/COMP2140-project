import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Session;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import javax.mail.Authenticator;

public class SendEmail {
    private StudentPrompt prompts;

    public SendEmail(StudentPrompt prompt) {
        this.prompts = prompt;
        sendEmail();
    }

    public void sendEmail() {
        // Sender's email address
        String from = "mkeshawn10@gmail.com";

        // Recipient's email address
        String[] to = { "tamaricashaw13@gmail.com", "bw6751430@gmail.com", "ezramuir12@gmail.com",
                "mkeshawn107@gmail.com", "britneyl.patterson@gmail.com" };

        // Sender's email credentials
        final String username = "mkeshawn10@gmail.com";
        final String password = "qwjs rrnd dpct emvs";

        // Properties for setting up the mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message1 = new MimeMessage(session);
            Message message2 = new MimeMessage(session);

            // Set From: header field of the header.
            message1.setFrom(new InternetAddress(from));
            message2.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            for (String t : to) {
                message1.addRecipients(Message.RecipientType.TO, InternetAddress.parse(t));
            }
            message2.setRecipients(Message.RecipientType.TO, InternetAddress.parse(prompts.getEmail()));

            // Set Subject: header field
            message1.setSubject("Sent Thesis");
            message2.setSubject("Received Thesis");

            // Now set the actual message
            message1.setText(
                    "Good day. please see the following thesis from" + prompts.getFName() + prompts.getLName());
            message2.setText(
                    "We have received your thesis. A librarian will reach out to you via corrections as soon as they're ready.");

            // Send message
            Transport.send(message1);
            Transport.send(message2);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
