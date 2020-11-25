package proj;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * Control class which performs the sending of notifications to the student's
 * email.
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-20
 */

class SendMailSSLCtrl {
	/**
	 * Method that sends message from a valid Gmail to a Student's Email.
	 * 
	 * @param from     The email of the sender.
	 * @param password The email password of the sender.
	 * @param to       The email of the receiver student.
	 * @param sub      The subject of the message to be sent.
	 * @param msg      The message to be sent.
	 */
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

	/**
	 * Sends message notifying the student that he/she has successfully registered
	 * for a course.
	 * 
	 * @param courseName The courseName of the course the the student has
	 *                   successfully registered for.
	 * @param courseID   The courseID of the course the student has successfully
	 *                   registered for.
	 * @param IndexID    The indexID of the course the student has successfully
	 *                   registered for.
	 * @param email      The email of the student who registered for the course.
	 */
	public static void SendRegisteredNoti(String courseName, String courseID, int IndexID, String email) {
		String newmessage = "You have successfully registered for:\n" + "   CourseID  CourseName  Index   \n"
				+ "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

		SendMailSSLCtrl.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage); // parameters
		// are
		// from,password,to,subject,message
	}

	/**
	 * Sends message notifying the student that he/she has been placed on the
	 * waitingList for the course he/she wants to register
	 * 
	 * @param courseName The courseID of the course the student has been placed on
	 *                   waitinglist for.
	 * @param IndexID    The indexID of the course the student has been placed on
	 *                   waitinglist for.
	 * @param email      The email of the student we are sending the email to.
	 */
	public static void SendWaitListNoti(String courseName, String courseID, int IndexID, String email) {
		String newmessage = "You are placed on waitlist for:\n" + "   CourseID  CourseName  Index   \n"
				+ "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

		SendMailSSLCtrl.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
	}

	/**
	 * Sends message notifying the student that he/she has successfully swapped
	 * index with his/her peer.
	 * 
	 * @param courseID       The courseId of the course the student wants to swap
	 *                       indexes for.
	 * @param initialIndexID The IndexID of the student's original CourseIndex.
	 * @param finalIndexID   The IndexID of the peer's CourseIndex.
	 * @param peerName       The name of the peer the student is swapping indexes
	 *                       with.
	 * @param email          THe email of the student who is swapping index.
	 */
	public static void SendSwapNoti(String courseID, int initialIndexID, int finalIndexID, String peerName,
			String email) {
		String newmessage = "You have successfully changed Index for " + courseID + "\n"
				+ "======================================\n" + "Index changed from " + initialIndexID + " to "
				+ finalIndexID + " \n " + " with " + peerName;

		SendMailSSLCtrl.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
	}

	/**
	 * Sends message notifying the the student that he/she has successfully dropped
	 * the Course.
	 * 
	 * @param courseName The courseName of the course the student wants to drop.
	 * @param courseID   The courseID of the course that the student wants to drop.
	 * @param IndexID    The indexID of the course Index the student wants to drop.
	 * @param email      The email of the student that is dropping the course.
	 */
	public static void SendDroppedNoti(String courseName, String courseID, int IndexID, String email) {
		String newmessage = "You have successfully dropped:\n" + "   CourseID  CourseName  Index   \n"
				+ "======================================\n" + courseID + "  " + courseName + "  " + IndexID;

		SendMailSSLCtrl.send("kennntu@gmail.com", "DSAIcz2002", email, "Stars Planner Notification", newmessage);
	}
}