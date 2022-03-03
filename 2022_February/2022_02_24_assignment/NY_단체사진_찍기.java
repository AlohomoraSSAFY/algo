package date0303;

public class NY_단체사진_찍기 {

    static char[] ch = {'A','C','F','J','M','N','R','T'};
    static int answer;
    public int solution(int n, String[] data) {
        answer = 0;
        char[] selected = new char[8];
        boolean[] visited = new boolean[8];
        per(0, selected, visited,n,data);
        return answer;
    }
    
    public static void per(int count, char[] selected, boolean[] visited,int n, String[] data){
        if(count == 8){
            if(possible(selected,n,data))
                answer++;
            return;
        }
        
        for(int i=0;i<8;i++){
            if(!visited[i]){
                selected[count] = ch[i];
                visited[i] = true;
                per(count+1, selected, visited,n,data);
                visited[i] = false;
            }
        }
    }
    public static boolean possible(char[] selected,int n, String[] data){
        //조건 모두 체크해서 하나라도 안 되면 false리턴
        for(int i=0;i<n;i++){
            String str = data[i];
            int aidx =0;
            int bidx = 0;
            for(int j=0;j<8;j++){
                if(str.charAt(0)==selected[j]){
                    aidx = j;
                }
                else if(str.charAt(2)==selected[j]){
                    bidx = j;
                }
            }
            
            char oper = str.charAt(3);
            int num = str.charAt(4)-'0';
            if(oper =='='){
                if(Math.abs(aidx-bidx) != num+1)
                    return false;
            }else if (oper=='<'){
                if(Math.abs(aidx-bidx)>=num+1)
                    return false;
            }else if(oper=='>'){
                if(Math.abs(aidx-bidx)<=num+1)
                    return false;
            }
        }
        return true;
    }
}
