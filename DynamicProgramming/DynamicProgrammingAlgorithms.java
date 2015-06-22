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
} 
