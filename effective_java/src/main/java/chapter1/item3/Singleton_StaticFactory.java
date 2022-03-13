package chapter1.item3;

public class Singleton_StaticFactory {
	private static final Singleton_StaticFactory INSTANCE = new Singleton_StaticFactory();

	private Singleton_StaticFactory(){}

	public static Singleton_StaticFactory getInstance() {
		return INSTANCE;
	}

	public void leaveTheBuilding(){
		//...
	}
}
