package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return 1 + getNext().size();
		}
	}

	@Override
	public T search(T element) {
		T res = null;

		if (!isEmpty()) {
			if (getData().equals(element)) res = getData();
			else res = getNext().search(element);
		}

		return res;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				setData(element);
				setNext(new RecursiveSingleLinkedListImpl<>());
			} else {
				getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {

			if (getData().equals(element)) {
				setData(getNext().getData());
				setNext(getNext().getNext());
			} else {
				getNext().remove(element);
			}

		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> res = new ArrayList<>();
		toArray(res, this);
		return (T[]) res.toArray(new Comparable[res.size()]);
	}

	private void toArray(ArrayList<T> list, RecursiveSingleLinkedListImpl node) {
		if (!node.isEmpty()) {
			list.add((T) node.getData());
			toArray(list, node.getNext());
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
