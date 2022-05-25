package study0526;

public class SY_1차_다트_게임 {
    public int solution(String dartResult) {
        String temp = "";
        int[] array = new int[3];
        int idx = 0;
        for (int i = 0; i < dartResult.length(); i++) {
            switch(dartResult.charAt(i)) {
                case 'S':
                    array[idx++] = Integer.parseInt(temp);
                    temp = "";
                    break;
                case 'D':
                    array[idx++] = (int)Math.pow((double)(Integer.parseInt(temp)), 2);
                    temp = "";
                    break;
                case 'T':
                    array[idx++] = (int)Math.pow((double)(Integer.parseInt(temp)), 3);
                    temp = "";
                    break;
                case '*':
                    if (idx > 1) array[idx-2] *= 2;
                    array[idx-1] *= 2;
                    break;
                case '#':
                    array[idx-1] *= -1;
                    break;
                default:
                    temp += dartResult.charAt(i);
                    break;
            }
        }
        
        int answer = 0;
        for (int i = 0; i < 3; i++) {
            answer += array[i];
        }
        return answer;
    }
}
