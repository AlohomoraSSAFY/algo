package net.acmicpc.march.week4;

import java.io.*;
import java.util.*;


public class BOJ1713 {
	
	static int N, T;
	
	static class Student implements Comparable<Student>{
		int idx;
		int time, likes;

		public Student(int idx, int time, int likes) {
			super();
			this.idx = idx;
			this.time = time;
			this.likes = likes;
		}
		
		@Override
		public int compareTo(Student o) {
			System.out.println("비교"+ this.toString()+" "+o.toString());
			if(this.likes == o.likes) {
				return this.time - o.time;
			}else {
				return this.likes - o.likes;
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idx;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Student other = (Student) obj;
			if (idx != other.idx)
				return false;
			return true;
		}

		

		@Override
		public String toString() {
			return "Student [idx=" + idx + ", time=" + time + ", likes=" + likes + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		T = Integer.parseInt(br.readLine());
		int[] candidate = new int[101];
		
		ArrayList<Student> list = new ArrayList<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < T; i++) {
			int val = Integer.parseInt(st.nextToken());
			if(list.contains(new Student(val, 0, 0))) { // 포함 여부 부터 확인
				for(Iterator<Student> iter = list.iterator(); iter.hasNext();) {
					Student s = iter.next();
					if(s.idx == val) {
						s.likes++;
						break;
					}
				}
			}else {
				if(list.size() == N) {
					Student min = new Student(0, 0, Integer.MAX_VALUE);
					for(Iterator<Student> iter = list.iterator(); iter.hasNext();) {
						Student s = iter.next();
						if(s.likes < min.likes) {
							min = s;
						}
					}
					candidate[min.idx] = 0;
					list.remove(min);
				}
				candidate[val] = 1;
				list.add(new Student(val, i, 1));
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 101; i++) {
			if(candidate[i]==1) {
				sb.append(i+" ");
			}
		}
		sb.append("\n");
		bw.write(sb.toString());
		
		bw.close();
		br.close();	
		
	}

}
/*

3
14
2 1 4 3 5 6 2 7 2 100 100 54 54 50


1번 2들어옴	2
2번 1 들어옴	2 1
3번 4 들어옴	2 1 4
4번 3 들어옴 2 out		1 4 3
5번 5 들어옴 1 out		4 3 5
6번 6 들어옴 4 out		3 5 6
7번 2 들어옴 3 out		5 6 2
8번 7 들어옴 5out		6 2 7
9번 2 들어옴		6 2(2) 7
10번 100들어옴	6out	7 2(2) 100
11번 100들어옴		7 2-2 100-2
12번들어옴 54		2-2 100-2 54
13번 들어옴		2-2 100-2 54-2
50번 들어옴		100 54 52

 */