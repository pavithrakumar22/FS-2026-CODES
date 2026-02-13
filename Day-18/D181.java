// You are a gardener designing a beautiful floral pathway in a vast botanical garden. 
// The garden is currently overgrown with plants, trees, and bushes arranged in a 
// complex branching structure, much like a tree. Your task is to carefully prune 
// and rearrange the plants to form a single-file walking path that visitors can 
// follow effortlessly.

// To accomplish this, you must flatten the garden’s layout into a linear sequence 
// while following these rules:
//     1. The garden path should maintain the same PlantNode structure, where the 
//        right branch connects to the next plant in the sequence, and the left branch 
//        is always trimmed (set to null).
       
//     2. The plants in the final garden path should follow the same arrangement 
//        as a pre-order traversal of the original garden layout. 

// Example 1:
// Input:
// 1 2 5 3 4 -1 6

// Output:
// 1 2 3 4 5 6

// Explanation:
// input structure:
//        1
//       / \
//      2   5
//     / \    \
//    3   4    6
   
// output structure:
// 	1
// 	 \
// 	  2
// 	   \
// 		3
// 		 \
// 		  4
// 		   \
// 			5
// 			 \
// 			  6
			  

import java.util.*;
class Node{
    int data;
    Node left,right;
    public Node(int data){
        this.data=data;
        left=right=null;
    }
}
class Solution{
    static Queue<Node>list;
    private static Node build(String[] input){
        int i=0,n=input.length;
        while(input[i].equals("-1")) i++;
        Node root = new Node(Integer.parseInt(input[i]));
        i++;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            if(i<n && !input[i].equals("-1")){
                node.left=new Node(Integer.parseInt(input[i]));
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
    private static void preorder(Node root){
        if(root==null || root.data==-1) return ;
        list.add(root);
        preorder(root.left);
        preorder(root.right);
    }
    private static Node solve(){
        if(list.isEmpty()) return null;
        Node root=list.poll();
        Node temp=root;
        while(!list.isEmpty()){
            Node node=list.poll();
            temp.right=node;
            temp=temp.right;
        }
        return root;
    }
    private static void printTree(Node root){
        if(root==null || root.data==-1) return ;
        System.out.print(root.data+" ");
        printTree(root.right);
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        list = new LinkedList<>();
        Node root = build(input);
        preorder(root);
        root=solve();
        printTree(root);
    }
}