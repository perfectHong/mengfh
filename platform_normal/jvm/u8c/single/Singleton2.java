package jvm.u8c.single;

class Singleton2 {
	
	public static int value1;
	
	public static int value2 = 0;
	
	private static Singleton2 singleton2 = new Singleton2();

	private Singleton2() {
		value1++;
		value2++;
	}

	public static Singleton2 getInstance2() {
		return singleton2;
	}

}