package date1010SUN;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;

public class BOJ2002 {
	static int n, answer;
	static String[] a, b;
	static boolean visited[];

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = 0;

		n = Integer.parseInt(br.readLine());
		a = new String[n];
		b = new String[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			a[i] = br.readLine();
		}

		for (int i = 0; i < n; i++) {
			b[i] = br.readLine();
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				
				if (!a[i].equals(b[j])) {
					if(!visited[j]) {
						answer++;
						visited[j]=true;
					}
				}
				else {
					visited[j] = true;
					break;
				}
			}

		}
		System.out.println(answer);
	}

}
