package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException, StackOverflowException {
		if (isFull()) {
			throw new QueueOverflowException();
		} else {
			stack1.push(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException, StackUnderflowException, StackOverflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			while(!stack1.isEmpty())
				stack2.push(stack1.pop());

			T res = stack2.pop();

			while(!stack2.isEmpty())
				stack1.push(stack2.pop());

			return res;
		}
	}

	@Override
	public T head() throws StackUnderflowException, StackOverflowException {
		if (isEmpty()) {
			return null;
		} else {
			while(!stack1.isEmpty())
				stack2.push(stack1.pop());

			T res = stack2.top();

			while(!stack2.isEmpty())
				stack1.push(stack2.pop());

			return res;
		}
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
