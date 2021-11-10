package study1107;

import java.util.*;

public class SY_기능개발 {
	public int[] solution(int[] progresses, int[] speeds) {
        int[] day = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            day[i] = (99 - progresses[i]) / speeds[i] + 1;
        }
        
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(day[0]);
        list.add(1);
        for (int i = 1; i < day.length; i++) {
            if (day[i] <= stack.peek()) {
                int size = list.size();
                list.set(size - 1, list.get(size - 1) + 1);
            } else {
                stack.push(day[i]);
                list.add(1);
            }
        }
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}
