package strings.stringProblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class MostCommonWord {
	public String mostCommonWord(String paragraph, String[] banned) {

		String[] arr = paragraph.split("[\\s,]+");
		boolean isBanned = banned == null || banned.length == 0 ? true : false;
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			String val = arr[i].toLowerCase().trim().replaceAll("[^a-z]", "");
			map.put(val, map.getOrDefault(val, 0) + 1);
		}
		int max = 0;
		String result = "";
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			boolean isBannedWord = false;
			if (entry.getValue() > max) {
				if (!isBanned) {
					for (int i = 0; i < banned.length; i++) {
						if (banned[i].equals(entry.getKey())) {
							isBannedWord = true;
							break;
						}
					}
				}
				if (!isBannedWord) {
					max = entry.getValue();
					result = entry.getKey();
				}
			}
		}
		return result;
	}

	public String mostCommonWordLeetCode(String paragraph, String[] banned) {
		paragraph += ".";

		Set<String> banset = new HashSet();
		for (String word : banned)
			banset.add(word);
		Map<String, Integer> count = new HashMap();

		String ans = "";
		int ansfreq = 0;

		StringBuilder word = new StringBuilder();
		for (char c : paragraph.toCharArray()) {
			if (Character.isLetter(c)) {
				word.append(Character.toLowerCase(c));
			} else if (word.length() > 0) {
				String finalword = word.toString();
				if (!banset.contains(finalword)) {
					count.put(finalword, count.getOrDefault(finalword, 0) + 1);
					if (count.get(finalword) > ansfreq) {
						ans = finalword;
						ansfreq = count.get(finalword);
					}
				}
				word = new StringBuilder();
			}
		}

		return ans;
	}

	public static void main(String[] args) {
		String paragraph = "Bob hit a ball, the hit BALL flew far after it was hit.";
		String[] banned = { "hit" };
		MostCommonWord mcw = new MostCommonWord();
		System.out.println(mcw.mostCommonWord(paragraph, banned));
	}
}