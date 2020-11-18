package proj;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

//ensure that the javamail and activation JARs are downloaded and added in the properties --> javabuildpath
class SendMailSSL {
  public static void send(String from, String password, String to, String sub, String msg) {
    // Get properties object
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    // get Session
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
      }
    });
    // compose message
    try {
      MimeMessage message = new MimeMessage(session);
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject(sub);
      message.setText(msg);
      // send message
      Transport.send(message);
      System.out.println("Email Message sent successfully");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }
  }

  public static void SendRegisteredNoti(String courseName, String courseID, int IndexID, String email) {
    String newmessage = "You have successfully registered for:\n" + "   CourseID  CourseName  Index   \n"
        + "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

    SendMailSSL.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage); // parameters
                                                        // are
                                                        // from,password,to,subject,message
  }
  public static void SendWaitListNoti(String courseName, String courseID, int IndexID, String email) {
    String newmessage = "You are placed on waitlist for:\n" + "   CourseID  CourseName  Index   \n"
        + "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

    SendMailSSL.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
  }


  public static void SendSwapNoti(String courseID, int initialIndexID, int finalIndexID, String peerName, String email) {
    String newmessage = "You have successfully changed Index for " + courseID + "\n"
        + "======================================\n" + "Index changed from " + initialIndexID + " to " + finalIndexID + " \n " + 
        " with " + peerName;

    SendMailSSL.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
  }

  public static void SendDroppedNoti(String courseName, String courseID, int IndexID, String email) {
    String newmessage = "You have successfully dropped:\n" + "   CourseID  CourseName  Index   \n"
        + "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

    SendMailSSL.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
  }
}