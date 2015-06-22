import java.util.*;

class BinaryTreeNode {
	int value;

	BinaryTreeNode parrent = null;
	BinaryTreeNode leftChild = null;
	BinaryTreeNode rigthChild = null;

	BinaryTreeNode() {
		value = (int) Math.random() * 1000;
	}
}

public class BinaryTreeAlgorithms {
	
	// Make random-balanced binary tree
	public static BinaryTreeNode makeRandomBallancedBibaryTree() {
		BinaryTreeNode root;
		int arraySize = 15;
		int[] anArray = new int[arraySize];

		// Initialize random array
		for (int i = 0; i < arraySize; i++)
			anArray[i] = (int) (Math.random() * 10000);

		Arrays.sort(anArray);

		root = convertSortedArrayToBinarySearchtree(anArray, 0, arraySize - 1);
		return root;

	}

	public static BinaryTreeNode makeRandomBallancedBibaryTree(int[] anArray) {

		BinaryTreeNode root;

		Arrays.sort(anArray);

		root = convertSortedArrayToBinarySearchtree(anArray, 0,
				anArray.length - 1);
		return root;

	}

	// Convert sorted array to balanced binary search tree
	// It's good to follow JAVA convention by including the first index and
	// exclusing the last index!
	public static BinaryTreeNode convertSortedArrayToBinarySearchtree(
			int[] anArray, int start, int end) {
		if (start > end || start < 0 || end < 0)
			return null;
		else if (start == end) {
			BinaryTreeNode root = new BinaryTreeNode();
			root.value = anArray[start];
			return root;
		} else {
			// Important line for stating the middle of array point!
			int mid = start + (end - start) / 2;
			BinaryTreeNode root = new BinaryTreeNode();
			root.value = anArray[mid];
			root.leftChild = convertSortedArrayToBinarySearchtree(anArray,
					start, mid - 1);
			if (root.leftChild != null)
				root.leftChild.parrent = root;
			root.rigthChild = convertSortedArrayToBinarySearchtree(anArray,
					mid + 1, end);
			if (root.rigthChild != null)
				root.rigthChild.parrent = root;
			return root;
		}
	}

	// Binary tree In-order traversal recursively
	public static void printBinarySearchTreeInOrderRecursively(
			BinaryTreeNode root) {
		if (root == null)
			return;
		else {
			printBinarySearchTreeInOrderRecursively(root.leftChild);
			System.out.print(" " + root.value);
			printBinarySearchTreeInOrderRecursively(root.rigthChild);
			return;
		}
	}

	// Binary tree pre-order recursively
	public static void printBinarySearchTreePreOrderRecursively(
			BinaryTreeNode root) {
		if (root == null)
			return;
		else {
			System.out.print(" " + root.value);
			printBinarySearchTreePreOrderRecursively(root.leftChild);
			printBinarySearchTreePreOrderRecursively(root.rigthChild);
			return;
		}

	}

	// Binary tree post-order recursively
	public static void printBinarySearchTreePostOrderRecursively(
			BinaryTreeNode root) {
		if (root == null)
			return;
		else {
			printBinarySearchTreePostOrderRecursively(root.leftChild);
			printBinarySearchTreePostOrderRecursively(root.rigthChild);
			System.out.print(" " + root.value);
			return;
		}

	}

	// Binary tree In-order traversal recursively
	public static void printBinarySearchTreeInOrderRecursively(
			BinaryTreeNode root, List<BinaryTreeNode> inOrderList) {
		if (root == null)
			return;
		else {
			printBinarySearchTreeInOrderRecursively(root.leftChild, inOrderList);
			inOrderList.add(root);
			printBinarySearchTreeInOrderRecursively(root.rigthChild,
					inOrderList);
			return;
		}

	}

	// Binary tree pre-order recursively
	public static void printBinarySearchTreePreOrderRecursively(
			BinaryTreeNode root, List<BinaryTreeNode> preOrderList) {
		if (root == null)
			return;
		else {
			preOrderList.add(root);
			printBinarySearchTreePreOrderRecursively(root.leftChild,
					preOrderList);
			printBinarySearchTreePreOrderRecursively(root.rigthChild,
					preOrderList);
			return;
		}

	}

