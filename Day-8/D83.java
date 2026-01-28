// You are developing an application for a garden management system where each tree 
// in the garden is represented as a binary tree structure. The system needs to 
// allow users to plant new trees in a systematic way, ensuring that each tree is 
// filled level by level.

// A gardener wants to:
//  - Plant trees based on user input.
//  - Ensure trees grow in a balanced way by filling nodes level by level.
//  - Inspect the garden layout by performing an in-order traversal, which helps 
//    analyze the natural arrangement of trees.

// Your task is to implement a program that:
//     - Accepts a list of N tree species (as integers).
//     - Builds a binary tree using level-order insertion.
//     - Displays the in-order traversal of the tree.

// Input Format:
// -------------
// - An integer N representing the number of tree plants.
// - A space-separated list of N integers representing tree species.

// Output Format:
// --------------
// A list of integers, in-order traversal of tree.


// Sample Input:
// -------------
// 7
// 1 2 3 4 5 6 7

// Sample Output:
// --------------
// 4 2 5 1 6 3 7


// Explanation:
// ------------
// The tree looks like this:

//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// The in order is : 4 2 5 1 6 3 7



// NODE Reference:

// class Node {
//     int data;
//     Node left, right;
    
//     public Node(int data) {
//         this.data = data;
//         left = right = null;
//     }
// }



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
                Node leftNode = new Node(arr[i]);
                i++;
                node.left=leftNode;
                q.add(leftNode);
            }
            if(i<n){
                Node rightNode = new Node(arr[i]);
                node.right=rightNode;
                q.add(rightNode);
                i++;
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
        int n=sc.nextInt();
        arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=sc.nextInt();
        Node root = new Node(arr[0]);
        build(root);
        inOrder(root);
    }
}