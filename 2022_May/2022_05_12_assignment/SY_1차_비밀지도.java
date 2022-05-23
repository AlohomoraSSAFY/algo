package study0526;

public class SY_1차_비밀지도 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            String temp1 = Integer.toBinaryString(arr1[i]);
            String temp2 = Integer.toBinaryString(arr2[i]);
            String str1 = "";
            String str2 = "";
            String result = "";
            
            for (int j = 0; j < n - temp1.length(); j++) {
                str1 += "0";
            }
            str1 += temp1;
            
            for (int j = 0; j < n - temp2.length(); j++) {
                str2 += "0";
            }
            str2 += temp2;
            
            for (int j = 0; j < n; j++) {
                int a = str1.charAt(j) - '0';
                int b = str2.charAt(j) - '0';
                
                if (a == 0 && b == 0) {
                    result += " ";
                } else {
                    result += "#";
                }
            }
            
            answer[i] = result;
        }
        
        return answer;
    }
}
