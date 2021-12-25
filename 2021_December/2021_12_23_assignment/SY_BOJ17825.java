package study1226;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SY_BOJ17825 {
	
	static int[] dice;
	static int[] order;
	static Node[] horse;
	static Node start;
	static int result;
	
	static class Node {
		int num;
		int score;
		boolean isEnd;
		boolean isExistHorse;
		Node nextBlue;
		Node nextRed;
		
		public Node(int num, int score) {
			this.num = num;
			this.score = score;
		}
		
		public Node getNode(int num) {
			Node temp = start;
			while(true) {
				if (temp == null) return null;
				if (temp.num == num) return temp;
				temp = temp.nextRed;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		dice = new int[10];
		order = new int[10];
		horse = new Node[4];
		start = new Node(0, 0);
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		
		init();
		permutation(0);
		
		System.out.println(result);
	}
	
	public static void init() {
		Node temp = start;
		for (int i = 1; i <= 20; i++) {
			temp.nextRed = new Node(i, i * 2);
			temp = temp.nextRed;
		}
		
		temp.nextRed = new Node(32, 0);
		temp = temp.nextRed;
		temp.isEnd = true;
		
		temp = start.getNode(5);
		temp.nextBlue = new Node(21, 13);
		temp = temp.nextBlue;
		temp.nextRed = new Node(22, 16);
		temp = temp.nextRed;
		temp.nextRed = new Node(23, 19);
		temp = temp.nextRed;
		temp.nextRed = new Node(24, 25);
		Node middle = temp.nextRed;
		
		temp = start.getNode(10);
		temp.nextBlue = new Node(25, 22);
		temp = temp.nextBlue;
		temp.nextRed = new Node(26, 24);
		temp = temp.nextRed;
		temp.nextRed = middle;
		
		temp = start.getNode(15);
		temp.nextBlue = new Node(27, 28);
		temp = temp.nextBlue;
		temp.nextRed = new Node(28, 27);
		temp = temp.nextRed;
		temp.nextRed = new Node(29, 26);
		temp = temp.nextRed;
		temp.nextRed = middle;
		
		temp = middle;
		temp.nextRed = new Node(30, 30);
		temp = temp.nextRed;
		temp.nextRed = new Node(31, 35);
		temp = temp.nextRed;
		temp.nextRed = start.getNode(20);
	}
	
	public static void permutation(int cnt) {
		if (cnt == 10) {
			game();
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			order[cnt] = i;
			permutation(cnt + 1);
		}
	}
	
	public static void game() {
		for (int i = 0; i < 4; i++) {
			horse[i] = start;
		}
		
		int score = 0;
		for (int i = 0; i < 10; i++) {
			Node loc = horse[order[i]];
			loc.isExistHorse = false;
			
			if (loc.isEnd) {
				continue;
			} else {
				int cnt = dice[i];
				Node move = loc;
				
				if (move.nextBlue != null) {
					move = loc.nextBlue;
					cnt--;
				}
				
				while (cnt-- > 0) {
					if (move.isEnd) break;
					move = move.nextRed;
				}
				
				if (!move.isEnd && move.isExistHorse) {
					score = 0;
					break;
				}
				
				score += move.score;
				horse[order[i]] = move;
				move.isExistHorse = true;
			}
		}
		
		for (int i = 0; i < 4; i++) {
			horse[i].isExistHorse = false;
		}
		
		result = Math.max(result, score);
	}

}
