public class RecursiveAlgorithms {
  
  // All path in grid with size m, n
	public static void allPath(List<String> path, int down, int rigth) {
		// Consider that the initial condition is set for ZERO!
		if (down == 0) {
			String s = "";
			for (int i = 0; i < rigth; i++)
				s += " RIGTH ";
			path.add(s);
			return;
		} else if (rigth == 0) {
			String s = "";
			for (int i = 0; i < down; i++)
				s += " DOWN ";
			path.add(s);
			return;
		}
		List<String> tmp0 = new ArrayList<String>();
		List<String> tmp1 = new ArrayList<String>();

		allPath(tmp0, down - 1, rigth);
		allPath(tmp1, down, rigth - 1);
		// Important!
		int i = 0;
		for (String s : tmp0) {
			tmp0.set(i, s + " DOWN ");
			i++;
		}
		i = 0;
		for (String s : tmp1) {
			tmp1.set(i, s + " RIGTH ");
			i++;
		}
		path.addAll(tmp0);
		path.addAll(tmp1);
		return;
	}

	// String Permutation
	public static void permutation(char[] str, int start, int end,
			Set<String> permutationSet) {
		if (end == start) {
			permutationSet.add(new String(str));
			return;
		} else {
			for (int counter = start; counter <= end; counter++) {
				swap(str, start, counter);
				permutation(str, start + 1, end, permutationSet);
				swap(str, start, counter);
			}
		}
	}
	public static void swap(char[] str, int source, int dest) {
		char tmp = str[source];
		str[source] = str[dest];
		str[dest] = tmp;
		return;
	}
	// String Permutation Another Simple method using recursive
	public static List<String> printPermutation(String str) {
		List<String> permSet = new ArrayList<String>();
		if (str.length() == 1) {
			permSet.add(str);
			return permSet;
		} else {
			permSet = printPermutation(str.substring(0, str.length() - 1));
			List<String> permSetCopy = new ArrayList<String>();
			permSetCopy.addAll(permSet);
			permSet.clear();
			for (String strTmp : permSetCopy) {
				permSet.add(str.substring(str.length() - 1) + strTmp);
				for (int i = 1; i <= strTmp.length(); i++) {
					permSet.add(strTmp.substring(0, i)
							+ str.substring(str.length() - 1)
							+ strTmp.substring(i));
				}
			}
		}
		return permSet;
	}
	// Print all parenthesis
	public static void printAllParanthesis(int npairparan, List<String> parList) {
		if (npairparan == 0)
			return;
		if (npairparan == 1) {
			parList.add("()");
			return;
		} else {
			List<String> A = new ArrayList<String>();
			List<String> B = new ArrayList<String>();

			for (int i = 0; i < npairparan; i++) {
				printAllParanthesis(i, A);
				printAllParanthesis(npairparan - 1 - i, B);

				if (A.isEmpty() && !B.isEmpty()) {
					for (String s2 : B)
						parList.add("()" + s2);
				} else if (!A.isEmpty() && B.isEmpty()) {
					for (String s1 : A)
						parList.add("(" + s1 + ")");

				} else if (!A.isEmpty() && !B.isEmpty()) {
					for (String s1 : A)
						for (String s2 : B)
							parList.add("(" + s1 + ")" + s2);
				}
				A.clear();
				B.clear();
				A = null;
				B = null;
			}
		}
	}
	// Fibonachi Sequence
	public static int Fibonachi(int index) {
		if (index == 0)
			return 0;
		else if (index == 1) {
			return 1;
		} else {
			if (index % 2 == 1) {
				return Fibonachi(index / 2 + 1) ^ 2 + Fibonachi(index / 2) ^ 2;
			} else {
				return (Fibonachi(index / 2 + 1) + Fibonachi(index / 2 - 1))
						* Fibonachi(index / 2);
			}
		}
	}
	// Solve Hanoi Tower recursive solution
	// Try to solve this problem using ITERATIVE SOLUTION!
	public static void solveHanoi(Stack<Integer> src, Stack<Integer> intr,
			Stack<Integer> dest, int N) {
		if (N == 2) {
			intr.push(src.pop());
			dest.push(src.pop());
			dest.push(intr.pop());

		} else {
			solveHanoi(src, dest, intr, N - 1);
			dest.push(src.pop());
			solveHanoi(intr, src, dest, N - 1);
		}
		return;
	}
	// Recursive Solution for Gray Codes
	// Try to find ITERATIVE SOLUTION
	public static void grayCode(int n, List<String> codes) {
		if (n == 0)
			return;
		else if (n == 1) {
			codes.add(new String("0"));
			codes.add(new String("1"));
		} else {
			List<String> tmpCodes = new LinkedList<String>();
			grayCode(n - 1, tmpCodes);
			ListIterator<String> itstr = tmpCodes.listIterator();

			while (itstr.hasNext())
				codes.add("0" + itstr.next());

			while (itstr.hasPrevious())
				codes.add("1" + itstr.previous());

			return;
		}
	}
		// Write border of the matrix clockwise
	public static void writeMatrixBorder(int[][] matrix, int i, int j, int m,
			int n, List<Integer> spiralFormat) {

		if (i < 0 || j < 0 || m < 0 || n < 0)
			return;
		// single element
		else if (i == m && j == n) {
			spiralFormat.add(matrix[i][j]);
		}
		// row matrix
		else if (i == m) {
			for (int counter = j; counter <= n; counter++)
				spiralFormat.add(matrix[i][counter]);
		}
		// column matrix
		else if (j == n) {
			for (int counter = i; counter <= m; counter++)
				spiralFormat.add(matrix[counter][j]);
		} else {

			// Upper Row
			for (int counter = j; counter <= n; counter++)
				spiralFormat.add(matrix[i][counter]);

			// Rigthmost Column
			for (int counter = i + 1; counter < m; counter++)
				spiralFormat.add(matrix[counter][n]);

			// Lower Row
			for (int counter = n; counter >= j; counter--)
				spiralFormat.add(matrix[m][counter]);

			// Leftmost Column
			for (int counter = m - 1; counter > i; counter--)
				spiralFormat.add(matrix[counter][j]);
		}
		return;
	}
	// Recursive Solution
	public static void makeRecursivlyMatrixSpiral(int[][] matrix, int i, int j,
			int m, int n, List<Integer> spiralFormat) {

		if (i > m || j > n)
			return;
		else if (i == m && j == n) {
			spiralFormat.add(matrix[i][j]);
		}
		// row matrix
		else if (i == m) {
			for (int counter = j; counter <= n; counter++)
				spiralFormat.add(matrix[i][counter]);
		}
		// column matrix
		else if (j == n) {
			for (int counter = i; counter <= m; counter++)
				spiralFormat.add(matrix[counter][j]);
		} else {

			for (int counter = j; counter <= n; counter++)
				spiralFormat.add(matrix[i][counter]);

			for (int counter = i + 1; counter < m; counter++)
				spiralFormat.add(matrix[counter][n]);

			for (int counter = n; counter >= j; counter--)
				spiralFormat.add(matrix[m][counter]);

			for (int counter = m - 1; counter > i; counter--)
				spiralFormat.add(matrix[counter][j]);

			makeRecursivlyMatrixSpiral(matrix, ++i, ++j, --m, --n, spiralFormat);
		}
	}
	// Generate all available codes for input number based on table code
	public static void generateAllCodesRecursive(Integer number, int size,
			Set<String> codesSet, Map<Integer, String> table) {
		if (size <= 0)
			return;
		if (size == 1) {
			codesSet.add(table.get(Integer.parseInt(number.toString()
					.substring(0, 1))));
			return;
		} else if (size == 2) {
			codesSet.add(table.get(Integer.parseInt(number.toString()
					.substring(0, 1)))
					+ table.get(Integer.parseInt(number.toString().substring(1,
							2))));
			codesSet.add(table.get(Integer.parseInt(number.toString()
					.substring(0, 2))));
			return;
		} else {
			Set<String> lst1 = new HashSet<String>();
			Set<String> lst2 = new HashSet<String>();
			generateAllCodesRecursive(number, size - 1, lst1, table);
			generateAllCodesRecursive(number, size - 2, lst2, table);

			Set<String> listCode1 = new HashSet<String>();
			Set<String> listCode2 = new HashSet<String>();

			for (String s : lst1)
				listCode1.add(s
						+ table.get(Integer.parseInt(number.toString()
								.substring(size - 1, size))));

			for (String s : lst2)
				listCode2.add(s
						+ table.get(Integer.parseInt(number.toString()
								.substring(size - 2, size))));

			codesSet.addAll(listCode1);
			codesSet.addAll(listCode2);
		}
	}
	// Given a target number, and a series of candidate numbers, print out all
	// combinations, so that the sum of candidate
	// numbers equals to the target.
	// Here order is not important, so donâ€™t print the duplicated combination.
	// e.g. target is 7, candidate is 2,3,6,7
	// output should be 7 and 3+2+2 (but not print 2+3+2, 2+2+3)
	// TRY To SOLVE this problem Recursively!

