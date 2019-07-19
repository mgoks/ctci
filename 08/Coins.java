import java.util.Arrays;

public class Coins {
	static int makeChange(int n) {
		int[] denoms = {25, 10, 5, 1};
		int[] memo = new int[n+1];
		return makeChange(n, denoms, 0, memo);
	}

	static int makeChange(int amount, int[] denoms, int index, int[] memo) {
		if (memo[amount] == 0) {
			if (index >= denoms.length - 1) memo[amount] = 1;
			else {
				int denomAmount = denoms[index];
				int ways = 0;
				for (int i = 0; i * denomAmount <= amount; i++) {
					int amountRemaining = amount - i * denomAmount;
					ways += makeChange(amountRemaining, denoms, index + 1, memo);
				}
				memo[amount] = ways;
			}
		}
		return memo[amount];
	}

	public static void main(String[] args) {
		int max = Integer.parseInt(args[0]);
		for (int n = 0; n < max; n++) {
			System.out.printf("n = %d, number of ways = %d\n", n, makeChange(n));
		}
	}
}