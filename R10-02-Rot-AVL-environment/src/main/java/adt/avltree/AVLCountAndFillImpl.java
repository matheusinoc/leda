package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {
		
	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		for (T elem : array)
			((BSTImpl<T>) this).insert(elem);
	}

	@Override
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);

		BSTNode<T> newRoot = null;

		if (Math.abs(balance) > 1) {
			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0) {
					newRoot = Util.rightRotation(node);
					LLcounter++;
				} else {
					newRoot = Util.leftRightRotation(node);
					LRcounter++;
				}
			} else {
				if (calculateBalance((BSTNode<T>) node.getRight()) <= 0) {
					newRoot = Util.leftRotation(node);
					RRcounter++;
				}
				else {
					newRoot = Util.rightLeftRotation(node);
					RLcounter++;
				}
			}
		}

		if (getRoot().equals(node) && newRoot != null) root = newRoot;
	}

}
