import java.util.*;

public class DynamicProgrammingAlgorithms {
  // Dynamic programming Knapsack Problem
	public static double knapSack(double[] w, double[] v, double maxWeigth) {

		double[][] maxValue = new double[w.length][(int) Math.ceil(maxWeigth) + 1];
		// Initialize the maxValue array!
		for (int weigth = 0; weigth < maxValue[0].length; weigth++)
			if (w[0] > weigth)
				maxValue[0][weigth] = 0;
			else
				maxValue[0][weigth] = v[0];

		for (int element = 0; element < w.length; element++)
			for (int weigth = 1; weigth < (int) Math.ceil(maxWeigth) + 1; weigth++) {
				if (w[element] > weigth)
					maxValue[element][weigth] = maxValue[element - 1][weigth];
				else
					maxValue[element][weigth] = Math.max(
							maxValue[element - 1][weigth],
							maxValue[element - 1][(int) (weigth - w[element])]
									+ v[element]);
			}
		return maxValue[w.length - 1][(int) Math.ceil(maxWeigth)];
	}
	
	// Dynamic programming approach O(n^2)
	public static void maximumContiguousSubsequence(double[] numbers) {

		int start_index = 0;
		int end_index = 0;
		double max_number = Double.MIN_VALUE;

		double[][] sum = new double[numbers.length][numbers.length];

		for (int i = 0; i < numbers.length; i++)
			for (int j = i; j < numbers.length; j++) {

				if (i == j)
					sum[i][j] = numbers[i];
				else if (i < j)
					sum[i][j] = sum[i][j - 1] + numbers[j];

				if (max_number < sum[i][j]) {
					start_index = i;
					end_index = j;
					max_number = sum[i][j];
				}
			}
		System.out.println("The maximum contiguous subsequesnt is : "
				+ max_number + " start from index :" + start_index
				+ " and end in index :" + end_index);
	}
	// Dynamic Programming Approach to Subset Sum Problem
	public static boolean subSetSumDynamicProgramming(List<Integer> numbers,
			int element, int sum) {

		boolean[][] sol = new boolean[element + 1][sum + 1];

		if (sum < 0)
			return false;

		else {

			// if sum==0 then empty set is the solution
			for (int i = 0; i <= element; i++)
				sol[i][0] = true;

			// if the set is empty then there is no solution for sum!=0 !
			for (int j = 1; j <= sum; j++)
				sol[0][j] = false;

			// else
			for (int i = 1; i <= element; i++)
				for (int j = 1; j <= sum; j++) {

					if (numbers.get(i - 1) > j)
						sol[i][j] = sol[i - 1][j];
					else if (numbers.get(i - 1) == j)
						sol[i][j] = true;
					else
						sol[i][j] = sol[i - 1][j - numbers.get(i - 1)]
								|| sol[i - 1][j];
				}
		}
		return sol[element][sum];
	}
} 
