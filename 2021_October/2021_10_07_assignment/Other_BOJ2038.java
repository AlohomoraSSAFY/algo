package TwoPointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
//	메모리 35068KB,	시간 196ms
public class Other_BOJ2038 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0);
        arr.add(1);
        // p : secondPointer, q : firstPointer
        int p = 1, q = 1;
        while(q < N){
            int fk = arr.get(p);
            int num_of_fk = arr.get(fk);
            // p 1, fk : 1, num_of_fk : 1, arr 2 : 2
            // p 2, fk : 2, num_of_fk : 2, arr 3 : 2
            // p 3, fk : 2, num_of_fk : 2, arr 4 : 3
            if (arr.get(p - num_of_fk + 1) == fk){
                arr.add(fk + 1);
            } else{
                arr.add(fk);
            }
            p++; // secondPointer 증가
            q += arr.get(p); // firsterPointer (sp가 가르키는 가중치만큼 증가)
        }
        System.out.println(p);
    }
}