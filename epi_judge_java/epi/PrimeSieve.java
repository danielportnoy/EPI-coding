package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PrimeSieve {
	@EpiTest(testDataFile = "prime_sieve.tsv")
	// Given n, return all primes up to and including n.
	public static List<Integer> generatePrimes(int n) {
		// TODO - you fill in here.
		List<Integer> primes = new ArrayList<Integer>();

		if(n<2)
			return primes;

		List<Boolean> isPrimes = new ArrayList<Boolean>(Collections.nCopies(n+1, true));
		isPrimes.set(0,false);
		isPrimes.set(1,false);
		
		for (int p = 2; p <= n; p++) {
			if(isPrimes.get(p))
			{
				primes.add(p);
				for (int i = p; i <=n; i+=p) {
					isPrimes.set(i,false);
				}
			}
		}

		return primes;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "PrimeSieve.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
