package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
public class LookAndSay {
	@EpiTest(testDataFile = "look_and_say.tsv")

	public static String lookAndSay(int n) {
		// TODO - you fill in here.
		String s = "1";
		for (int i = 1; i < n; i++) {
			s = nextNumber(s);
		}
		return s;
	}

	private static String nextNumber(String s) {
		StringBuilder result=new StringBuilder();

		int count=1;
		char type = s.charAt(0);

		for (int i = 1; i < s.length(); i++) {
			if(s.charAt(i)==type)
				count++;
			else {
				result.append(count);
				result.append(type);
				type=s.charAt(i);
				count=1;
			}
		}

		result.append(count);
		result.append(type);

		return result.toString();
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "LookAndSay.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
