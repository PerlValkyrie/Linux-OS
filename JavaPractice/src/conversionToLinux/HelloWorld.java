package conversionToLinux;

import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {
		String name;

		Scanner input = new Scanner(System.in);
		System.out.println("Please enter you name: ");
		name = input.nextLine();
		input.close();
		
		System.out.println();
		System.out.println("Welcome to Linux " + name + " .");

	}

}
