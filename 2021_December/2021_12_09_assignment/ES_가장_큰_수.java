import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        // String[] arr = {"99", "5","9","44"};
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < numbers.length ; i++){
            list.add(String.valueOf(numbers[i]));
        }
        Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return ((a + b).compareTo(b + a));
			}
		});
        
        // 330
        // 303
        if(list.get(0).equals("0")) {
			return "0";
		}
        
        StringBuilder sb = new StringBuilder();
        for(int i = numbers.length-1; i > -1 ; i--){
            // System.out.println(list.get(i));  
            sb.append(list.get(i));
        }
        answer = sb.toString();
        return answer;
    }
}