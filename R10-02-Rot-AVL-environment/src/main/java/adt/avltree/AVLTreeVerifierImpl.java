package adt.avltree;

import adt.bst.BSTNode;
import adt.bst.BSTVerifierImpl;

/**
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return isBST() && isAVL();
	}

	private boolean isAVL () {
		return avlTree.isEmpty() || isAVL(avlTree.getRoot());
	}

	private boolean isAVL (BSTNode<T> node) {
		boolean resp;

		if (node.isEmpty()) {
			resp = true;
		} else {
			if (Math.abs(avlTree.calculateBalance(node)) > 1)
				resp = false;
			else
				resp = isAVL((BSTNode<T>) node.getLeft()) && isAVL((BSTNode<T>) node.getRight());
		}

		return resp;
	}

}
