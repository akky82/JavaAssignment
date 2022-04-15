package Assignment2;
import java.util.*;

/* Author: Damon Hill
 * Student ID: 9632816
 * 
 * This program has been created to mimic a contact list, it creates a predefined 
 * array list of objects for testing, then prompts the user for input and acts 
 * accordingly by checking if a number exists in the list, after which it calls 
 * the object's methods; or allows the user to add a new object to the list, 
 * of which the type is determined via processing the user's input.
 * 
 * The program continues, allowing the user to check and add multiple numbers,
 * including the ones they have input themselves, until the user chooses to exit.
 * 
 * This program demonstrates inheritance and method overriding, as well as using
 * an abstract class to create an array list that can contain multiple inherited
 * subclasses, which can be identified using type checking, and then their unique 
 * methods can be called via type casting.
 */


// Abstract Phone class, defined as abstract for the purpose of
// extending and allow usage in a collection
abstract class Phone {
	String number;
	String serviceProvider;
	
	// Outputs the phone number
	void printNumber() {
		System.out.println("Phone number is: " + number);
	}
	
	// Dials the phone number
	void dial() {
		System.out.println("Dialing " + number + "...");
	}
}

// MobilePhone class extends Phone by adding battery duration
// and method to send a given text message to the number
class MobilePhone extends Phone {
	int batteryDuration;
	
	// Constructor
	public MobilePhone(String number, String serviceProvider, int batteryDuration) {
		this.number = number;
		this.serviceProvider = serviceProvider;
		this.batteryDuration = batteryDuration;
	}
	
	// Outputs the phone number
	void printNumber() {
		System.out.println(number + " is for a mobile phone service, registered on the " + serviceProvider + " network.");
	}
	
	// Output a message confirming sending a given text message to the number
	void sendText(String message) {
		System.out.println("Sending message \"" + message + "\" to " + number);
	}
	
	// Output the battery duration
	void printBatteryDuration() {
		System.out.println("Batery duration is " + batteryDuration + "%");
	}
}

// FixedLinePhone class extends Phone by adding a street address
class FixedLinePhone extends Phone {
	String streetAddress;
	
	// Constructor
	public FixedLinePhone(String number, String serviceProvider, String streetAddress) {
		this.number = number;
		this.serviceProvider = serviceProvider;
		this.streetAddress = streetAddress;
	}
	
	// Outputs the phone number
	void printNumber() {
		System.out.println(number + " is a fixed line number, registered on the " + serviceProvider + " network.");
	}
	
	// Output the street address registered to the number
	void printAddress() {
		System.out.println("The number is registered to the address: " + streetAddress);
	}
}

// FaxMachine class extends FixedLinePhone by adding 
// a business name and overriding the dial method
class FaxMachine extends FixedLinePhone {
	String businessName;
	 
	// Constructor
	public FaxMachine(String number, String serviceProvider, String streetAddress, String businessName) {
		super(number, serviceProvider, streetAddress);
		this.businessName = businessName;
	}
	
	// Outputs the phone number
	void printNumber() {
		System.out.println(number + " is for a fax service, registered on the " + serviceProvider + " network.");
	}
	
	// Output the business name registered to the number
	void printBusinessName() {
		System.out.println("This number is registered to the business: " + businessName);
	}
	
	// Dial the number
	void dial() {
		System.out.println("Please insert a document to initiate fax service.");
	}
}

