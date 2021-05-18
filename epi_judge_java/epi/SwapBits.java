package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class SwapBits {
	@EpiTest(testDataFile = "swap_bits.tsv")
	public static long swapBits(long x, int i, int j) {
		// TODO - you fill in here.
		long bit_i = (x>>>i)&1;
		long bit_j = (x>>>j)&1;
		
		if(bit_i!=bit_j) {
			long mask = (1L<<i) | (1L<<j);
			x^=mask;
		}

		return x;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "SwapBits.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
