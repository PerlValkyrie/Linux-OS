package objectOrientedProgramming;

public class Main {
	
	private int myInt = 25;
	
	public static void main(String[] args) {
		
		System.out.println("***Demonstration-1. A class demo with 2 objects ***");
		Main obA = new Main();
		Main obB = new Main();
		System.out.println("obA.myInt = " + obA.myInt);
		System.out.println("obA.myInt = " + obB.myInt);
		
	}
	public class ClassEx1 {
		
		int myInt;
		

	}

}
