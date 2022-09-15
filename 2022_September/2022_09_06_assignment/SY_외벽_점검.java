package study0920;

public class SY_외벽_점검 {
    
    int n;
    int[] weak, dist, weakList, selected;
    boolean[] visited;
    boolean flag;
    int answer;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        this.weak = weak;
        this.dist = dist;
        
        weakList = new int[weak.length * 2 - 1];
        for (int i = 0; i < weak.length; i++) {
            weakList[i] = weak[i];
        }
        for (int i = 0; i < weak.length - 1; i++) {
            weakList[weak.length + i] = n + weak[i];
        }
        
        for (int i = 1; i <= dist.length; i++) {
            selected = new int[i];
            visited = new boolean[dist.length];
            permutation(0, i);
            if (flag) break;
        }
        
        if (flag) return answer;
        else return -1;
    }
    
    private void permutation(int cnt, int target) {
        if (cnt == target) {
            if (isPossible()) {
                flag = true;
                answer = target;
            }
            
            return;
        }
        
        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;
            
            visited[i] = true;
            selected[cnt] = dist[i];
            permutation(cnt + 1, target);
            visited[i] = false;
            
            if (flag) return;
        }
    }
    
    private boolean isPossible() {
        for (int i = 0; i < weak.length; i++) {
            boolean check = true;
            int start = i;
            here: for (int friend = 0; friend < selected.length; friend++) {
                for (int j = i; j < i + weak.length; j++) {
                    if (weakList[j] - weakList[start] > selected[friend]) {
                        friend++;
                        start = j;
                        
                        if (friend == selected.length) {
                            check = false;
                            break here;
                        }
                    }
                }
            }
            
            if (check) return true;
        }
        
        return false;
    }
}