public class PhoneTest { 
	// Print a menu each time the program loops, to prompt the user for input
	public static void printMenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("Choose from the following:");
		System.out.println("- Enter a phone number to search for and call its methods");
		System.out.println("- Enter \"A\" to enter a new phone number to the phonebook");
		System.out.println("- Enter 0 to stop the program");
		System.out.println("------------------------------------------------------------");
	}
	
	
	// Method to validate the length and content of the phone number given
	public static boolean validateNumber(String number) {
		if (number.length() != 10) {
			System.out.println("*** Error: Phone number should be 10 digits. ***");
			return false;
		} else if (!number.matches("[0-9]+")) {
			System.out.println("*** Error: Phone number can only contain numbers, not letters or special characters. ***");
			return false;
		}
		
		return true;
	}
	
	
	// Method that takes an argument of the abstract class Phone and calls appropriate
	// methods depending on what type of subclass the object is.
	public static void testNumber(Phone phoneNumber, Scanner input) {
		if (phoneNumber instanceof MobilePhone) {
			// Call methods for MobilePhone class
			phoneNumber.printNumber();
			((MobilePhone)phoneNumber).printBatteryDuration();
			
			// Prompt the user whether they wish to call or send a text to the mobile number
			System.out.println("Would you like to call this number, or send a text message ? (Input \"C\" for call, or \"T\" for text)");
			boolean validChoice = false;
			
			do {
				String choice = input.nextLine().trim();
				if (choice.matches("c|C")) {
					// Dial the number
					phoneNumber.dial();
					validChoice = true;
				} else if (choice.matches("t|T")) {
					// Get a non-empty user input for a message to pass to the sendText method
					System.out.println("Enter the message you wish to send:");
					String message = input.nextLine().trim();
					
					while (message.isBlank()) {
						System.out.println("Sorry, please enter the message you wish to send:");
						message = input.nextLine().trim();
					}
					
					((MobilePhone)phoneNumber).sendText(message);
					validChoice = true;
				} else {
					System.out.println("Sorry, that's an invalid choice, please enter \"C\" to call, or \"T\" to send a text message.");
				}
			} while (!validChoice);
		} else if (phoneNumber instanceof FaxMachine) {
			// Call methods for FaxMachine class
			phoneNumber.printNumber();
			((FaxMachine)phoneNumber).printBusinessName();
			((FaxMachine)phoneNumber).printAddress();
			phoneNumber.dial();
		} else if (phoneNumber instanceof FixedLinePhone) {
			// Call methods for FixedLinePhone class
			phoneNumber.printNumber();
			((FixedLinePhone)phoneNumber).printAddress();
			phoneNumber.dial();
		} else {
			System.out.println(phoneNumber.number + " is a number of unknown type.");
			phoneNumber.dial();
		}
	}
	
	// Method to prompt user to input values to be used to create a FixedLinePhone
	// or FaxMachine object
	public static Phone createFixedOrFax(String number, Scanner input) {
		Phone phone = null;
		
		// Get non-empty user input for service provider
		System.out.println("Enter a service provider:");
		String provider = input.nextLine().trim();
					
		while (provider.isBlank()) {
			System.out.println("Sorry, please enter a service provider:");
			provider = input.nextLine().trim();
		}
					
		// Get non-empty user input for street address
		System.out.println("Enter a street address:");
		String address = input.nextLine().trim();
		while (address.isBlank()) {
			System.out.println("Sorry, please enter a street address:");
			address = input.nextLine().trim();
		};
					
		// Prompt the user to choose if the number is for a fax service or not
		System.out.println("Is this for a fax service? Enter \"Y\" for yes or \"N\" for no.");
		String isFax = input.nextLine().trim();
					
		while (!isFax.matches("y|Y|N|n")) {
			System.out.println("Sorry, please enter \"Y\" if it is for a fax service, or \"N\" for no.");
			isFax = input.nextLine().trim();
		}
					
		// Check if number is for a fax service and get business name and create a FaxMachine object
		// Otherwise create a FixedLinePhone object
		if (isFax.matches("y|Y")) {
			// Get non-empty user input for business name
			System.out.println("Enter the business name:");
			String businessName = input.nextLine().trim();
						
			while (businessName.isBlank()) {
				System.out.println("Sorry, enter the business name:");
				businessName = input.nextLine().trim();
			}
						
			// Create a FaxMachine object
			phone = new FaxMachine(number, provider, address, businessName);
		} else { 
			// Create a FixedLinePhone object
			phone = new FixedLinePhone(number, provider, address);
		}
		
		return phone;
	}
	
	// Method to prompt user to input values to be used to create a MobilePhone object
	public static MobilePhone createMobilePhone(String number, Scanner input) {
		MobilePhone phone = null;
		// Get non-empty user input for service provider
		System.out.println("Enter a service provider:");
		String provider = input.nextLine().trim();
								
		while (provider.isBlank()) {
			System.out.println("Sorry, please enter a service provider:");
			provider = input.nextLine().trim();
		}
					
		// Get a valid whole number from user input for battery duration
		boolean validBatteryDuration = false;
					
		do {
			System.out.println("Please enter the battery duration:");
					
			try {
				int batteryDuration = input.nextInt();

				// Create a MobilePhone object
				phone = new MobilePhone(number, provider, batteryDuration);
				validBatteryDuration = true;
				
				// Clear the scanner input of any new line or whitespace which may have 
				// been entered, to prevent calling printMenu() multiple times
				input.nextLine();
			}
			catch (InputMismatchException e) {
				System.out.println("Sorry, invalid input, please enter a whole number for battery duration:");
			}
		} while (!validBatteryDuration);
		
		return phone;
	}
	
	
	// Method to add a new number to the phone book by determining what method it should
	// invoke to create an object of the appropriate Phone subclass based on the area code
	public static Phone addNumber(String number, Scanner input) {		
		Phone phone = null;
		// Check to see if the number should be a FixedLinePhone (or FaxMachine), MobilePhone, or doesn't have a valid area code
		if (number.substring(0,2).matches("02|03|07|08")) {
			// Invoke a method to create a FixedLinePhone or FaxMachine object
			phone = createFixedOrFax(number, input);
		} else if (number.substring(0,2).equals("04")) {
			// Invoke a method to get user input to create a MobilePhone object
			phone = createMobilePhone(number, input);
		} else {
			// If the area code is not valid, method will return null Phone object
			System.out.println("*** Error adding number, invalid area code. ***");
		}

		// Return the object we created, or null if there was an error
		return phone;
	}

	
	// Main method
	public static void main(String[] args) {
		
		// Create an array list phone book of predefined phone objects of different types
		List<Phone> phonebook = new ArrayList<>();
		phonebook.add(new FixedLinePhone("0211112222", "Vodafone", "123 Somewhere St, ELSEWHERE, 2000"));
		phonebook.add(new FixedLinePhone("0311112222", "Optus", "456 Another Ave, ELSEWHERE, 3000"));
		phonebook.add(new FixedLinePhone("0755556666", "Telstra", "789 Fake Rd, SOMEWHERE, 4000"));
		phonebook.add(new MobilePhone("0411112222", "TPG", 11));
		phonebook.add(new MobilePhone("0433334444", "Vodafone", 95));
		phonebook.add(new MobilePhone("0455556666", "Virgin Mobile", 57));
		phonebook.add(new FaxMachine("0712345678", "Tesltra", "123 Example Drive, SOMEWHERE, 4000", "The Tool Shop"));
		
		Scanner input = new Scanner(System.in);
		boolean flag = false;
		
		// Run the program until the user chooses to exit
		do {
			printMenu();
			// Prompt the user for input, stripping all whitespace
			String number = input.nextLine().replaceAll("\\s", "");
			
			// Check to see if the user wishes to exit, if not, validate the number input
			if (number.isEmpty()) {
			} else if (number.equals("0")) {
				flag = true;
				break;
			} else if (number.matches("a|A")) {
				// Ask for a number to add, handle validation and add to the phone book if appropriate
				System.out.println("Enter a phone number to add to the phone book:");
				String newNumber = input.nextLine().replaceAll("\\s", "");
				
				// Check for any errors regarding number format and content
				if (!validateNumber(newNumber)) {
					continue;
				}
				
				// Check to see if the number already exists in the phone book
				boolean numberExists = false;
				
				for (Phone checkNumber: phonebook) {
					if (checkNumber.number.equals(newNumber)) {
						System.out.println("That number is already listed in the phone book.");
						numberExists = true;
					}
				}
				
				// If the number is not found, create one, otherwise just return to the menu
				if (!numberExists) {
					// Invoke a method to create an object of a subclass of Phone to add to the phone book
					Phone phone = addNumber(newNumber, input);
				
					// Check if any errors regarding area code
					if (phone != null) {
						// Add the element to the phone book array list
						phonebook.add(phone);
						System.out.println("Success, added " + newNumber + " to phone book.");
					}
				}
			} else if (validateNumber(number)) {
				int index = -1;
				
				// Loop through the phone book to search for the number from user input
				for (int i = 0; i < phonebook.size(); i++) {
					if (phonebook.get(i).number.equals(number)) {
						index = i;
					}
				}
				
				// If the number is found, test it by passing the object to a function, otherwise not found
				if (index >= 0) {
					testNumber(phonebook.get(index), input);
				} else {
					System.out.println("Number not found.");
				}
			}
		} while (!flag);
		
		System.out.println("Program terminated.");
		input.close();
	} 
}
