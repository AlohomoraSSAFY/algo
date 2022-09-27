package study0927;

import java.util.*;

public class SY_블록_이동하기 {
    
    int n;
    int[][] board;
    int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    int answer;
    
    class Node implements Comparable<Node> {
        int x1;
        int y1;
        int x2;
        int y2;
        int cnt;
        
        public Node(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.cnt - o.cnt;
        }
    }
    
    public int solution(int[][] board) {
        n = board.length;
        this.board = board;
        bfs();
        
        return answer;
    }
    
    private void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0, 1, 0));
        boolean[][][][] visited = new boolean[n][n][n][n];
        visited[0][0][0][1] = true;
        visited[0][1][0][0] = true;
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if ((cur.x1 == n-1 && cur.y1 == n-1 ) || (cur.x2 == n-1 && cur.y2 == n-1)) {
                answer = cur.cnt;
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + d[i][0];
                int ny1 = cur.y1 + d[i][1];
                int nx2 = cur.x2 + d[i][0];
                int ny2 = cur.y2 + d[i][1];
                
                if (nx1 < 0 || nx1 >= n || ny1 < 0 || ny1 >= n || nx2 < 0 || nx2 >= n || ny2 < 0 || ny2 >= n) continue;
                if (visited[nx1][ny1][nx2][ny2]) continue;
                if (board[nx1][ny1] == 1 || board[nx2][ny2] == 1) continue;
                
                pq.offer(new Node(nx1, ny1, nx2, ny2, cur.cnt + 1));
                visited[nx1][ny1][nx2][ny2] = true;
                visited[nx2][ny2][nx1][ny1] = true;
            }
            
            if (cur.x1 == cur.x2) { //가로 방향
                if (cur.x1 > 0 && board[cur.x1 - 1][cur.y1] == 0 && board[cur.x1 - 1][cur.y2] == 0) {
                    if (!visited[cur.x1 - 1][cur.y1][cur.x1][cur.y1]) {
                        pq.offer(new Node(cur.x1 - 1, cur.y1, cur.x1, cur.y1, cur.cnt + 1));
                        visited[cur.x1 - 1][cur.y1][cur.x1][cur.y1] = true;
                        visited[cur.x1][cur.y1][cur.x1 - 1][cur.y1] = true;
                    }
                    if (!visited[cur.x1 - 1][cur.y2][cur.x1][cur.y2]) {
                        pq.offer(new Node(cur.x1 - 1, cur.y2, cur.x1, cur.y2, cur.cnt + 1));
                        visited[cur.x1 - 1][cur.y2][cur.x1][cur.y2] = true;
                        visited[cur.x1][cur.y2][cur.x1 - 1][cur.y2] = true;
                    }
                }
                if (cur.x1 < n-1 && board[cur.x1 + 1][cur.y1] == 0 && board[cur.x1 + 1][cur.y2] == 0) {
                    if (!visited[cur.x1 + 1][cur.y1][cur.x1][cur.y1]) {
                        pq.offer(new Node(cur.x1 + 1, cur.y1, cur.x1, cur.y1, cur.cnt + 1));
                        visited[cur.x1 + 1][cur.y1][cur.x1][cur.y1] = true;
                        visited[cur.x1][cur.y1][cur.x1 + 1][cur.y1] = true;
                    }
                    if (!visited[cur.x1 + 1][cur.y2][cur.x1][cur.y2]) {
                        pq.offer(new Node(cur.x1 + 1, cur.y2, cur.x1, cur.y2, cur.cnt + 1));
                        visited[cur.x1 + 1][cur.y2][cur.x1][cur.y2] = true;
                        visited[cur.x1][cur.y2][cur.x1 + 1][cur.y2] = true;
                    }
                }
            } else { //세로 방향
                if (cur.y1 > 0 && board[cur.x1][cur.y1 - 1] == 0 && board[cur.x2][cur.y1 - 1] == 0) {
                    if (!visited[cur.x1][cur.y1 - 1][cur.x1][cur.y1]) {
                        pq.offer(new Node(cur.x1, cur.y1 - 1, cur.x1, cur.y1, cur.cnt + 1));
                        visited[cur.x1][cur.y1 - 1][cur.x1][cur.y1] = true;
                        visited[cur.x1][cur.y1][cur.x1][cur.y1 - 1] = true;
                    }
                    if (!visited[cur.x2][cur.y1 - 1][cur.x2][cur.y1]) {
                        pq.offer(new Node(cur.x2, cur.y1 - 1, cur.x2, cur.y1, cur.cnt + 1));
                        visited[cur.x2][cur.y1 - 1][cur.x2][cur.y1] = true;
                        visited[cur.x2][cur.y1][cur.x2][cur.y1 - 1] = true;
                    }
                }
                if (cur.y1 < n-1 && board[cur.x1][cur.y1 + 1] == 0 && board[cur.x2][cur.y1 + 1] == 0) {
                    if (!visited[cur.x1][cur.y1][cur.x1][cur.y1 + 1]) {
                        pq.offer(new Node(cur.x1, cur.y1, cur.x1, cur.y1 + 1, cur.cnt + 1));
                        visited[cur.x1][cur.y1][cur.x1][cur.y1 + 1] = true;
                        visited[cur.x1][cur.y1 + 1][cur.x1][cur.y1] = true;
                    }
                    if (!visited[cur.x2][cur.y1][cur.x2][cur.y1 + 1]) {
                        pq.offer(new Node(cur.x2, cur.y1, cur.x2, cur.y1 + 1, cur.cnt + 1));
                        visited[cur.x2][cur.y1][cur.x2][cur.y1 + 1] = true;
                        visited[cur.x2][cur.y1 + 1][cur.x2][cur.y1] = true;
                    }
                }
            }
        }
    }
}