	public static void generateAllSolution(Integer number,
			List<Integer> numberList, List<List<String>> solutionList) {

		List<Integer> limit = new LinkedList<Integer>();

		// Upper bound for coefficient of each number in the list of numbers
		for (Integer i : numberList)
			limit.add(number / i);

		Integer max = Collections.max(limit);
		int counter = 0;
		int sum = 0;

		int totalNumberofCombinations = (int) Math.pow(max + 1, limit.size());

		List<String> solutionVector = new LinkedList<String>();

		for (int i = 0; i < totalNumberofCombinations; i++) {
			solutionVector = convertToMbase(i, max + 1, limit.size());
			if (isLessThanEqual(solutionVector, limit)) {
				counter = 0;
				sum = 0;
				for (String str : solutionVector) {
					sum += Integer.parseInt(str) * numberList.get(counter);
					counter++;
				}
				if (sum == number)
					solutionList.add(new LinkedList<String>(solutionVector));
			}
			solutionVector.clear();
		}
	}
	// Given a binary tree containing digits from 0-9 only, each root-to-leaf
	// path could represent a number.
	// An example is the root-to-leaf path 1->2->3 which represents the number
	// 123.
	// Find the total sum of all root-to-leaf numbers.
	// For example,
	// 1
	// / \
	// 2 3
	// The root-to-leaf path 1->2 represents the number 12.
	// The root-to-leaf path 1->3 represents the number 13.
	// Return the sum = 12 + 13 = 25.
	public static void totalPathSum(BinaryTreeNode root, List<String> lst) {
		if (root == null)
			return;
		if (root.leftChild == null && root.rigthChild == null)
			lst.add(String.valueOf(root.value));
		else {

			List<String> left = new LinkedList<String>();
			totalPathSum(root.leftChild, left);

			List<String> rigth = new LinkedList<String>();
			totalPathSum(root.rigthChild, rigth);

			for (String str : left)
				lst.add(root.value + str);

			for (String str : rigth)
				lst.add(root.value + str);
		}
	}
	public static int totalPathSum(BinaryTreeNode root) {
		List<String> numbers = new LinkedList<String>();
		totalPathSum(root, numbers);
		int sum = 0;
		for (String str : numbers)
			sum += Integer.parseInt(str);

		return sum;
	}
}
