package chapter1.item2;

/**
 * Javabeans Pattern
 * setter 메서드를 사용한다
 * 단점 1 : 객체 하나를 만드려면 메서드를 여러개 호출해야 한다.
 * 단점 2 : 객체가 완전히 생성되기 전까지는 일관성이 무너진 상태에 놓이게 된다.

 */
public class PencilCase_JavaBeans {
	private int pen;
	private int eraser;
	private int pencil;

	public void setPen(int pen) {
		this.pen = pen;
	}

	public void setEraser(int eraser) {
		this.eraser = eraser;
	}

	public void setPencil(int pencil) {
		this.pencil = pencil;
	}
}
