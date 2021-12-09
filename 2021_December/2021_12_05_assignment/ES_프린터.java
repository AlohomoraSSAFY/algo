import java.util.*;

class Solution {
    class Task{
        int location;
        int priority;
        public Task(int location, int priority) {
            this.location = location;
            this.priority = priority;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Task> queue = new LinkedList<>();

        for(int i = 0; i < priorities.length; i++){
            queue.add(new Task(i, priorities[i]));
        }

        int pop = 0;
        while(!queue.isEmpty()){
            Task cur = queue.poll();
            boolean flag = false;
            for(Task tmp : queue){
                if(tmp.priority > cur.priority){
                    flag = true;
                    queue.add(cur);
                    break;
                }
            }
            
            if(!flag) {
                pop++;
                if(cur.location == location) {
                    answer = pop;
                    break;
                }

            }
        }
        return answer;
    }
}