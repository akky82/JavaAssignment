package Assignment2;
import java.util.*;

/* Author: Damon Hill
 * Student ID: 9632816
 * 
 * Abstract shape class, I created this so we can create an instance of
 * Shape and then reassign it to the appropriate subclass later, this 
 * and keep the calls to the methods for perimeter and area out of the 
 * switch statement, as they would be called for whatever shape chosen */
abstract class Shape {
	void printArea() {}
	
	void printPerimeter() {}
}

//Circle class
class Circle extends Shape {
	double radius;
	
	// Constructor
	public Circle(double radius) {
		this.radius = radius;
	}
	
	// Method to calculate the area and output the result
	void printArea() {
		double area = 2 * Math.PI * radius;
		System.out.printf("\tThe area of the circle is: %.2f\n", area);
	}
	
	// Method to calculate the perimeter and output the result
	void printPerimeter() {
		double circumfrence = Math.PI * Math.pow(radius, 2);
		System.out.printf("\tThe circumfrence of the circle is: %.2f\n", circumfrence);
	}
}

// Rectangle class
class Rectangle extends Shape {
	double length, width;
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}
	
	void printArea() {
		double area = length * width;
		System.out.printf("\tThe area of the rectangle is: %.2f\n", area);
	}
	
	void printPerimeter() {
		double perimeter = 2 * (length + width);
		System.out.println("\tThe perimeter of the rectangle is: " + perimeter);
	}
}

// Hexagon class
class Hexagon extends Shape {
	double sideLength;
	
	// Constructor
	public Hexagon(double sideLength) {
		this.sideLength = sideLength;
	}
	
	// Method to calculate the area and output the result
	void printArea() {
		double area = ((3 * Math.sqrt(3)) / 2) * Math.pow(sideLength, 2);
		System.out.printf("\tThe area of the hexagon is: %.2f\n", area);
	}
	
	// Method to calculate the perimeter and output the result
	void printPerimeter() {
		double perimeter = 6 * sideLength;
		System.out.printf("\tThe perimeter of the hexagon is: %.2f\n", perimeter);
	}
}

// Program that prompts the user to choose a shape from a list, then prompts
// for the relevant dimensions, then calls then relevant methods to calculate
// and output the shape's area and perimeter.
public class ShapeTest {
	// Method to print a menu showing the available options for the user to choose
	public static void printMenu() {
		System.out.println("------------------------------------------------------------");
		System.out.println("Choose a shape to calculate area and perimeter: ");
		System.out.println("1 - Circle");
		System.out.println("2 - Rectangle");
		System.out.println("3 - Hexagon");
		System.out.println("0 - Exit");	
		System.out.println("------------------------------------------------------------");
	}
	
	// Method to prompt user for radius and return a circle object
	public static Circle createCircle(Scanner input) {
		// Prompt the user for the radius of a circle
		System.out.println("Enter the radius:");
		try {
			double radius = input.nextDouble();
	
			// Create an instance of Circle with the given value
			Circle shape = new Circle(radius);
			return shape;
		} 
		catch (InputMismatchException e) {
			System.out.println("*** Invalid number entered (must be a whole or decimal number), returning to menu. ***");
			input.nextLine();
			return null;
		}
	}
	
	// Method to prompt user for the side lengths of a rectangle and return a rectangle object
	public static Rectangle createRectangle(Scanner input) {
		double side1, side2;
		// Prompt user for the first side
		System.out.println("Enter the length of side 1:");
		try {
			side1 = input.nextDouble();
		}
		catch (InputMismatchException e) {
			System.out.println("*** Invalid number entered (must be a whole or decimal number), returning to menu. ***");
			input.nextLine();
			return null;
		}
		
		// Prompt user for a different length side
		System.out.println("Enter the length of side 2:");
		try {
			side2 = input.nextDouble();
		}
		catch (InputMismatchException e) {
			System.out.println("*** Invalid number entered (must be a whole or decimal number), returning to menu. ***");
			input.nextLine();
			return null;
		}
	
		// Check to see if the user actually input a square...
		if (Double.compare(side1, side2) == 0) {
			System.out.println("Sorry, that's a square, not a rectangle.");
			return null;
		}
	
		// Create an instance of Rectangle with the given values
		Rectangle shape = new Rectangle(side1, side2);
		return shape;
	}
	
	// Prompt user for the side length of a hexagon and return a hexagon object
	public static Hexagon createHexagon(Scanner input) {
		// Prompt the user for the side length
		System.out.println("Enter a side length:");
		try {
			double sideLength = input.nextDouble();

			// Create an instance of Hexagon with the given value
			Hexagon shape = new Hexagon(sideLength);
			return shape;
		}
		catch (InputMismatchException e) {
			System.out.println("*** Invalid number entered (must be a whole or decimal number), returning to menu. ***");
			input.nextLine();
			return null;
		}
	}
	
	// Main method
	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int choice;
		boolean flag = false;
		
		// Loop until the user chooses to exit
		do {
			printMenu();
			
			if (input.hasNextInt()) {
				choice = input.nextInt();
			} else {				
				System.out.println("Invalid choice, please choose one of the options given.");
				input.nextLine();
				continue;
			}
			
			Shape shape = null;
			
			switch(choice) {
			case 1:
				shape = createCircle(input);
				break;
			case 2:
				shape = createRectangle(input);
				break;
			case 3:
				shape = createHexagon(input);
				break;
			case 0:
				// Exit the program
				flag = true;
				break;
			default:
				System.out.println("Sorry, that isn't a valid option\n");
			}
			
			// Call the methods for the shape created
			if (shape != null) {
				shape.printArea();
				shape.printPerimeter();
			}
		} while (!flag);
		
		System.out.println("Program terminated.");
		input.close();
	}

}
