class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        for(int i=left ; i<= right; i++){
            if(getcnt(i)%2==1){//홀수
                answer-=i;
            }
            else
                answer+=i;
        }
        
        return answer;
    }
    public static int getcnt(int num){
        int count =0;
        for(int i=1;i<=num;i++){
            if(num%i==0){
                count++;
            }
        }
        return count;
    }
}