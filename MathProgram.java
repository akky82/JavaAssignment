package Assignment2;
import java.util.*;

/* Author: Damon Hill
 * Student ID: 9632816
 * 
 * This program consists of 4 mathematical methods, prompting the user to choose
 * which method to invoke and handles appropriately. The program continues until
 * the user chooses to exit.
 */
public class MathProgram {
	// Print a menu to promt the user to choose which method to invoke, or exit.
	public static void printMenu() {
		System.out.println("----------------------------------------");
		System.out.println("Choose from one of the following:\n");
		System.out.println("1 - Adding two numbers greater than 20");
		System.out.println("2 - Sum of even numbers from 1-50");
		System.out.println("3 - Min, Max and range of numbers in an array");
		System.out.println("4 - Check if a value is in an array");
		System.out.println("0 - EXIT");
		System.out.println("----------------------------------------");
	}
	
	// This method prompts the user for 2 numbers, if both numbers are greater
	// then 20, the function will add them and output the result
	public static void addNumbers(Scanner input) {
		// Scanner input = new Scanner(System.in);
		double firstNumber, secondNumber;
		boolean flag = false;
		
		System.out.println("This program takes two numbers, if they are both more than 20, add them.");
		
		// Loop until 2 valid numbers are input
		do {
			// Prompt for the numbers
			System.out.println("Enter the first number: ");
			try {			
				firstNumber = input.nextDouble();
			}
			catch (InputMismatchException e) {
				System.out.println("*** Invalid number entered (must be a whole or decimal number), restarting. ***");
				input.nextLine();
				continue;
			}
			
			System.out.println("Enter the second number: ");
			try {
				secondNumber = input.nextDouble();
			}
			catch (InputMismatchException e) {
				System.out.println("*** Invalid number entered (must be a whole or decimal number), restarting. ***");
				input.nextLine();
				continue;
			}
			
			// Check if they are both greater than 20
			if ((firstNumber > 20) && (secondNumber > 20)) {
				// Add the numbers, output result, and set the flag to exit the loop
				System.out.println("Success, the total is " + (firstNumber + secondNumber));
				flag = true;
			} else {
				// Output error, then continue loop to prompt again
				System.out.println("Sorry, one or both numbers are less than 20, try again");
				System.out.println("----------------------------------------");
			}
			
		} while (!flag);
	}
	
	// This method finds the sum of all even numbers from 1 to 50
	public static void sumEvenNumbers() {
		int total = 0;
		
		// Loop through all numbers from 1 to 50
		for (int i = 1; i <= 50; i++) {
			// Check to see if the current number is even
			if ((i % 2) == 0) {
				// If so, add it to the running total
				total += i;
			}
		}
		
		// Output the result
		System.out.println("The total of all even numbers between 1 and 50 is: " + total + ".");
	}

	// Method to find the minimum and maximum numbers, as well as the range, in a given array
	public static void minMaxRange() {
		// Initialise the array with the given values
		int[] myArray = new int[] { 16,67,65,45,32,19,86,54,34,21 };
		
		// Initialise minimum and maximum to first value in the array. Initialising these
		// to 0 at the start would cause logic problems with an array of negative numbers
		int minimum = myArray[0], maximum = myArray[0];
		
		// Loop through the array to compare each number
		for (int i: myArray) {
			// If current number is greater than max, assign max to be the current number
			if (i > maximum) {
				maximum = i;
			}
			
			// If current number is less than minimum, assign minimum to be the current number
			if (i < minimum) {
				minimum = i;
			}
		}
		
		// Output the results
		System.out.println("The minimum value is " + minimum
				+ " and the maximum value is " + maximum + ".");
		System.out.println("The range of the array is " + (maximum - minimum) + ".");
	}
	
	// Method to check if a user input value is contained in a given array
	public static void searchArray(Scanner input) {
		// Initialise array with given values
		int[] anotherArray = new int[] { 45,32,78,65,43 };
		boolean numberFound = false;
		int userInput = 0;
		
		// Prompt the user to input a number to check for
		System.out.println("Please enter a number to check if it is in the array:");
		
		// Loop until a number is found
		while (!numberFound) {
			try {
				userInput = input.nextInt();
				
				// Loop through the array to compare each element to the input
				for (int j : anotherArray) {
					if (j == userInput) {
						// If found, exit the loop
						numberFound = true;
						break;
					}
				}
				
				// If the value isn't found, prompt the user for another
				if  (!numberFound) {
					System.out.println("The number " + userInput + " isn't in the list, please try again:");
				}
			}
			catch (InputMismatchException e) {
				System.out.println("Invalid input, a whole number is required, please try again:");
				input.nextLine();
			}

			
		}
		
		System.out.println("The number " + userInput + " is in the list!");
	}
	
	// Main method
	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int choice = -1;
		
		// Continue the program after each method call is finished, until user chooses to exit with 0
		do {		
			// Output a menu displaying the options to the user and prompt for their choice
			printMenu();
			try {
				choice = input.nextInt();
			}
			catch (InputMismatchException e) {
				choice = -1;
				input.nextLine();
			}
			
			switch (choice) {
			case 1: addNumbers(input); break;
			case 2: sumEvenNumbers(); break;
			case 3: minMaxRange(); break;
			case 4: searchArray(input); break;
			default: System.out.println("Sorry, that isn't a valid selection, please try again.");
			}			
		} while (choice != 0);
		
		System.out.println("Program terminated.");
		input.close();
	}
}