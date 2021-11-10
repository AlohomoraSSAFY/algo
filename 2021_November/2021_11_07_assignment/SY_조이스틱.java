package study1111;

public class SY_조이스틱 {
	public int solution(String name) {
        int answer = 0;
        char[] array = name.toCharArray();
        for (int i = 0; i < array.length; i++) {
            char c = array[i];
            int a = c - 'A';
            int b = 'Z' - c + 1;
            if (a < b) {
                answer += a;
            } else {
                answer += b;
            }
        }
        
        boolean check = true;
        int min = array.length - 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != 'A') {
                check = false;
                min = Math.min(min, array.length - i);
                break;
            }
        }
        
        if (check) min = 0;
        
        for (int i = array.length - 1; i >= 1; i--) {
            if (array[i] != 'A') {
                min = Math.min(min, i);
                break;
            }
        }
        
        int tempA = -1;
        int tempB = -1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] != 'A') {
                tempA = i;
                for (int j = i+1; j < array.length; j++) {
                    if (array[j] != 'A') {
                        tempB = j;
                        break;
                    }
                }
            }
            
            if (tempB != -1) {
                min = Math.min(min, tempA * 2 + array.length - tempB);
                tempB = -1;
            }
        }
        
        answer += min;
        return answer;
    }
}
