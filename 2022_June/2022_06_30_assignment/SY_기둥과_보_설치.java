package study0707;

public class SY_기둥과_보_설치 {
    
    int n;
    boolean[][] pillar;
    boolean[][] beam;
    
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        pillar = new boolean[n+1][n+1];
        beam = new boolean[n+1][n+1];
        
        int cnt = 0;
        for (int i = 0; i < build_frame.length; i++) {
            int x = build_frame[i][0];
            int y = build_frame[i][1];
            int a = build_frame[i][2];
            int b = build_frame[i][3];
            
            if (a == 0) {
                if (b == 0) {
                    pillar[x][y] = false;
                    if (!checkDelete()) pillar[x][y] = true;
                    else cnt--;
                } else {
                    if (checkPillar(x, y)) {
                        pillar[x][y] = true;
                        cnt++;
                    }
                }
            } else {
                if (b == 0) {
                    beam[x][y] = false;
                    if (!checkDelete()) beam[x][y] = true;
                    else cnt--;
                } else {
                    if (checkBeam(x, y)) {
                        beam[x][y] = true;
                        cnt++;
                    }
                }
            }
        }
        
        int[][] answer = new int[cnt][3];
        int idx = 0;
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (pillar[i][j]) {
                    answer[idx][0] = i;
                    answer[idx++][1] = j;
                }
                if (beam[i][j]) {
                    answer[idx][0] = i;
                    answer[idx][1] = j;
                    answer[idx++][2] = 1;
                }
            }
        }
        return answer;
    }
    
    private boolean checkPillar(int x, int y) {
        if (y == 0) return true;
        if (beam[x][y] || (x > 0 && beam[x-1][y])) return true;
        if (y > 0 && pillar[x][y-1]) return true;
        return false;
    }
    
    private boolean checkBeam(int x, int y) {
        if (y > 0 && (pillar[x][y-1] || pillar[x+1][y-1])) return true;
        if (x > 0 && beam[x-1][y] && beam[x+1][y]) return true;
        return false;
    }
    
    private boolean checkDelete() {
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < n+1; j++) {
                if (pillar[i][j] && !checkPillar(i, j)) return false;
                if (beam[i][j] && !checkBeam(i, j)) return false;
            }
        }
        
        return true;
    }
}
