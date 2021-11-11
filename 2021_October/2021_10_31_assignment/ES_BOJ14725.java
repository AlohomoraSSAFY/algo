import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

// 개미굴
public class Main {

    static int N;

    static class TrieNode{
        Map<String, TrieNode> children = new TreeMap<>(); // children을 생성하기
        boolean terminal = false;
        public TrieNode() {}
    }

    static TrieNode root;

    static void insert(String[] key) {
        int len = key.length;
        TrieNode cur = root;
        for(int level = 1; level < len ; level++) {
            String c = key[level];
            if(!cur.children.containsKey(c)) {
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.terminal = true;
    }

    static void printTrie(TrieNode node, int level, StringBuilder sb){
        TrieNode cur = node;
        Map<String, TrieNode> child = cur.children;

        if(cur.terminal){
            return;
        }

        for(String str : child.keySet()){
            for(int c = 0; c < level; c++){
                System.out.print("--");
            }
            System.out.println(str);
            printTrie(cur.children.get(str), level+1, sb);
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        root = new TrieNode();
        for(int i = 0 ; i < N; i++){
            String[] data = br.readLine().split(" ");
            int K = Integer.parseInt(data[0]);
            insert(data);
        }

        StringBuilder sb = new StringBuilder("");
        printTrie(root, 0, sb);


        br.close();
        bw.close();
    }
}