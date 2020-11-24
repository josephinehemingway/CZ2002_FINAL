package proj;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MainUI {

	// LOGIN
	public static void main(String[] args) throws Exception {
		int choice = 0;
		String username;
		String password;
		Scanner sc = new Scanner(System.in);

		PasswordHash.PasswordHashed();
		byte[][] SaltArray = PasswordHash.ReturnByteArray();
		String[] HashedPasswords = PasswordHash.ReturnHashedPasswordsArray();

		do {
			System.out.println("Welcome to STARS! Enter your choice: ");
			System.out.println("(1) Log in as Student");
			System.out.println("(2) Log in as Admin");
			System.out.println("(3) Quit");

			while (true) {
				try {
					while (true) {
						choice = sc.nextInt();
						if (choice == 1 || choice == 2 || choice == 3) {

							break;
						} else {
							System.out.println("Invalid choice, please enter integer between 1 and 3. ");
						}
					}
					break;
				} catch (Exception e) {
					System.out.println("Invalid choice, please enter integer between 1 and 3. ");
					sc = new Scanner(System.in);
				}
			}

			switch (choice) {
			case 1:
				int count = 0;
				while (true) { // student login
					System.out.println("\nLogging in as Student");
					System.out.println("Username: ");
					username = sc.next().toUpperCase();
					System.out.println("Password: ");
					password = sc.next();
					if (UserValidation.loginStudent(username, password, SaltArray, HashedPasswords) == true) {
						if (UserValidation.studentCanAccess(username) == true) {
							System.out.println("Login successfully!");
							StudentUI.console(username);
						}
						break;
					} else {
						System.out.println("Incorrect username or password!\n");
						count += 1;
						if (count == 3) {
							System.out.println("You have entered incorrect username or password 3 times. Exiting...\n");
							break;
						}
					}
				}
				break;

			case 2:
				int count2 = 0;
				while (true) {
					System.out.println("\nLogging in as Admin");
					System.out.println("Username: ");
					username = sc.next().toUpperCase();
					System.out.println("Password: ");
					password = sc.next();
					if (UserValidation.loginAdmin(username, password) == true) {
						System.out.println("Login successfully!");
						AdminUI.console();
						break;
					} else {
						System.out.println("Incorrect username or password!\n");
						count2 += 1;
						if (count2 == 3)
							System.out.println("You have entered incorrect username or password 3 times. Exiting...\n");
							break;
					}
				}
				break;

			case 3:
				System.out.println("Quitting.. ");
				break;

			default:
				System.out.println("Invalid input!");
				break;
			}
		} while (choice != 3);
	}
}