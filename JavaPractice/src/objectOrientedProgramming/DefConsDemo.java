package objectOrientedProgramming;

public class DefConsDemo {

	public int myInt;
	public float myFloat;
	public double myDouble;
	public DefConsDemo()
	{
		System.out.println("I am initializing with my own choice.");
		myInt = 10;
		myFloat = 0.123456f;
		myDouble = 9.8765432;
	}
}

	class DefaultConstructorCaseStudy {
		public static void main(String[] args) {
			System.out.println("**Demonstration-2.Comparison between user-defined and  Java-provided default constructors***\n");
			DefConsDemon ObDef = new DefConsDemo();
			System.out.println("myInt=" + ObDef.myInt);
			System.out.println("myFloat=" + ObDef.myFloat);
			System.out.println("myDouble=" + ObDef.myDouble);
		}
	}
