package conversionToLinux;

import java.util.Scanner;

public class SingleArray {

	public static void main(String[] args) {
		String name;
/*
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter you name: ");
		name = input.nextLine();
		input.close();
		
		System.out.println();
		System.out.println("Welcome to Linux " + name + " .");
*/
		double myList[] = new double[3]; 
		
		myList[0] = 5.6;
		myList[1] = 4.5;
		myList[2] = 3.3;
		
		System.out.println(myList[2]);
		System.out.println();
		
		for(int i = 0; i < myList.length; i++) {
			System.out.println(myList[i] + " loop ");
		}
	}

}
