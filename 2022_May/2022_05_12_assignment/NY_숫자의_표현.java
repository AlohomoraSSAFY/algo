class Solution {
    public int solution(int n) {
        int answer = 0;
        for(int i=1;i<=n;i++){
            int cur =0;
            for(int j=i;j<=n;j++){
                cur+=j;
                if(cur==n){
                    answer++;
                    break;
                }
                else if(cur>n)
                    break;
            }
        }
        return answer;
    }
}