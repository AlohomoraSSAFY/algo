import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static boolean [][] map;
    static boolean [] dest;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        String tmp = br.readLine();
        map = new boolean[2][N];
        dest = new boolean[N];
        for(int i = 0; i < N; ++i)
        {
            boolean b = tmp.charAt(i) == '1';
            map[0][i] = b;
            map[1][i] = b;
        }
        tmp = br.readLine();
        for(int i = 0; i < N; ++i) dest[i] = tmp.charAt(i) == '1';

        for(int i = 0; i < 2; ++i) map[0][i] = !map[0][i]; // 0 1 누름

        int answer = Integer.MAX_VALUE;
        int cnt = 1;
        for(int t = 0; t < 2; ++t)
        {
            for(int i = 1; i < N-1; ++i)
            {
                if(map[t][i-1] != dest[i-1])
                {
                    press(t,i);
                    cnt++;
                }
            }
            if(map[t][N-2] != dest[N-2])
            {
                map[t][N-2] = !map[t][N-2];
                map[t][N-1] = !map[t][N-1];
                cnt++;
            }
            if(map[t][N-1] == dest[N-1]) answer = Math.min(answer,cnt);
            cnt = 0;
        }
        if(answer == Integer.MAX_VALUE) answer = -1;
        System.out.print(answer);
    }
    static void press(int idx, int num)
    {
        for(int i = num-1; i < num+2; ++i) map[idx][i] = !map[idx][i];
    }
}