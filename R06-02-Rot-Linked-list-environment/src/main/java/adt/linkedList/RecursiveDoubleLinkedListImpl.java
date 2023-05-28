package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if(this.isEmpty()){
				RecursiveDoubleLinkedListImpl<T> nilLast = new RecursiveDoubleLinkedListImpl<>();

				this.data = element;
				this.next = nilLast;
				nilLast.previous = this;

				if (this.previous == null){
					RecursiveDoubleLinkedListImpl<T> nilHead = new RecursiveDoubleLinkedListImpl<>();

					this.previous = nilHead;
					nilHead.setNext(this);
				}
			} else
				this.next.insert(element);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {

			if (this.isEmpty()) {
				this.insert(element);
			} else {
				RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();

				newNode.setData(this.data);
				this.setData(element);
				newNode.setNext(this.next);
				this.setNext(newNode);

				newNode.setPrevious(this);
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(newNode);
			}

		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			if (this.data.equals(element)){
				if (this.previous.isEmpty())
					this.removeFirst();
				else if (this.next.isEmpty())
					this.removeLast();
				else {
					this.previous.setNext(this.next);
					((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(this.previous);
				}
			}
			else
				this.next.remove(element);
		}
	}

	@Override
	public void removeFirst() {
		if (!this.isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setPrevious(null);
				this.setNext(null);
			} else {
				this.setData(this.getNext().getData());
				this.setNext(this.getNext().getNext());
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).setPrevious(this);
			}
		}
	}

	@Override
	public void removeLast() {
		if (!this.isEmpty()) {

			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);

				if (this.getPrevious().isEmpty()) this.setPrevious(null);

			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.getNext()).removeLast();
			}

		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
