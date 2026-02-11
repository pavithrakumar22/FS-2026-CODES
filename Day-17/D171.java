// Imagine you are a librarian organizing books on vertical shelves in a grand 
// library. The books are currently scattered across a tree-like structure, where 
// each book (node) has a position determined by its shelf number (column) and 
// row number (level).

// Your task is to arrange the books on shelves so that:
// 1. Books are placed column by column from left to right.
// 2. Within the same column, books are arranged from top to bottom (i.e., by row).
// 3. If multiple books belong to the same shelf and row, they should be arranged 
// from left to right, just as they appear in the original scattered arrangement.

// Example 1:
// Input:
// 3 9 20 -1 -1 15 7
// Output: 
// [[9],[3,15],[20],[7]]

// Explanation:
//          3
//        /   \
//       9     20
//           /    \
//          15     7

// Shelf 1: [9]
// Shelf 2: [3, 15]
// Shelf 3: [20]
// Shelf 4: [7]


// Example 2:
// Input:
// 3 9 8 4 0 1 7
// Output: 
// [[4],[9],[3,0,1],[8],[7]]

// Explanation:
//           3
//        /     \
//       9       8
//     /   \   /   \
//    4     0 1     7

// Shelf 1: [4]
// Shelf 2: [9]
// Shelf 3: [3, 0, 1]
// Shelf 4: [8]
// Shelf 5: [7]

// Library Organization Rules:
// 1. Each column represents a shelf from left to right.
// 2. Books on the same shelf are arranged from top to bottom.
// 3. If books share the same position, they are arranged left to right in order 
// of appearance.




import java.util.*;
class Node{
    int data;
    Node left,right;
    public Node(int data){
        this.data=data;
        left=right=null;
    }
}

class Pair {
    Node node;
    int col;
    Pair(Node node, int col) {
        this.node = node;
        this.col = col;
    }
}
class Solution{
    private static Node build(String[] input){
        int i=0,n=input.length;
        if(input[i].equals("-1")) return null;
        Node root = new Node(Integer.parseInt(input[i]));
        i++;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            if(i<n && !input[i].equals("-1")){
                node.left= new Node(Integer.parseInt(input[i]));
                q.add(node.left);
            }
            i++;
            if(i<n && !input[i].equals("-1")){
                node.right= new Node(Integer.parseInt(input[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    private static List<List<Integer>> solve(Node root){
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair p=q.poll();
            Node node =p.node;
            int col =p.col;
            if (node == null) continue;
            map.computeIfAbsent(col, k -> new ArrayList<>()).add(node.data);
            if (node.left != null) q.add(new Pair(node.left, col-1));
            if (node.right != null) q.add(new Pair(node.right, col+1));
        }

        return new ArrayList<>(map.values());
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        Node root = build(input);
        List<List<Integer>>res=solve(root);
        System.out.println(res);
    }
}