import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static List<long[]> jewerly;
    static PriorityQueue<Long> pq;
    static long[] bag;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        jewerly = new ArrayList<>();
        pq = new PriorityQueue<>(Collections.reverseOrder());
        bag = new long[K];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            long m = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());
            jewerly.add(new long[]{m, k});
        }
        Collections.sort(jewerly, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[0], o2[0]);
            }
        });

//        for (long[] tmp : jewerly){
//            System.out.println(tmp[0]+" "+tmp[1]);
//        }

        for(int i = 0; i < K; i++){
            long c = Long.parseLong(br.readLine());
            bag[i] = c;
        }
        Arrays.sort(bag);

        long sum = 0;
        int jdx = 0;
        for (int i = 0; i < K; i++){
            long c = bag[i];
            for(int j = jdx; j < jewerly.size(); j++){
                long[] tmp = jewerly.get(j);
                if(tmp[0] > c){
                    break;
                }else {
                    pq.add(tmp[1]);
                }
                jdx++;
            }
//            for (Iterator<long[]> iter = jewerly.iterator(); iter.hasNext(); ){
//                long[] j = iter.next();
//                System.out.println(i+"번  "+j[0]+" "+j[1]);
//                if( j[0] > c) { //  무게 넘으면
//                    break;
//                }else { //
//                    iter.remove();
//                    pq.add(j[1]);
//                }
//            }

            long max = 0;
            if(!pq.isEmpty()){
                max = pq.poll();
            }
            //System.out.printf("%d %d\n", c, max);
            sum += max;
        }

        bw.write(sum+"\n");
        br.close();
        bw.close();
    }
}