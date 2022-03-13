package chapter1.item1;

public class Singleton {
	private static Singleton singleton = null;

	private Singleton(){}

	static Singleton getInstance(){
		if(singleton == null) {
			singleton = new Singleton();
		}

		return singleton;
	}
}
