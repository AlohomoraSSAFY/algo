package study1004;

import java.util.*;

public class SY_길_찾기_게임 {
    
    Node[] nArr;
    int preIdx, postIdx;
    int[][] answer;
    
    class Node implements Comparable<Node> {
        int x;
        int y;
        int num;
        Node left;
        Node right;
        
        public Node(int x, int y, int num, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public int compareTo(Node o) {
            if (o.y == this.y) {
                return this.x - o.x;
            }
            return o.y - this.y;
        }
    }
    
    public int[][] solution(int[][] nodeinfo) {
        nArr = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            nArr[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }
        
        Arrays.sort(nArr);
        
        for (int i = 1; i < nodeinfo.length; i++) {
            connect(nArr[0], nArr[i]);
        }
        
        answer = new int[2][nodeinfo.length];
        preOrder(nArr[0]);
        postOrder(nArr[0]);
        return answer;
    }
    
    private void connect(Node parent, Node child) {
        if (child.x < parent.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                connect(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                connect(parent.right, child);
            }
        }
    }
    
    private void preOrder(Node node) {
        answer[0][preIdx++] = node.num;
        if (node.left != null) preOrder(node.left);
        if (node.right != null) preOrder(node.right);
    }
    
    private void postOrder(Node node) {
        if (node.left != null) postOrder(node.left);
        if (node.right != null) postOrder(node.right);
        answer[1][postIdx++] = node.num;
    }
}
