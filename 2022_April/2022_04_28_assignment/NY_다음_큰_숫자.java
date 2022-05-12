class Solution {
    public int solution(int n) {
        int answer = 0;
        String str = Integer.toBinaryString(n);
        int count =0;
        for(int i=0;i<str.length();i++){
            if(str.charAt(i) == '1'){
                count++;
            }
        }
        int num = n+1;
        while(true){
            String str2 = Integer.toBinaryString(num);
              int cnt =0;
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i) == '1'){
                cnt++;
            }
        }
            if(cnt == count){
                break;
            }
            else
                num++;
      
        }
        return num;
    }
}