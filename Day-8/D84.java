// Write a program to construct a binary tree from level-order input, while treating -1 
// as a placeholder for missing nodes. The program reads input, constructs the tree, 
// and provides an in-order traversal to verify correctness.

// Input Format:
// ---------------
// Space separated integers, level order data (where -1 indiactes null node).

// Output Format:
// -----------------
// Print the in-order data of the tree.

// NODE REFERENCE
// --------------
// class Node {
//     int value;
//     Node left, right;

//     public Node(int value) {
//         this.value = value;
//         this.left = null;
//         this.right = null;
//     }
// }


// Sample Input:
// ----------------
// 1 2 3 -1 -1 4 5

// Sample Output:
// ----------------
// 2 1 4 3 5

// Explanation:
// --------------
//     1
//    / \
//   2   3
//      / \
//     4   5


// Sample Input:
// ----------------
// 1 2 3 4 5 6 7

// Sample Output:
// ----------------
// 4 2 5 1 6 3 7

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4  5 6  7


import java.util.*;
class Node {
    int data;
    Node left, right;
    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class Solution{
    static int[] arr;
    static void build(Node root){
        int i=1,n=arr.length;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            if(i<n){
                if(arr[i]==-1){
                    i++;
                }
                else{
                Node leftNode = new Node(arr[i]);
                i++;
                node.left=leftNode;
                q.add(leftNode);
                }
                }
            if(i<n){
                if(arr[i]==-1){
                    i++;
                }
                else{
                Node rightNode = new Node(arr[i]);
                node.right=rightNode;
                q.add(rightNode);
                i++;
                }
                }
        }
    }
    static void inOrder(Node root){
        if(root==null) return ;
        inOrder(root.left);
        System.out.print(root.data+" ");
        inOrder(root.right);
        }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input=sc.nextLine();
        String[] splitting = input.split(" ");
        int n=splitting.length;
        arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(splitting[i]);
        Node root = new Node(arr[0]);
        build(root);
        inOrder(root);
    }

}