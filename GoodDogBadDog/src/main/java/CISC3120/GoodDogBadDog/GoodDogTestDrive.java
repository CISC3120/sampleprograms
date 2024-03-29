package CISC3120.GoodDogBadDog;

public class GoodDogTestDrive {
	public static void main(String[] args) {
		GoodDog one = new GoodDog();
		one.size = 5;
		one.setSize(70);
		GoodDog two = new GoodDog();
		two.setSize(8);
		System.out.println("Dog one: " + one.getSize());
		System.out.println("Dog two: " + two.getSize());
		one.bark();
		two.bark();
	}
}

class GoodDog {
	int size;

	public int getSize() {
		return size;
	}

	public void setSize(int s) {
		size = s;
	}

	void bark() {
		if (size > 60) {
			System.out.println("Wooof! Wooof!");
		} else if (size > 60) {
			System.out.println("Ruff! Ruff!");
		} else {
			System.out.println("Yip! Yip!");
		}
	}
}