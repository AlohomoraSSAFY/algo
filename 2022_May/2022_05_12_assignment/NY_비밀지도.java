class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        
        String[] answer = new String[n];
        for(int i=0;i<n;i++){
            String cur = "";
            String str1 = Integer.toBinaryString(arr1[i]);
            String str2 = Integer.toBinaryString(arr2[i]);
            
            str1 = fillzero(str1,n);
            str2 = fillzero(str2,n);
            
            for(int j=0;j<n;j++){
                if(str1.charAt(j) == str2.charAt(j) &&'1' == str2.charAt(j)){
                    cur+="#";
                }else if(str1.charAt(j) == str2.charAt(j) &&'0' == str2.charAt(j)){
                    cur+=" ";
                }else{
                    cur+="#";
                }
            }
            answer[i] = cur;
        }
        return answer;
    }
    public static String fillzero(String str, int n){
        if(str.length()<n){
            while(str.length()<n){
                str= "0"+str;
            }
        }
        return str;
    }
}