package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node.isEmpty()) return 0;
		else return height((BSTNode<T>) node.getLeft()) - height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balance = this.calculateBalance(node);

		BSTNode<T> newRoot = null;

		if (Math.abs(balance) > 1) {
			if (balance > 1) {
				if (this.calculateBalance((BSTNode<T>) node.getLeft()) >= 0) newRoot = Util.rightRotation(node);
				else newRoot = Util.leftRightRotation(node);
			} else {
				if (this.calculateBalance((BSTNode<T>) node.getRight()) <= 0) newRoot = Util.leftRotation(node);
				else newRoot = Util.rightLeftRotation(node);
			}
		}

		if (getRoot().equals(node) && newRoot != null) root = newRoot;
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		while (node.getParent() != null) {
			rebalance((BSTNode<T>) node.getParent());
			node = (BSTNode<T>) node.getParent();
		}
	}

	@Override
	public void insert (T elem) {
		if (elem != null) this.insert(root, elem);
	}

	private void insert (BSTNode<T> node, T elem) {
		if (node.isEmpty()) {
			node.setData(elem);
			node.setLeft(new BSTNode.Builder<T>().parent(node).build());
			node.setRight(new BSTNode.Builder<T>().parent(node).build());
		} else {
			if (elem.compareTo(node.getData()) <= 0) this.insert((BSTNode<T>) node.getLeft(), elem);
			else this.insert((BSTNode<T>) node.getRight(), elem);

			rebalance(node);
		}
	}

	@Override
	public void remove (T elem) {
		BSTNode<T> node = search(elem);
		if (!node.isEmpty()) this.remove(node);
	}

	private void remove (BSTNode<T> node) {
		if (node.isLeaf()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);
			rebalanceUp(node);
		} else if (node.getRight().isEmpty() || node.getLeft().isEmpty()) {
			BSTNode<T> childNode =
					!node.getLeft().isEmpty() ? (BSTNode<T>) node.getLeft() : (BSTNode<T>) node.getRight();

			if (this.root.equals(node)) {
				this.root = childNode;
				this.root.setParent(null);
			} else {
				childNode.setParent(node.getParent());

				if (node.getParent().getLeft().equals(node)) node.getParent().setLeft(childNode);
				else node.getParent().setRight(childNode);
			}

			rebalanceUp(node);
		} else {
			BSTNode<T> sucessor = this.sucessor(node.getData());
			node.setData(sucessor.getData());
			this.remove(sucessor);
		}
	}
}
