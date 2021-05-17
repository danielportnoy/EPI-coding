package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class ReverseBits {
	
	private static long[] precomputedReverseBits = new long[1<<16];
	
	private static long precomputedReverseBits(long x) {
		long reversed = 0;
		for(int i=0,j=15;i<j;i++,j--) {
			long lsb = (x>>i)&1;
			long msb = (x>>j)&1;
			reversed|=(lsb<<j);
			reversed|=(msb<<i);
		}
		return reversed;
	}
	
	static {
		for(int i=0; i<precomputedReverseBits.length; i++) 
			precomputedReverseBits[i] = precomputedReverseBits(i);
	}
	
	@EpiTest(testDataFile = "reverse_bits.tsv")
	public static long reverseBits(long x) {
		// TODO - you fill in here.
		int mask = 0xFFFF;
		int mask_size = 16;

		long reserved = precomputedReverseBits[(int) (mask&x)]<<(3*mask_size) | 
				precomputedReverseBits[(int) (mask&(x>>mask_size))]<<(2*mask_size) |
				precomputedReverseBits[(int) (mask&(x>>(2*mask_size)))]<<(mask_size) | 
				precomputedReverseBits[(int) (mask&(x>>(3*mask_size)))];
		
		return reserved;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "ReverseBits.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
