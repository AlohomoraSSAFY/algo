package study1202;

public class SY_큰_수_만들기 {
	public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int len = number.length();
        int[] array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = number.charAt(i) - '0';
        }
        
        int c = 0;
        for (int i = 0; i < len-k; i++) {
            int max = -1;
            int index = -1;
            for (int j = c; j <= k + i; j++) {
                if (array[j] > max) {
                    max = array[j];
                    index = j;
                }
            }
            
            sb.append(max);
            c = index + 1;
        }

        return sb.toString();
    }
}
