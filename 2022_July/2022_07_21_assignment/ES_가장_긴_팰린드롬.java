import java.util.*;

class Solution
{
    public int solution(String s)
    {

        char[] ch = s.toCharArray();
        
        for(int i = s.length(); i > 1; i--) {
            for(int j = 0; j <= s.length() - i; j++) {
                boolean check = true;
                for(int k = 0; k < i/2; k++){
                    if(ch[j+k] != ch[j + i - k -1]){
                        check = false;
                        break;
                    }
                }
                if(check) {
                    return i;
                }
            }
        }

        return 1;
    }
}