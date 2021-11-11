import java.util.*;

class Solution {

    static int N;
    static int len, answer = 0;
    static int[] selected;
    static int[] arr;

    public static void dfs(int cnt, int sum){
        if(cnt==len){
            if(sum==N) answer++;
            return;
        }
        selected[cnt] = 0;
        dfs(cnt+1, sum+arr[cnt]);
        selected[cnt] = 1;
        dfs(cnt+1, sum-arr[cnt]);
    }

    public int solution(int[] numbers, int target) {
        N = target;
        len = numbers.length;
        selected = new int[len];
        arr = numbers;

        answer = 0;
        dfs(0, 0);

        return answer;
    }
}