class Solution {
    public int solution(int n) {
        int answer = 0;
        String str = Integer.toString(n,3);
        String reverse ="";
        for(int i=str.length()-1;i>=0;i--){
            reverse+=str.charAt(i);
        }    
        int idx =0;
        for(int i=reverse.length()-1;i>=0;i--){
        answer+= Math.pow(3,idx++)*(reverse.charAt(i)-'0');
        
        }
        return answer;
    }
}