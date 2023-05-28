package adt.linkedList;

import java.util.ArrayList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int res = 0;
		SingleLinkedListNode<T> aux = this.head;

		while(!aux.isNIL()) {
			res++;
			aux = aux.getNext();
		}

		return res;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.head;

		while(!aux.isNIL()) {
			if (aux.getData().equals(element)) return element;
			aux = aux.getNext();
		}

		return null;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			SingleLinkedListNode<T> aux = this.head;

			while (!aux.isNIL())
				aux = aux.getNext();

			aux.setData(element);
			aux.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if (search(element) != null) {

			if (this.head.getData().equals(element)) {
				setHead(head.getNext());
			} else {

				SingleLinkedListNode<T> aux = this.head;

				while (!aux.isNIL() && !aux.getData().equals(element))
					aux = aux.getNext();

				if (!aux.isNIL()) {
					aux.setData(aux.getNext().getData());
					aux.setNext(aux.getNext().getNext());
				}
			}

		}
	}

	@Override
	public T[] toArray() {
		ArrayList<T> lista = new ArrayList<>();
		SingleLinkedListNode<T> aux = this.head;

		while(!aux.isNIL()) {
			lista.add(aux.getData());
			aux = aux.getNext();
		}

		return (T[]) lista.toArray(new Comparable[lista.size()]);
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
