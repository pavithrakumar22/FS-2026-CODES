// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3

import java.util.*;

class Node {
    int val;
    Node left, right;
    Node(int val) {
        this.val = val;
    }
}

class Pair {
    Node node;
    int level;
    Pair(Node node, int level) {
        this.node = node;
        this.level = level;
    }
}

class Solution {
    static int preIdx = 0;
    static Map<Integer, Integer> postIndex = new HashMap<>();

    private static Node build(int[] pre, int[] post, int l, int r) {
        if (l > r || preIdx >= pre.length) return null;
        Node root = new Node(pre[preIdx++]);
        if (l == r) return root;
        int leftRootVal = pre[preIdx];
        int idx = postIndex.get(leftRootVal);
        root.left = build(pre, post, l, idx);
        root.right = build(pre, post, idx + 1, r - 1);
        return root;
    }

    public static void printTree(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));

        int level = 0;
        List<Integer> temp = new ArrayList<>();

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.level > level) {
                res.add(temp);
                temp = new ArrayList<>();
                level = p.level;
            }
            temp.add(p.node.val);
            if (p.node.left != null)
                q.add(new Pair(p.node.left, p.level + 1));
            if (p.node.right != null)
                q.add(new Pair(p.node.right, p.level + 1));
        }
        res.add(temp);
        System.out.println(res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] preStr = sc.nextLine().split(" ");
        String[] postStr = sc.nextLine().split(" ");
        int n = preStr.length;
        int[] pre = new int[n];
        int[] post = new int[n];

        for (int i = 0; i < n; i++) {
            pre[i] = Integer.parseInt(preStr[i]);
            post[i] = Integer.parseInt(postStr[i]);
            postIndex.put(post[i], i);
        }
       Node root = build(pre, post, 0, n - 1);
        printTree(root);
    }

}