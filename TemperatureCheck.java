package Assignment2;
import java.util.*;

/* Author: Damon Hill
 * Student ID: 9632816
 *
 * This program prompts the user to enter what day of the week to start,
 * then prompts the user to enter the temperature for each day, calculates
 * and outputs the minimum and maximum temperatures, as well as which day
 * those temperatures were recorded, then also calculate the average.
 */

public class TemperatureCheck {
	// Method takes input of string for the day of the week and returns corresponding number
	public static int getDayNumber(Scanner input) {
		int dayNumber = -1;
		System.out.println("What day of the week was the first day?");
		
		do {
			String dayName = input.next().trim();
		
			switch (dayName.toLowerCase()) {
			case ("monday") : dayNumber = 0; break;
			case ("tuesday") : dayNumber = 1; break;
			case ("wednesday") : dayNumber = 2; break;
			case ("thursday") : dayNumber = 3; break;
			case ("friday") : dayNumber = 4; break;
			case ("saturday") : dayNumber = 5; break;
			case ("sunday") : dayNumber = 6; break;
			default: System.out.println("*** Invalid day entered, try again. ***");	
			}
		} while (dayNumber == -1);
		
		return dayNumber;
	}
	
	// Method takes day number and returns the name of the corresponding day of the week
	public static String getDayName(int dayNumber) {
		String dayName = "";
		
		switch (dayNumber) {
		case 0: dayName = "Monday"; break;
		case 1: dayName = "Tuesday"; break;
		case 2: dayName = "Wednesday"; break;
		case 3: dayName = "Thursday"; break;
		case 4: dayName = "Friday"; break;
		case 5: dayName = "Saturday"; break;
		case 6: dayName = "Sunday"; break;
		default: dayName = "(day unknown)";
		}
		
		return dayName;
	}
	
	public static void main(String[] args) {
		double[] temperatures = new double[7];
		int dayNumber = -1;
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("------------------------------------------------------------");
		
		// Invoke a method that prompts the user to enter the day of the week that
		// the first temperature was for, to be used in later calculations
		dayNumber = getDayNumber(input);

		double minimum = 0;
		double maximum = 0;
		double total = 0;
		int maxDayNumber = dayNumber, minDayNumber = dayNumber;
		
		// Loop to prompt the user to input the temperatures for 7 days
		for (int i = 0; i < 7; i++) {
			System.out.println("Enter the temperature for day " + (i + 1) + ": ");
			try {
				temperatures[i] = input.nextDouble();
			
				// Add the new temperature to the total
				total += temperatures[i];
			
				// Compare new temperature to the current minimum/maximum and reassign if necessary
				// If first iteration of the loop, assign the first temperature to maximum
				if (i == 0 || temperatures[i] > maximum) {
					maximum = temperatures[i];
					// Calculate the corresponding day of the week for the maximum temperature
					maxDayNumber = (dayNumber + i) % 7;
				}
				
				// If first iteration of the loop, assign the first temperature to minimum
				if (i == 0 || temperatures[i] < minimum) {
					minimum = temperatures[i];
					// Calculate the corresponding day of the week for the minimum temperature
					minDayNumber = (dayNumber + i) % 7;
				}
			}
			catch (InputMismatchException e) {
				// Catch an input error and rerun the prompt for the current iteration
				System.out.println("*** Invalid value entered, please enter a whole or decimal number. ***");
				input.nextLine();
				i--;
			}
		}
		
		// Calculate the average of the temperatures
		double averageTemperature = total/7;
		
		// Output the results, including the day of the week that matches the relevant dates output
		System.out.println("------------------------------------------------------------");
		System.out.printf("The maximum temperature was %.1f degrees, first recorded on " + getDayName(maxDayNumber) + ".\n", maximum);
		System.out.printf("The minimum temperature was %.1f degrees, first recorded on " + getDayName(minDayNumber) + ".\n", minimum);
		System.out.printf("The average was %.1f degrees.", averageTemperature);
		
		input.close();
	}
}