	// Binary tree post-order recursively
	public static void printBinarySearchTreePostOrderRecursively(
			BinaryTreeNode root, List<BinaryTreeNode> postOrderList) {
		if (root == null)
			return;
		else {
			printBinarySearchTreePostOrderRecursively(root.leftChild,
					postOrderList);
			printBinarySearchTreePostOrderRecursively(root.rigthChild,
					postOrderList);
			postOrderList.add(root);
			return;
		}

	}

	// Calculate the depth of the binary tree
	public static int binaryTreeDepth(BinaryTreeNode root) {
		if (root == null)
			return 0;
		else
			return 1 + Math.max(binaryTreeDepth(root.leftChild),
					binaryTreeDepth(root.rigthChild));
	}

	// Clone binary tree
	public static BinaryTreeNode cloneBinaryTree(BinaryTreeNode root) {
		if (root == null)
			return null;
		else {
			BinaryTreeNode clonedRoot = new BinaryTreeNode();
			clonedRoot.value = root.value;
			clonedRoot.leftChild = cloneBinaryTree(root.leftChild);
			clonedRoot.rigthChild = cloneBinaryTree(root.rigthChild);
			return clonedRoot;
		}
	}

	// check if two binary trees are equal
	public static boolean isEqual(BinaryTreeNode root1, BinaryTreeNode root2) {
		if (root1 == null && root2 == null)
			return true;
		else if ((root1 != null && root2 == null)
				|| (root1 == null && root2 != null))
			return false;
		else if (root1.value != root2.value)
			return false;
		else
			// Important Line
			return isEqual(root1.leftChild, root2.leftChild)
					&& isEqual(root1.rigthChild, root2.rigthChild);
	}

	// insert node to binary search tree
	public static void insertNodeInBinarySearchTree(BinaryTreeNode root,
			BinaryTreeNode node) {
		if (root == null || node == null)
			return;
		else if (node.value < root.value) {
			if (root.leftChild == null) {
				root.leftChild = node;
				node.parrent = root;
			} else
				insertNodeInBinarySearchTree(root.leftChild, node);
		} else {
			if (root.rigthChild == null) {
				root.rigthChild = node;
				node.parrent = root;
			} else
				insertNodeInBinarySearchTree(root.rigthChild, node);
		}
		return;
	}

	// delete node from binary search tree
	public static boolean deleteNodeFromBinarySearchTree(BinaryTreeNode root,
			BinaryTreeNode node) {
		if (root == null || node == null)
			return true;
		else if (root.value > node.value)
			return deleteNodeFromBinarySearchTree(root.leftChild, node);
		else if (root.value < node.value)
			return deleteNodeFromBinarySearchTree(root.rigthChild, node);
		else {
			if (root.leftChild == null && root.rigthChild == null) {
				// you can define get parent function in binary tree
				if (root.parrent != null) {
					if (root.parrent.leftChild != null) {
						if (root.parrent.leftChild.value == node.value)
							root.parrent.leftChild = null;
					} else
						root.parrent.rigthChild = null;
					return true;
				} else
					return false;
			} else if (root.leftChild != null && root.rigthChild == null) {
				root.value = root.leftChild.value;
				root.leftChild = null;
				return true;
			} else if (root.leftChild == null && root.rigthChild != null) {
				root.value = root.rigthChild.value;
				root.rigthChild = null;
				return true;
			}
			// node has two children
			else {
				BinaryTreeNode tmp = findMaxElementInBinarySearchTree(root.leftChild);
				root.value = tmp.value;
				return deleteNodeFromBinarySearchTree(root, tmp);
			}
		}
	}

	// Find the node that contains maximum element
	public static BinaryTreeNode findMaxElementInBinarySearchTree(
			BinaryTreeNode root) {
		if (root == null)
			return null;
		else if (root.rigthChild == null)
			return root;
		else
			return findMaxElementInBinarySearchTree(root.rigthChild);
	}

