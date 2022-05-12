import java.util.*;

class Word implements Comparable<Word>{
    String head;
    String num;
   String tail;
    int order;
    
    public Word(String head,String num,String tail, int order){
        this.head =head;
        this.num = num;
        this.tail = tail;
        this.order =order;
    }
    
    public int compareTo(Word w){
        String thishead= this.head.toUpperCase();
        String whead = w.head.toUpperCase();
        if(whead.equals(thishead)){
            int thisnum = Integer.parseInt(this.num);
            int wnum = Integer.parseInt(w.num);
            if(thisnum == wnum){
                return this.order - w.order;
            }
            return thisnum - wnum;
        }
            return thishead.compareTo(whead);
    }
    
    
}
class Solution {
    public String[] solution(String[] files) {
   
        PriorityQueue<Word> pq = new PriorityQueue<>();
        for(int i=0;i<files.length;i++){
            String str = files[i];
            int idx =0;
            String HEAD = "";
            String NUMBER = "";
            String TAIL = "";
            //HEAD
            for(int j=0;j<str.length()-1;j++){
                HEAD+=str.charAt(j);
                if(str.charAt(j+1)<='9' && str.charAt(j+1)>='0'){ //다음 글자가 숫자면
                    idx = j+1;
                    break;   
                }
            }
            
            //NUMBER
            //최대 다섯 글자 
            for(int j=idx;j<idx+5;j++){
                  NUMBER+=str.charAt(j);
                if(j+1 < str.length() && !(str.charAt(j+1)>='0') || !(str.charAt(j+1)<='9')){ //다음 글자가 문자면
                    idx = j+1;
                    break;   
                }
                if(j==str.length()-1){//TAIL 없는 경우
                    idx= str.length();
                }
            }
            
            //TAIL
             for(int j=idx;j<str.length();j++){
                  TAIL+=str.charAt(j);
            }
            
            System.out.println(HEAD+" "+NUMBER+" "+TAIL);
            
            pq.add(new Word(HEAD, NUMBER,TAIL, i));
        }
        
             String[] answer =new String[files.length];
        int index = 0;
        while(!pq.isEmpty()){
            Word w = pq.poll();
            String temp = w.head+""+w.num+""+w.tail;
            answer[index++]=temp;
        }
        return answer;
    }
}