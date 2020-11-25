package proj;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Represents the Password Hashing class that is used on the Student's Passwords
 * 
 * @author DSAI/1 Group 5
 * @version 1.0
 * @since 2020-11-19
 *
 */
public class PasswordHash {
	/**
	 * studentList is a student list control object that holds the list of students
	 * and their login details SaltArray is an array which will store the Salt
	 * Strings of the Students' Passwords HashedPasswords is an array which will
	 * store the Hashed Passwords of the students
	 * 
	 */
	static StudentListCtrl studentList = new StudentListCtrl();
	static byte[][] SaltArray = new byte[studentList.getStudentList().size()][];
	static String[] HashedPasswords = new String[studentList.getStudentList().size()];

	/**
	 * Static method that is called to generate an ordered list of the Student's
	 * Usernames and their Corresponding hashed passwords in a text file
	 */
	public static void PasswordHashed() throws Exception {
		// LOGIN
		// --------------------------------------------------------------------------------------
		FileWriter fwOb = new FileWriter("StudentHashedInfo.txt", false);
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();
		// line above empties the text file each time as each time the system is run,
		// the salt changes and hence the corresponding passwords change
		Writer output;
		output = new BufferedWriter(new FileWriter("StudentHashedInfo.txt", true));

		for (int i = 0; i < studentList.getStudentList().size(); i++) {
			byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(), studentList.getStudent(i).getPassword());
			output.append(ReturnHashInfo(studentList.getStudent(i).getUsername(),
					studentList.getStudent(i).getPassword(), currentsalt));
			SaltArray[i] = currentsalt;
			HashedPasswords[i] = ReturnHashedPassword(studentList.getStudent(i).getPassword(), currentsalt);
		}
		output.close();
	}

	/**
	 * Static method that generates an array of the Salt String for all students in
	 * the student list
	 * 
	 * @return SaltArray once the entire list of Salt Strings has been generated
	 */
	public static byte[][] ReturnByteArray() {
		for (int i = 0; i < studentList.getStudentList().size(); i++) {
			byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(), studentList.getStudent(i).getPassword());
			SaltArray[i] = currentsalt;
		}
		return SaltArray;
	}

	/**
	 * Static method that generates and assigns a random of the Hashed Passwords for
	 * all students in the student list
	 * 
	 * @return HashedPasswords once the entire list of Hashed Student Passwords has
	 *         been generated
	 */
	public static String[] ReturnHashedPasswordsArray() {
		StudentListCtrl studentList = new StudentListCtrl();
		String[] HashedPasswords = new String[studentList.getStudentList().size()];
		for (int i = 0; i < studentList.getStudentList().size(); i++) {
			byte[][] SaltArray = new byte[studentList.getStudentList().size()][];
			byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(), studentList.getStudent(i).getPassword());
			SaltArray[i] = currentsalt;
			HashedPasswords[i] = ReturnHashedPassword(studentList.getStudent(i).getPassword(), currentsalt);
		}
		return HashedPasswords;
	}
	/**
	 * Static method that generates a random salt value for a student
	 * @param StudentID The StudentID of the student which the user wishes to Hash their corresponding password
	 * @param password The password of the student which the user wishes to Hash 
	 * @return salt The randomly generated salt string if no error occurs
	 */
	public static byte[] Salt(String StudentID, String password) {
		MessageDigest md;
		try {
			// Select the message digest for the hash computation -> SHA-256
			md = MessageDigest.getInstance("SHA-256");

			// Generate the random salt
			SecureRandom random = new SecureRandom();
			byte[] salt = new byte[16];
			random.nextBytes(salt);

			// Passing the salt to the digest for the computation
			md.update(salt);
			return salt;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	// --------------------------------------------------------------------------------
	/**
	 * Static method that derives and prints a Student's ID and their corresponding hashed password
	 * @param StudentID The StudentID of the student which the user wishes to Hash their corresponding password
	 * @param password The password of the student which the user wishes to Hash 
	 * @param salt The random salt string that is used to generate the hashed password
	 * @return Finaloutput It is a string of a Student's ID and their corresponding hashed password
	 */
	public static String ReturnHashInfo(String StudentID, String Password, byte[] salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			// Generate the salted hash
			byte[] hashedPassword = md.digest(Password.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();
			for (byte b : hashedPassword)
				sb.append(String.format("%02x", b));
			String Finaloutput = "StudentID:" + StudentID + "		" + "Password:" + sb + "\n";

			return Finaloutput;
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
		}
		return "Error";
	}

//---------------------------------------------------------------
	/**
	 * Static method that derives the Hashed version of the given password input
	 * @param password The password of the student which the user wishes to Hash 
	 * @param salt The random salt string that is used to generate the hashed password
	 * @return Finaloutput It is the Hashed version of a given password
	 */
	public static String ReturnHashedPassword(String Password, byte[] salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			// Generate the salted hash
			byte[] hashedPassword = md.digest(Password.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();
			for (byte b : hashedPassword)
				sb.append(String.format("%02x", b));
			String Finaloutput = sb.toString();
			return Finaloutput;
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
		}
		return "Error";
	}// --------------------------------------------------------
}
