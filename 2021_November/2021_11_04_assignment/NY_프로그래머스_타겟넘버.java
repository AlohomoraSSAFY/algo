package date1107SUN;

public class 프로그래머스_타겟넘버 {
	static int answer;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        dfs(-1,0,target,numbers);
        return answer;
    }
    
    
    public static void dfs(int idx, int num, int target, int[] numbers){
        if(idx == (numbers.length)-1){
            if( num == target)
                answer++;
            return;
        }
        
        dfs(idx+1,num+numbers[idx+1], target, numbers);
        dfs(idx+1,num-numbers[idx+1], target,numbers);
        
    }
}
