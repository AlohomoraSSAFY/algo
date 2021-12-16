import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
		
		Arrays.sort(people);
        int s = 0;

		for(int i = people.length-1; s <= i; i--) {
			if(people[i] + people[s] > limit){
                answer++;
            } else {
				answer++;
				s++;
			}
		}

		return answer;
    }
}