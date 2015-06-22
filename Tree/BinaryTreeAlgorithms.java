class BinaryTreeNode {
	int value;

	BinaryTreeNode parrent = null;
	BinaryTreeNode leftChild = null;
	BinaryTreeNode rigthChild = null;

	BinaryTreeNode() {
		value = (int) Math.random() * 1000;
	}
}
