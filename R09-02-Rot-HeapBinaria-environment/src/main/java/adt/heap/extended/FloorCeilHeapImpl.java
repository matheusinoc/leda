package adt.heap.extended;

import java.util.Comparator;

import adt.heap.HeapImpl;

public class FloorCeilHeapImpl extends HeapImpl<Integer> implements FloorCeilHeap {

	public FloorCeilHeapImpl(Comparator<Integer> comparator) {
		super(comparator);
	}

	@Override
	public Integer floor(Integer[] array, double num) {
		Integer floor = null;

		for (Integer n : array)
			insert(n);

		while(!isEmpty()) {
			Integer root = extractRootElement();
			if (root <= num && (floor == null || root >= floor)) floor = root;
		}

		return floor;
	}

	@Override
	public Integer ceil(Integer[] array, double num) {
		Integer ceil = null;

		for (Integer value : array)
			this.insert(value);

		while(!isEmpty()) {
			Integer root = extractRootElement();
			if (root >= num && (ceil == null || root <= ceil)) ceil = root;
		}

		return ceil;
	}

}
