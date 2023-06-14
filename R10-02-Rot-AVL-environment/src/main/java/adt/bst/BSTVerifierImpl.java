package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {
	
	private BSTImpl<T> bst;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}
	
	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		if (bst.isEmpty() || bst.size() == 1) return true;
		else return isBST(bst.getRoot());
	}

	private boolean isBST (BSTNode<T> node) {
		boolean resp;

		if (node.isEmpty()) {
			resp = true;
		} else {
			if (this.isValidLeft(node) && this.isValidRight(node))
				resp = isBST((BSTNode<T>) node.getLeft()) && isBST((BSTNode<T>) node.getRight());
			else
				resp = false;
		}

		return resp;
	}

	private boolean isValidLeft (BSTNode<T> node) {
		if (node.getLeft().isEmpty()) return true;
		else return node.getLeft().getData().compareTo(node.getData()) < 0;
	}

	private boolean isValidRight (BSTNode<T> node) {
		if (node.getRight().isEmpty()) return true;
		else return node.getRight().getData().compareTo(node.getData()) > 0;
	}

}
