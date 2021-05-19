package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class EvenOddListMerge {
	@EpiTest(testDataFile = "even_odd_list_merge.tsv")

	public static ListNode<Integer> evenOddMerge(ListNode<Integer> L) {
		// TODO - you fill in here.
		if(L==null)
			return null;

		ListNode<Integer> firstOdd = L.next;
		ListNode<Integer> evenIter = L;
		ListNode<Integer> oddIter = firstOdd;

		while(oddIter!=null && oddIter.next!=null)
		{
			evenIter.next = oddIter.next;
			evenIter = oddIter.next;
			oddIter.next = evenIter.next;
			oddIter = evenIter.next;			
		}

		evenIter.next = firstOdd;

		return L;
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "EvenOddListMerge.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
