// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation: 
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100

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
    static int maxi,total;
    private static int sum(Node node){
        if(node==null || node.data==-1) return 0;
        return node.data+sum(node.left)+sum(node.right);
    }
    private static void solve(Node root){
        if(root==null || root.data==-1) return ;
        int left=root.data+sum(root.left);
        maxi=Math.max(maxi,left*(total-left));
        int right=root.data+sum(root.right);
        maxi=Math.max(maxi,right*(total-right));
        solve(root.left);
        solve(root.right);
    }
    private static Node buildTree(int[] arr){
        int i=0,n=arr.length;
        Node root = new Node(arr[i]);
        i++;
        Queue<Node>q = new LinkedList<>();trrr
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            total+=node.data;
            if(i<n && arr[i]!=-1){
                node.left=new Node(arr[i]);
                q.add(node.left);
            }
            i++;
            if(i<n && arr[i]!=-1){
                node.right= new Node(arr[i]);
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int n=input.length;
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(input[i]);
        }
        maxi=0;
        total=0;
        Node root=buildTree(arr);
        solve(root);
        System.out.println(maxi);
    }
}