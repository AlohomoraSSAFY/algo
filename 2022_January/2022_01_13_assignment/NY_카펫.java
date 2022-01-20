package date0120THU;

public class 프로그래머스_카펫 {
	public int[] solution(int brown, int yellow) {

		int r = (brown / 2) - 1;
		int c = 3;

		while (true) {
			if (r * c - brown == yellow)
				break;
			r--;
			c++;
		}

		int[] answer = { r, c };
		return answer;
	}
}
