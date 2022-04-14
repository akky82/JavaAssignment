package Assignment2;
import java.util.*;

/* Author: Damon Hill
 * Student ID: 9632816
 *
 * Login program prompts user for user name, password, and authentication code.
 * Checks for a match to all 3 against predefined values, if not, provides error. 
 */
public class LoginProgram {

	public static void main(String[] args) {
		/* Create variables to hold valid values, was going to make these constants
		 * e.g. final String USERNAME but decided better to have the variable names 
		 * differ by more than just upper case vs lower case. */
		String validUsername = "admin";
		String validPassword = "password";
		String validAuthenticator = "123456";
		int attempts = 0;
		
		Scanner input = new Scanner(System.in);
		boolean credentialsMatch = false;
		
		// Loop through the login process until valid values are input
		// or exit after 4 failed attempts.
		do {
			// Prompt user to input user name
			System.out.println("Enter your username: ");
			String username = input.nextLine();
			
			// Prompt user to input password
			System.out.println("Enter your password: ");
			String password = input.nextLine();
			
			// Prompt user to input authenticator code
			System.out.println("Enter your two factor authentication code: ");
			String authenticator = input.nextLine();
			
			System.out.println("------------------------------------------------------------");
			
			// Check values against given valid value variables
			if (username.equals(validUsername) && password.equals(validPassword) && authenticator.equals(validAuthenticator)) {
				// Values are valid, set flag to exit loop
				System.out.println("Successfully logged in! Welcome " + username);
				credentialsMatch = true;
			} else {
				// Values don't match, loop again
				System.out.println("*** Username, password, or authentication code do not match, try again. ***");
				attempts++;
			}
			System.out.println("------------------------------------------------------------");
			
			if (attempts == 4) {
				// Exit program due to excess failed attempts
				System.out.println("** This account has been locked due to excess failed login attempts. ***");
				input.close();
				return;
			}
		} while (!credentialsMatch);
		
		input.close();
	}
}