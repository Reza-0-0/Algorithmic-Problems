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
}
