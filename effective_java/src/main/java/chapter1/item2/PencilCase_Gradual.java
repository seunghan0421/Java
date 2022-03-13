package chapter1.item2;

/**
 * 점층적 생성자 패턴
 * 단점 : 매개변수가 많아지면 클라이언트 코드를 작성하거나 읽기 어렵다.
 */
public class PencilCase_Gradual {
	private int pen;
	private int eraser;
	private int pencil;

	public PencilCase_Gradual(int pen, int eraser, int pencil) {
		this.pen = pen;
		this.eraser = eraser;
		this.pencil = pencil;
	}

	public PencilCase_Gradual(int pen, int eraser) {
		this(pen, eraser, 0);
	}

	public PencilCase_Gradual(int pen) {
		this(pen, 0, 0);
	}
}
