package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

import java.util.LinkedList;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (element != null && !this.isFull()) {
			int i = 0;
			boolean inserted = false;
			while (i < capacity() && !inserted) {
				int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
				if (this.table[hash] == null || this.table[hash].equals(this.deletedElement)) {
					this.table[hash] = element;
					this.elements++;
					this.COLLISIONS += i;
					inserted = true;
				} else if (this.table[hash].equals(element)) {
					inserted = true;
				} else {
					i++;
				}

				if (i >= capacity()) throw new HashtableOverflowException();
			}
		} else {
			if (this.isFull()) throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			int i = 0;
			boolean removed = false;

			while (i < capacity() && !removed) {
				int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);
				if (this.table[hash] != null) {
					if (this.table[hash].equals(element)) {
						this.table[hash] = this.deletedElement;
						this.elements--;
						this.COLLISIONS -= i;
						removed = true;
					}
					i++;
				} else {
					removed = true;
				}
			}
		}
	}

	@Override
	public T search(T element) {
		if (indexOf(element) == -1) return null;
		else return element;
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int i = 0;
			while(i < capacity()) {
				int hash = ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, i);

				if (this.table[hash] != null) {
					if (this.table[hash].equals(element))
						return hash;
					i++;
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

}
