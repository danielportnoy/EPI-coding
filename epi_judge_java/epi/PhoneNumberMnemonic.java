package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class PhoneNumberMnemonic {

	private static final String[] keypadMapping = 
		{"0", "1","ABC","DEF","GHI","JKL","MNO","PQRS","TUV","WXYZ"};

	@EpiTest(testDataFile = "phone_number_mnemonic.tsv")
	public static List<String> phoneMnemonic(String phoneNumber) {
		// TODO - you fill in here.
		char[] partialMnemonic = new char[phoneNumber.length()];
		List<String> mnemonics = new ArrayList<String>();
		phoneMnemonicRecursive(phoneNumber, 0, partialMnemonic, mnemonics);
		return mnemonics;
	}

	private static void phoneMnemonicRecursive(String phoneNumber, int digit, char[] partialMnemonic, List<String> mnemonics) {

		if(digit==phoneNumber.length()) {
			mnemonics.add(new String(partialMnemonic));
			return;
		}
		
		int key = phoneNumber.charAt(digit)-'0';
		String keypads = keypadMapping[key];

		for (int i = 0; i < keypads.length(); i++) {
			partialMnemonic[digit] = keypads.charAt(i);
			phoneMnemonicRecursive(phoneNumber,digit+1,partialMnemonic,mnemonics);
		}
	}

	@EpiTestComparator
	public static boolean comp(List<String> expected, List<String> result) {
		if (result == null) {
			return false;
		}
		Collections.sort(expected);
		Collections.sort(result);
		return expected.equals(result);
	}

	public static void main(String[] args) {
		System.exit(
				GenericTest
				.runFromAnnotations(args, "PhoneNumberMnemonic.java",
						new Object() {}.getClass().getEnclosingClass())
				.ordinal());
	}
}
