class Solution {
    
    static int[] A = {1, 2, 3, 4, 5};
    static int[] B = {2, 1, 2, 3, 2, 4, 2, 5};
    static int[] C = {3, 3, 1, 1, 2 ,2, 4, 4, 5, 5};
    int acnt=0, bcnt=0, ccnt =0;
    
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        for (int i = 0 ; i < answers.length ; i++ ){
            //A
            if(A[i%5]==answers[i]) acnt++;
            //B
            if(B[i%8]==answers[i]) bcnt++;
            //C
            if(C[i%10]==answers[i]) ccnt++;
        }
        // a 와 b 비교
        if( acnt > bcnt){
            if(acnt > ccnt) answer = new int[]{1};
            else if(acnt == ccnt) answer = new int[]{1,3};
            else answer = new int[]{3}; 
        }else if(acnt == bcnt){
            if(acnt > ccnt) answer = new int[]{1,2};
            else if(acnt == ccnt) answer = new int[]{1,2,3};
            else answer = new int[]{3};
        }else{ // acnt < bcnt
            if(bcnt > ccnt) answer = new int[]{2};
            else if(bcnt == ccnt) answer = new int[]{2,3};
            else answer = new int[]{3};
        }
        
        return answer;
    }
}