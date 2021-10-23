package conversionToLinux.testFolder;

import java.util.Scanner;

public class VariableTest {

	public static void main(String[] args) {
		// Selected variables
		String firstName;
		byte date;
		double year;
		float calendarDays;
		boolean state;
		
		// Initializing data
		firstName = "Randy Jenkins";
		date = 21;
		year = 2021;
		calendarDays = 365.2425f;
		state = true;
		
		// Scanner data
		float newNumber;
		
		
		System.out.println("This is a test of the variables in Java: "
		+ "First Name: " + firstName + ", Date: " + date + ", Year: " + year + ", Calendar Days: " + calendarDays + ", State: " + state + ". ");
		System.out.println();
		
		// Scanner test
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter a boring number: ");
		newNumber = input.nextFloat();
		
		System.out.println("The boring number is: " + newNumber + ". ");
		
		
		
	}

}
