package date0721;

public class NY_가장_긴_팰린드롬 {

	//abcba  5
	//abcbb 3 
	public int solution(String s) {
		char[] arr = s.toCharArray();

		for (int i = s.length(); i > 1; i--) {

			for (int j = 0; i + j <= s.length(); j++) {
				boolean flag = true;

				for (int k = 0; k < i / 2; k++) {
					if (arr[j + k] != arr[j + i - k - 1]) {
						flag = false;
						break;
					}
				}

				if (flag)
					return i;
			}
		}

		return 1;
	}
}
