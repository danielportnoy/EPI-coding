package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class EvenOddArray {

	public static void evenOdd(List<Integer> A) {
		// TODO - you fill in here.
		if(A==null || A.size()==1)
			return;
		
		int next_even = 0; 
		int next_odd = A.size()-1;
		
		while(next_even<next_odd) {
			if(A.get(next_even)%2==0)
				next_even++;
			else {
				int temp = A.get(next_even);
				A.set(next_even, A.get(next_odd));
				A.set(next_odd, temp);
				next_odd--;
			}
		}
	}
	@EpiTest(testDataFile = "even_odd_array.tsv")
	public static void evenOddWrapper(TimedExecutor executor, List<Integer> A)
			throws Exception {
		List<Integer> before = new ArrayList<>(A);
		executor.run(() -> evenOdd(A));

		boolean inOdd = false;
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i) % 2 == 0) {
				if (inOdd) {
					throw new TestFailure("Even elements appear in odd part");
				}
			} else {
				inOdd = true;
			}
		}
		List<Integer> after = new ArrayList<>(A);
		Collections.sort(before);
		Collections.sort(after);
		if (!before.equals(after)) {
			throw new TestFailure("Elements mismatch");
		}
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "EvenOddArray.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
