package date1202THU;

public class 프로그래머스_소수찾기 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
class Solution {
    static int len;
    static int answer;
    static char[] selected;
    static boolean visited[];
    static boolean checked[];
    
    public int solution(String numbers) {
        checked = new boolean[10000000];
        
        len = numbers.length();
        answer =0;
        
        
        for(int i=1;i<=len;i++){
            selected = new char[i];
            visited = new boolean[len];
      
            permutation(0,i,numbers);
        }
        
        return answer;
    }
    
    public static void permutation(int count,int leng, String numbers){
        if(count == leng){
            String num = "";
            for(int i=0;i<leng;i++){
                num+=selected[i];
            }
            int cur = Integer.parseInt(num);
            if(isprimenum(cur))
                answer++;
            return;
        }
        
        for(int i=0;i<len;i++){
            if(!visited[i]){
                selected[count] = numbers.charAt(i);
                visited[i] = true;
                permutation(count+1, leng,numbers);
                visited[i] = false;
            }
        }
    }
    public static boolean isprimenum(int num){
        if(checked[num])
            return false;
        if(num == 0 || num ==1 )
            return false;
        for(int i=2;i<1000;i++){
            if( num != i && num % i ==0)
                return false;
        }
        checked[num] = true;
        return true;
    }
}