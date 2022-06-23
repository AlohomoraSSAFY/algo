class Solution {
    public int solution(int n) {
        int N = n-1;
        int answer = 0;
        for(int i = 2; i < N+1; i++){
            if(N%i==0){
                answer = i;
                break;
            }
        }
        return answer;
    }
}