package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element == null);
		else if (isFull()) throw new StackOverflowException();
		else top.insertFirst(element);

	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) throw new StackUnderflowException();
		else {
			T res = top();
			this.top.removeFirst();
			return res;
		}
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			return ((DoubleLinkedListImpl<T>) this.top).getHead().getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return top.isEmpty();
	}

	@Override
	public boolean isFull() {
		return top.size() == size;
	}

}
