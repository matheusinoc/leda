package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.head = new DoubleLinkedListNode<>();
		this.last = (DoubleLinkedListNode<T>) this.head;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T>
					newNode = new DoubleLinkedListNode<>(),
					nil = new DoubleLinkedListNode<>();

			newNode.setData(element);
			newNode.setNext(nil);
			nil.setPrevious(newNode);
			this.last.setNext(newNode);
			newNode.setPrevious(this.last);

			if (this.last.isNIL())
				this.head = newNode;

			this.last = newNode;
		}
	}

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<>();

		aux.setData(element);
		aux.setNext(this.getHead());
		aux.setPrevious(new DoubleLinkedListNode<>());

		((DoubleLinkedListNode<T>) this.getHead()).setPrevious(aux);

		if (this.head.isNIL()) this.last = aux;

		this.setHead(aux);
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.head.getData().equals(element))
				this.removeFirst();
			else if (this.last.getData().equals(element))
				this.removeLast();
			else {
				DoubleLinkedListNode<T> currentNode = (DoubleLinkedListNode<T>) this.head;

				while (!currentNode.isNIL() && !currentNode.getData().equals(element))
					currentNode = (DoubleLinkedListNode<T>) currentNode.getNext();

				if (!currentNode.isNIL()){
					currentNode.getPrevious().setNext(currentNode.getNext());
					((DoubleLinkedListNode<T>) currentNode.getNext()).setPrevious(currentNode.getPrevious());
				}
			}
		}
	}

	@Override
	public void removeFirst() {
		if (!this.getHead().isNIL()) {
			this.setHead(this.getHead().getNext());

			if (this.getHead().isNIL()) this.setLast((DoubleLinkedListNode<T>) this.getHead());
			else ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<>());
		}
	}

	@Override
	public void removeLast() {
		if (!this.getHead().isNIL()) {
			this.setLast(this.getLast().getPrevious());

			if (this.getLast().isNIL()) this.setHead(this.getLast());
			else ((DoubleLinkedListNode<T>) this.getLast()).setNext(new DoubleLinkedListNode<>());
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
