import java.util.*;

//These classes are for linked list nodes and will be used in some of the functions bellow.
class SinglyNode {
	public int value;

	public SinglyNode next = null;

	SinglyNode() {
		value = (int) Math.random() * 1000;
	}
}

class DoublyNode {
	public int value;

	public DoublyNode next = null;
	public DoublyNode previous = null;

	DoublyNode() {
		value = (int) Math.random() * 1000;
	}
}

public class MiscellaneousAlgorithms {
        // Ugly numbers are numbers whose only prime factors are 2, 3 or 5. The sequence
	// 1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, â€¦ shows the first 11 ugly numbers. By convention,
	// 1 is included. Write a program to find and print the 150â€²th ugly number.
	public static Integer uglyNumber(int kth) {

		PriorityQueue<Integer> tmp = new PriorityQueue<Integer>();
		Integer element = 1;
		Set<Integer> uglyNumberSet = new TreeSet<Integer>();

		tmp.add(new Integer(1));
		uglyNumberSet.add(1);

		while (uglyNumberSet.size() < kth) {

			element = tmp.remove();
			uglyNumberSet.add(element);

			tmp.add(element * 2);
			tmp.add(element * 3);
			tmp.add(element * 5);
		}
		return element;
	}
	// Given an array of integers, every element appears three times except for
	// one. Find that single one.
	// Note: Your algorithm should have a linear runtime complexity. Could you
	// implement it without using
	// extra memory?
	// solution 1: using hash table time complexity O(n) space complexity
	// O(n)
	// solution 2: Sort at first then find element repetition is not 3. Time
	// complexity O(n2) space O(1)
	// solution 3: linear time complexity and space O(1).
	public static int retElement(int[] anArray) {

		int solution = Integer.MIN_VALUE;
		Map<Integer, Integer> table = new HashMap<Integer, Integer>();

		// Walk over the array for finding the number of occurrence of each
		// element
		for (int i : anArray) {
			if (table.containsKey(i))
				table.put(i, table.get(i) + 1);
			else
				table.put(i, 1);
		}

		for (Integer i : table.keySet())
			if (table.get(i) != 3)
				return i;

		return solution;
	}
	
	// Given numRows, generate the first numRows of Pascal's triangle.
	public static void pascalTriangle_Simple(int row) {
		int[][] matrix;
		matrix = new int[row + 1][];

		// initialize first row
		matrix[1] = new int[1 + 2];
		matrix[1][1] = 1;

		// fill in Pascal's triangle
		for (int i = 2; i <= row; i++) {
			matrix[i] = new int[i + 2];
			for (int j = 1; j < matrix[i].length - 1; j++)
				matrix[i][j] = matrix[i - 1][j - 1] + matrix[i - 1][j];
		}
		// print results
		for (int i = 1; i <= row; i++) {
			for (int j = 1; j < matrix[i].length - 1; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	// There is an array A[N] of N numbers. You have to compose an array
	// Output[N] such that Output[i] will be equal to multiplication of all
	// the elements of A[N] except A[i]. Solve it without division operator and
	// in O(n).
	public static int[] solveArray(int[] inputArray) {
		int[] left = new int[inputArray.length];
		int[] rigth = new int[inputArray.length];
		int[] sol = new int[inputArray.length];

		int res = 1;

		for (int i = 0; i < inputArray.length; i++) {
			left[i] = res;
			res = res * inputArray[i];
		}

		res = 1;

		for (int i = inputArray.length - 1; i >= 0; i--) {
			rigth[i] = res;
			res = res * inputArray[i];
		}

		for (int i = 0; i < inputArray.length; i++)
			sol[i] = left[i] * rigth[i];

		return sol;
	}
        // Given a singly linked list, find if there exist a loop.
	public static boolean isCircular(SinglyNode lst) {
		SinglyNode slowPointer = lst;
		SinglyNode fastPointer = null;
		if (lst.next != null)
			if (lst.next.next != null)
				fastPointer = lst.next.next;

		while (slowPointer != null && fastPointer != null) {
			if (slowPointer == fastPointer)
				return true;
			slowPointer = slowPointer.next;
			if (fastPointer.next != null)
				fastPointer = fastPointer.next.next;
			else
				return false;
		}
		return false;
	}
	// Find the intersection of two sorted arrays.
	public static List<Integer> intersectionOfTwoSortedArray(int[] a,
			int start_a, int end_a, int[] b, int start_b, int end_b) {

		// case 1: two arrays don't have any intersection
		if (b[start_b] > a[start_a] && b[start_b] > a[end_a])
			return null;
		else if (a[start_a] > b[start_b] && a[start_a] > b[end_b])
			return null;
		else {
			List<Integer> intersection = new ArrayList<Integer>();

			if ((end_a - start_a) > (end_b - start_b)) {
				for (int i : b)
					if (binarySearch(a, start_a, end_a, i))
						intersection.add(i);
			} else {
				for (int i : a)
					if (binarySearch(b, start_b, end_b, i))
						intersection.add(i);
			}

			return intersection;
		}
	}

	public static boolean binarySearch(int[] array, int start, int end, int key) {
		if (key > array[end] || key < array[start] || start > end)
			return false;
		else if (key == array[start])
			return true;
		else if (key == array[end])
			return true;
		else {
			int mid = start + (end - start) / 2;
			if (key == array[mid])
				return true;
			else
				return binarySearch(array, start, mid - 1, key)
						|| binarySearch(array, mid + 1, end, key);
		}
	}

	// efficient way which O(m+n)
	public static List<Integer> intersectionOfTwoSortedArray(int[] a,
			int start_a, int end_a, int[] b, int start_b, int end_b, String st) {

		int counter_a = start_a;
		int counter_b = start_b;
		List<Integer> intersection = new ArrayList<Integer>();

		while (counter_a <= end_a && counter_b <= end_b) {
			if (a[counter_a] < b[counter_b])
				counter_a++;
			else if (a[counter_a] > b[counter_b])
				counter_b++;
			else {
				intersection.add(a[counter_a]);
				counter_a++;
				counter_b++;
			}
		}
		return intersection;
	}

	// Given two sorted arrays A, B of size m and n respectively. Find the k-th
	// smallest element in the union of A and B. You can assume that there are
	// no duplicate elements.

	public static int kthSmallestElement(int[] a, int[] b, int k)
			throws Exception {

		if (k == 0)
			return Math.min(a[0], b[0]);

		if (k > (a.length + b.length))
			throw new Exception("Out of bound");

		int counter_a = 0;
		int counter_b = 0;

		List<Integer> tmp = new LinkedList<Integer>();

		while (counter_a < a.length && counter_b < b.length) {

			if (a[counter_a] < b[counter_b]) {
				tmp.add(a[counter_a]);
				counter_a++;
			}

			else {
				tmp.add(b[counter_b]);
				counter_b++;
			}
		}

		while (counter_a < a.length) {
			tmp.add(a[counter_a]);
			counter_a++;
		}

		while (counter_b < b.length) {
			tmp.add(b[counter_b]);
			counter_b++;
		}

		return tmp.get(k);
	}

}
