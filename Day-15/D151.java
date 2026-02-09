// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4



import java.util.*;
class Node{
    int data;
    Node left,right;
    public Node(int data){
        this.data=data;
        this.left=this.right=null;
    }
}

class Solution{
    private static Node build(int[] arr){
        int i=0,n=arr.length;
        Node root = new Node(arr[i++]);
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            if(i<n && arr[i]!=-1){
            node.left=new Node(arr[i]);
            q.add(node.left);
            }
            i++;
            if(i<n && arr[i]!=-1){
            node.right=new Node(arr[i]);
             q.add(node .right);
            }
            i++;
        }
        return root;
    }
    private static Node solve(Node root){
       if(root==null || root.left==null) return root;
       Node nr=solve(root.left);
       root.left.left=root.right;
       root.left.right=root;
       root.left=null;
       root.right=null;
       return nr;
    }
    private static void levelOrder(Node root){
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            System.out.print(node.data+" ");
            if(node.left!=null)
            q.add(node.left);
            if(node.right!=null)
            q.add(node.right);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] ip = sc.nextLine().split(" ");
        int n=ip.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++) arr[i]=Integer.parseInt(ip[i]);
        Node root=build(arr);
        root=solve(root);
        levelOrder(root);
    }
}