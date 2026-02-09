// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.

import java.util.*;
class Node{
    int val;
    Node left,right;
    public Node(int val){
        this.val=val;
        left=right=null;
    }
}
class Solution{
    private static Node LCA(int n1,int n2,Node root){
        if(root==null) return null;
        if(root.val==n1 || root.val==n2) return root;
        Node left=LCA(n1,n2,root.left);
        Node right=LCA(n1,n2,root.right);
        if(left!=null && right!=null) return root;
        else if(left==null && right!=null) return right;
        else if(right==null && left!=null) return left;
        return null;
    }
    private static Node build(String[] ip){
        Queue<Node>q = new LinkedList<>();
        int i=0,n=ip.length;
        Node root=new Node(Integer.parseInt(ip[i]));
        q.add(root);
        i++;
        while(!q.isEmpty()){
            Node node=q.poll();
            if(i< n && !ip[i].equals("-1")){
                node.left=new Node(Integer.parseInt(ip[i]));
                q.add(node.left);
            }
            i++;
            if(i<n && !ip[i].equals("-1")){
                node.right=new Node(Integer.parseInt(ip[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    private static int dist(Node root,int val){
        if(root==null) return -1;
        if(root.val==val) return 0;
        int left=dist(root.left,val);
        if(left!=-1) return left+1;
        int right=dist(root.right,val);
        if(right!=-1) return right+1;
        return -1;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] ip = sc.nextLine().split(" ");
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        Node root=build(ip);
        Node lca=LCA(n1,n2,root);
        System.out.println(dist(lca,n1)+dist(lca,n2));
    }
}