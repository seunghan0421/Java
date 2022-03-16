package chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {
	private E[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;

	// 이렇게 할 수 있지만 일반적으로 타입 안전하지 않다. 또한 경고가 뜬다.
	// 컴파일ㄹ러가 타입 안전한지 증명할 수 없기 떄문에 우리가 증명해얗 한다.

	// 배열 elements는 push(E)로 넘어온 E 인스턴스만 담는다.
	// 따라서 타입 안전성을 보장하지만
	// 이 배열의 런타임 타입은 E[] 가 아닌 Object[] 이다.
	@SuppressWarnings("unchecked")
	public Stack(){
		elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
	}

	public void push(E e){
		ensureCapacity();
		elements[size++] = e;
	}

	public E pop() {
		if(size == 0){
			throw new EmptyStackException();
		}
		E result = elements[--size];
		elements[size] = null;
		return result;
	}


	private void ensureCapacity(){
		if(elements.length == size){
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
	}
}
