class Solution {
    public int solution(String dartResult) {
            int answer = 0;
    int prev =Integer.MAX_VALUE;
        int idx =0;
    for(int i=0;i<dartResult.length();i++){
        if(dartResult.charAt(i)<'0' || dartResult.charAt(i)>'9')
            continue;
        int score =0;
        
        int c1 = dartResult.charAt(i)-'0';
        char c2 = dartResult.charAt(i+1);
        boolean ten = false;
        if(c2 == '0'){
            i++;
            c1=10;
            c2 = dartResult.charAt(i+1);
            
        }
        if(c2 == 'S')
            score = c1;
        else if(c2 == 'D')
            score = (int)Math.pow(c1,2);
        else if(c2 == 'T')
            score = (int)Math.pow(c1,3);
        
        if(i+2<dartResult.length() && (dartResult.charAt(i+2)  == '#' || dartResult.charAt(i+2)  == '*' )){
            char c3 = dartResult.charAt(i+2);
            if(c3 == '#'){
                score *= (-1);
            }else if(c3 == '*'){
                score *=2;
                if(prev != Integer.MAX_VALUE){ //첫 번째 기회에서 나온 스타상이 아닌 경우
                    answer += prev;
                }
            }
        }
        answer+=score;
        prev = score;
        System.out.println(score);
           
    }
    return answer;
    }
}