	// sum of even level minus odd level
	public static int evenLevelMinusOddLevel(BinaryTreeNode root, int depth) {

		if (root == null)
			return 0;
		else {
			return ((int) Math.pow(-1, depth))
					* root.value
					+ (evenLevelMinusOddLevel(root.leftChild, depth + 1) + evenLevelMinusOddLevel(
							root.rigthChild, depth + 1));

		}
	}

	// make binary tree from Inorder-postorder traversal
	public static BinaryTreeNode buildTree(int[] inorder, int is, int ie,
			int[] postorder, int ps, int pe) {
		if (is > ie || ps > pe)
			return null;
		int rootVal = postorder[pe];
		BinaryTreeNode root = new BinaryTreeNode();
		root.value = rootVal;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] == rootVal) {
				BinaryTreeNode left = buildTree(inorder, is, i - 1, postorder,
						ps, ps + i - is - 1);
				BinaryTreeNode right = buildTree(inorder, i + 1, ie, postorder,
						pe - ie + i, pe - 1);
				root.leftChild = left;
				root.rigthChild = right;
			}
		}
		return root;
	}

	// Write a function isBST(BinaryTree *node) to verify if a given binary tree
	// is a Binary
	// Search Tree (BST) or not.
	public static boolean isBinarySearchTree(BinaryTreeNode root, Count tmp) {
		if (root == null)
			return true;
		else {
			if (root.leftChild != null && root.rigthChild != null)
				if (root.leftChild.value <= root.value
						&& root.value <= root.rigthChild.value) {
					tmp.count++;
					return isBinarySearchTree(root.leftChild, tmp)
							&& isBinarySearchTree(root.rigthChild, tmp);
				} else
					return false;
			else if (root.leftChild == null && root.rigthChild != null)
				if (root.value <= root.rigthChild.value) {
					tmp.count++;
					return isBinarySearchTree(root.rigthChild, tmp);
				} else
					return false;
			else if (root.leftChild != null && root.rigthChild == null)
				if (root.leftChild.value <= root.value) {
					tmp.count++;
					return isBinarySearchTree(root.leftChild, tmp);
				} else
					return false;
			else
				return true;
		}
	}

	// Print all edge nodes of a "complete" binary tree anti-clockwise.
	// That is all the left most nodes starting at root, then the leaves left to
	// right and finally all the rightmost nodes.
	// In other words, print the boundary of the tree.
	// "Variant: Print the same for a tree that is not complete."
	public static void printBinaryTreeBorder(BinaryTreeNode root) {
		printBinaryTreeLeftNodes(root);
		printBinaryTreeLeaves(root);
		printBinaryTreeRigthNodes(root);
	}

	public static void printBinaryTreeLeftNodes(BinaryTreeNode root) {
		if (root == null)
			return;
		// Important line make sure that the node is Not leaf!
		else if (root.leftChild == null)
			return;
		else {
			System.out.print(" " + root.value);
			printBinaryTreeLeftNodes(root.leftChild);
		}
	}

	public static void printBinaryTreeLeaves(BinaryTreeNode root) {
		if (root == null)
			return;
		// check that if the node is leaf?
		else if (root.rigthChild == null && root.leftChild == null)
			System.out.print(" " + root.value);
		else {
			printBinaryTreeLeaves(root.leftChild);
			printBinaryTreeLeaves(root.rigthChild);
			return;
		}
	}

	public static void printBinaryTreeRigthNodes(BinaryTreeNode root) {
		if (root == null)
			return;

		int depth = AlgorithmicQuestions.binaryTreeDepth(root);
		for (int i = depth; i > 0; i--)
			printRigthChildofLevelKofBinaryTree(root, i);
	}

	public static void printRigthChildofLevelKofBinaryTree(BinaryTreeNode root,
			int depth) {
		if (root == null)
			return;
		if (depth == 0 && root.rigthChild != null)
			System.out.print(" " + root.value);
		else
			printRigthChildofLevelKofBinaryTree(root.rigthChild, depth - 1);
	}

	// Given a binary tree, print out the tree in level order (ie, from left to
	// right, level by level).
	// Output a newline after the end of each level.
	public static void printBinaryTreeLevelbyLevel(BinaryTreeNode root) {
		if (root == null)
			return;
		else {
			int depth = binaryTreeDepth(root);
			for (int i = 0; i < depth; i++) {
				printLevelKofBinaryTree(root, i);
				System.out.print("\n");
			}
		}
	}

	public static void printLevelKofBinaryTree(BinaryTreeNode root, int depth) {
		if (root == null)
			return;
		if (depth == 0)
			System.out.print(" " + root.value);
		else {
			printLevelKofBinaryTree(root.leftChild, depth - 1);
			printLevelKofBinaryTree(root.rigthChild, depth - 1);
		}
	}

	public static void printLevelKofBinaryTree(BinaryTreeNode root, int depth,
			boolean direction) {
		if (root == null)
			return;
		if (depth == 0)
			System.out.print(" " + root.value);
		else {
			if (direction) {
				printLevelKofBinaryTree(root.leftChild, depth - 1, direction);
				printLevelKofBinaryTree(root.rigthChild, depth - 1, direction);
			} else {
				printLevelKofBinaryTree(root.rigthChild, depth - 1, direction);
				printLevelKofBinaryTree(root.leftChild, depth - 1, direction);
			}
		}
	}

	// Given a binary tree, print out the tree in zig zag level order (ie, from
	// left to right, then right to
	// left for the next level and alternate between). Output a newline after
	// the end of each level.
	public static void printBinaryTreeZigzagly(BinaryTreeNode root) {

		if (root == null)
			return;
		else {
			int depth = binaryTreeDepth(root);
			for (int i = 0; i < depth; i++) {
				if (i % 2 == 0)
					printLevelKofBinaryTree(root, i, true);
				else
					printLevelKofBinaryTree(root, i, false);
				System.out.print("\n");
			}
		}

	}

	// Given a binary tree, print the elements in pre-order iteratively without
	// using recursion.
	public static void printBinaryTreePreOrderIteratively(BinaryTreeNode root) {
		if (root == null)
			return;
		Stack<BinaryTreeNode> nodeStack = new Stack<BinaryTreeNode>();
		BinaryTreeNode tmp;

		nodeStack.push(root);
		while (!nodeStack.isEmpty()) {
			tmp = nodeStack.pop();
			System.out.print(" " + tmp.value);
			// remember not to push Null element into Stack!
			if (tmp.rigthChild != null)
				nodeStack.push(tmp.rigthChild);
			if (tmp.leftChild != null)
				nodeStack.push(tmp.leftChild);
		}

		return;
	}

	// Given a binary tree, print the elements in post-order iteratively without
	// using recursion.
	public static void printBinaryTreePostOrderIteratively(BinaryTreeNode root) {
		if (root == null)
			return;

		Stack<BinaryTreeNode> tmp = new Stack<BinaryTreeNode>();
		Stack<BinaryTreeNode> res = new Stack<BinaryTreeNode>();

		tmp.push(root);
		BinaryTreeNode node = null;

		while (!tmp.isEmpty()) {
			node = tmp.pop();
			res.push(node);
			if (node.leftChild != null)
				tmp.push(node.leftChild);
			if (node.rigthChild != null)
				tmp.push(node.rigthChild);

		}

		while (!res.isEmpty())
			System.out.print(" " + res.pop().value);
	}

	// Given a binary tree, print the elements in in-order iteratively without
	// using recursion.
	public static void printBinaryTreeInOrderIteratively(BinaryTreeNode root) {
		if (root == null)
			return;

		Stack<BinaryTreeNode> res = new Stack<BinaryTreeNode>();
		Set<BinaryTreeNode> printed = new HashSet<BinaryTreeNode>();

		res.push(root);
		BinaryTreeNode node = null;

		while (!res.isEmpty()) {
			node = res.peek();

			if (node.leftChild != null) {
				if (!printed.contains(node.leftChild))
					res.push(node.leftChild);
				else {
					System.out.print(" " + node.value);
					printed.add(node);
					res.pop();
					if (node.rigthChild != null)
						res.push(node.rigthChild);
				}

			} else {
				System.out.print(" " + node.value);
				printed.add(node);
				res.pop();
				if (node.rigthChild != null)
					res.push(node.rigthChild);
			}
		}
	}

	// Given a binary tree, find the lowest common ancestor of two given nodes
	// in the tree. Each node contains a parent pointer which links to its
	// parent.
	public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root,
			BinaryTreeNode node1, BinaryTreeNode node2) {

		List<BinaryTreeNode> inOrderList = new LinkedList<BinaryTreeNode>();
		List<BinaryTreeNode> postOrderList = new LinkedList<BinaryTreeNode>();

		printBinarySearchTreeInOrderRecursively(root, inOrderList);
		printBinarySearchTreePostOrderRecursively(root, postOrderList);

		int node_1_index_in_inOrder, node_2_index_in_inOrder;

		node_1_index_in_inOrder = inOrderList.indexOf(node1);
		node_2_index_in_inOrder = inOrderList.indexOf(node2);

		int startIndex, endIndex;

		if (node_1_index_in_inOrder > node_2_index_in_inOrder) {
			startIndex = node_2_index_in_inOrder;
			endIndex = node_1_index_in_inOrder;
		} else {
			startIndex = node_1_index_in_inOrder;
			endIndex = node_2_index_in_inOrder;
		}

		List<BinaryTreeNode> subInList = inOrderList.subList(startIndex,
				endIndex + 1);

		// Get the element with maximum index in post order
		int max_index = 0;

		for (BinaryTreeNode node : subInList) {
			if (max_index < postOrderList.indexOf(node))
				max_index = postOrderList.indexOf(node);
		}

		return postOrderList.get(max_index);

	}

	public static BinaryTreeNode lowestCommonAncestorWithParrentPointer(
			BinaryTreeNode root, BinaryTreeNode node1, BinaryTreeNode node2) {

		BinaryTreeNode node = node1;
		List<BinaryTreeNode> lst = new LinkedList<BinaryTreeNode>();

		while (node != null) {
			lst.add(node);
			node = node.parrent;
		}

		// List contains parent of the first node
		node = node2;

		while (node != null) {
			if (lst.contains(node))
				return node;
			node = node.parrent;
		}

		return null;

	}

	// Merge two balanced BST into one Balanced BST
	public static BinaryTreeNode mergeBallancedBST(BinaryTreeNode root1,
			BinaryTreeNode root2) {

		BinaryTreeNode tmp = null;
		List<BinaryTreeNode> inOrderList1 = new LinkedList<BinaryTreeNode>();
		List<BinaryTreeNode> inOrderList2 = new LinkedList<BinaryTreeNode>();
		List<BinaryTreeNode> mergedList = null;

		printBinarySearchTreeInOrderRecursively(root1, inOrderList1);
		printBinarySearchTreeInOrderRecursively(root2, inOrderList2);

		mergedList = mergeLists(inOrderList1, inOrderList2);

		tmp = makeBSTfromSortedList(mergedList);

		return tmp;
	}

	public static BinaryTreeNode makeBSTfromSortedList(List<BinaryTreeNode> lst) {
		if (lst == null || lst.size() == 0)
			return null;
		else {
			int middle = lst.size() / 2;
			BinaryTreeNode tmp = lst.get(middle);
			tmp.leftChild = makeBSTfromSortedList(lst.subList(0, middle));
			tmp.rigthChild = makeBSTfromSortedList(lst.subList(middle + 1,
					lst.size()));

			return tmp;
		}
	}

	public static List<BinaryTreeNode> mergeLists(List<BinaryTreeNode> lst1,
			List<BinaryTreeNode> lst2) {

		List<BinaryTreeNode> mergedList = new LinkedList<BinaryTreeNode>();

		Iterator<BinaryTreeNode> lst1itr = lst1.iterator();
		Iterator<BinaryTreeNode> lst2itr = lst2.iterator();
		BinaryTreeNode node1, node2;
		node1 = lst1itr.next();
		node2 = lst2itr.next();

		while (lst1itr.hasNext() && lst2itr.hasNext()) {
			if (node1.value <= node2.value) {
				mergedList.add(node1);
				node1 = lst1itr.next();
			} else {
				mergedList.add(node2);
				node2 = lst2itr.next();

			}
		}

		while (lst1itr.hasNext())
			mergedList.add(lst1itr.next());

		while (lst2itr.hasNext())
			mergedList.add(lst2itr.next());

		return mergedList;

	}

	// Check if the BST contains two nodes which there sum is N
	public static boolean checkSumBST(BinaryTreeNode root, int sum) {
		if (root == null)
			return false;
		List<BinaryTreeNode> inOrderList = new LinkedList<BinaryTreeNode>();
		printBinarySearchTreeInOrderRecursively(root, inOrderList);

		ListIterator<BinaryTreeNode> start_it = inOrderList.listIterator();
		// Remember to initialize to the size of list
		ListIterator<BinaryTreeNode> end_it = inOrderList
				.listIterator(inOrderList.size());

		BinaryTreeNode node1, node2;
		node1 = start_it.next();
		node2 = end_it.previous();

		while (start_it != end_it) {

			if (node1.value + node2.value == sum) {
				System.out.println("elements " + node1.value + " and "
						+ node2.value + " are solutions!");
				return true;
			} else if (node1.value + node2.value > sum) {
				node2 = end_it.previous();
			} else {
				node1 = start_it.next();
			}

		}
		return false;
	}

	// Mirror Binary Tree
	public static BinaryTreeNode mirrorBinaryTree(BinaryTreeNode root) {
		if (root == null)
			return null;
		else {
			BinaryTreeNode mTree = new BinaryTreeNode();
			mTree.value = root.value;
			mTree.rigthChild = mirrorBinaryTree(root.leftChild);
			mTree.leftChild = mirrorBinaryTree(root.rigthChild);
			return mTree;
		}

	}

	// Given a binary tree, find the largest Binary Search Tree (BST), where
	// largest means BST with largest number of
	// nodes in it. The largest BST may or may not include all of its
	// descendants.
	public static BinaryTreeNode largestBST(BinaryTreeNode root) {
		if (root == null)
			return null;
		else {
			Count counter1 = new Count();
			Count counter2 = new Count();
			if (isBinarySearchTree(root, counter1) == true)
				return root;
			else {
				counter1.count = 0;
				counter2.count = 0;
				boolean rigth, left;
				rigth = isBinarySearchTree(root.rigthChild, counter1);
				left = isBinarySearchTree(root.rigthChild, counter1);
				if (rigth && left) {
					if (counter1.count > counter2.count)
						return root.rigthChild;
					else
						return root.leftChild;
				} else if (rigth && !left)
					return root.rigthChild;
				else if (!rigth && left)
					return root.leftChild;
				else
					return null;

			}
		}
	}

	// Convert a BST to a sorted circular doubly-linked list "IN_PLACE" . Think
	// of the left and right pointers as synonymous to the previous and next
	// pointers in a doubly-linked list. (do it in-place!)
	public static BinaryTreeNode convertBSTToCircularDoublyList(
			BinaryTreeNode root) {
		if (root == null)
			return null;

		BinaryTreeNode head = null;
		BinaryTreeNode left = convertBSTToCircularDoublyList(root.leftChild);
		BinaryTreeNode rigth = convertBSTToCircularDoublyList(root.rigthChild);

		if (left == null && rigth == null) {
			// Points to itself! Important
			root.leftChild = root;
			root.rigthChild = root;
			head = root;
			return head;

		} else if (left == null && rigth != null) {
			root.rigthChild = rigth;
			root.leftChild = rigth.leftChild;
			rigth.leftChild = root;
			root.leftChild.rigthChild = root;
			head = root;
			return head;

		} else if (left != null && rigth == null) {
			head = left;

			root.leftChild = left.leftChild;
			root.rigthChild = left;
			left.leftChild = root;
			root.leftChild.rigthChild = root;
			return head;

		}
		// Both are not NULL
		else {
			head = left;
			root.leftChild = left.leftChild;
			root.leftChild.rigthChild = root;

			root.rigthChild = rigth;

			left.leftChild = rigth.leftChild;
			root.rigthChild.leftChild = root;
			left.leftChild.rigthChild = left;
			return head;
		}
	}

	
}
