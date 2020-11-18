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

public class PasswordHash {
	static StudentListCtrl studentList = new StudentListCtrl();
	static byte[][] SaltArray = new byte[studentList.getStudentList().size()][];
	static String[] HashedPasswords = new String[studentList.getStudentList().size()];
	
	public static void PasswordHashed() throws Exception {
	//LOGIN
		//--------------------------------------------------------------------------------------
		FileWriter fwOb = new FileWriter("StudentHashedInfo.txt", false); 
		PrintWriter pwOb = new PrintWriter(fwOb, false);
		pwOb.flush();
		pwOb.close();
		fwOb.close();
		// line above empties the text file each time as each time the system is run, the salt changes and hence the corresponding passwords change
		Writer output;
		output = new BufferedWriter(new FileWriter("StudentHashedInfo.txt", true));
//		StudentListCtrl studentList = new StudentListCtrl();
//		byte[][] SaltArray = new byte[studentList.getStudentList().size()][];
//		String[] HashedPasswords = new String[studentList.getStudentList().size()];
		
		for(int i = 0; i < studentList.getStudentList().size(); i++) {
		byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(),studentList.getStudent(i).getPassword());
		output.append(ReturnHashInfo(studentList.getStudent(i).getUsername(),studentList.getStudent(i).getPassword(),currentsalt));
		SaltArray[i]= currentsalt;
		HashedPasswords[i]= ReturnHashedPassword(studentList.getStudent(i).getPassword(),currentsalt);
		}
		output.close();
	}
	
		public static byte[][] ReturnByteArray() {
			for(int i = 0; i < studentList.getStudentList().size(); i++) {
				byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(),studentList.getStudent(i).getPassword());
				SaltArray[i]= currentsalt;
			}
			return SaltArray;
		}
		
		public static String[] ReturnHashedPasswordsArray() {
			StudentListCtrl studentList = new StudentListCtrl();
			String[] HashedPasswords = new String[studentList.getStudentList().size()];
			for(int i = 0; i < studentList.getStudentList().size(); i++) {
				byte[][] SaltArray = new byte[studentList.getStudentList().size()][];
				byte[] currentsalt = Salt(studentList.getStudent(i).getUsername(),studentList.getStudent(i).getPassword());
				SaltArray[i]= currentsalt;
				HashedPasswords[i]= ReturnHashedPassword(studentList.getStudent(i).getPassword(),currentsalt);
				}
			return HashedPasswords;
		}
	//--------------------------------------------------------------------------------
	//Salt Hashing https://www.javainterviewpoint.com/java-salted-password-hashing/
		public static byte[] Salt ( String StudentID, String password) {
		MessageDigest md;
		try
		{	
		  //Select the message digest for the hash computation -> SHA-256
		  md = MessageDigest.getInstance("SHA-256");

		  // Generate the random salt
		  SecureRandom random = new SecureRandom();
		  byte[] salt = new byte[16];
		  random.nextBytes(salt);

		  // Passing the salt to the digest for the computation
		  md.update(salt);
		  return salt;
		} catch (NoSuchAlgorithmException e)
		{
		e.printStackTrace();
		}
		return null;
		}
	//--------------------------------------------------------------------------------
		public static String ReturnHashInfo (String StudentID ,String Password, byte[] salt)
		{
		MessageDigest md;
		try
		{	
		 md = MessageDigest.getInstance("SHA-256");
		 // Generate the salted hash
		 byte[] hashedPassword = md.digest(Password.getBytes(StandardCharsets.UTF_8));

		 StringBuilder sb = new StringBuilder();
		 for (byte b : hashedPassword)
		     sb.append(String.format("%02x", b));
		 String Finaloutput = "StudentID:" + StudentID + "		" + "Password:" + sb + "\n";
		 
		 return Finaloutput;
		} catch (NoSuchAlgorithmException e)
		{
		 //e.printStackTrace();
		}
		return "Error" ;
		}
//---------------------------------------------------------------
		public static String ReturnHashedPassword (String Password, byte[] salt)
		{
		MessageDigest md;
		try
		{	
		 md = MessageDigest.getInstance("SHA-256");
		 // Generate the salted hash
		 byte[] hashedPassword = md.digest(Password.getBytes(StandardCharsets.UTF_8));

		 StringBuilder sb = new StringBuilder();
		 for (byte b : hashedPassword)
		     sb.append(String.format("%02x", b));
		 String Finaloutput = sb.toString();
		 return Finaloutput;
		} catch (NoSuchAlgorithmException e)
		{
		 //e.printStackTrace();
		}
		return "Error" ;
		}//--------------------------------------------------------
}

