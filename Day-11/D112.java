// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Refernce Node:
// --------------
// class TreeNode {
//     Integer val;
//     TreeNode left, right;
    
//     TreeNode(Integer val) {
//         this.val = val;
//         this.left = this.right = null;
//     }
// }


// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]


import java.util.*;
class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}
class Pair{
    TreeNode node;
    int level;
    public Pair(TreeNode node,int level){
        this.node=node;
        this.level=level;
    }
}
class Solution{
    public static TreeNode build(int[] arr){
        int idx=0;
        TreeNode root;
        if(arr[idx]==-1) return null;
        else{
            root = new TreeNode(arr[idx]);
        }
        Queue<TreeNode>q = new LinkedList<>();
        q.add(root);
        idx++;
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(idx<arr.length){
                if(arr[idx]==-1) node.left=null;
                else{
                    node.left = new TreeNode(arr[idx]);
                    q.add(node.left);
                } 
            }
            idx++;
            if(idx<arr.length){
                if(arr[idx]==-1) node.right=null;
                else{
                    node.right = new TreeNode(arr[idx]);
                    q.add(node.right);
                }
            }
           idx++;
        }
        return root;
    }
    public static void rightsideview(TreeNode root){
        Queue<Pair>q = new LinkedList<>();
        q.add(new Pair(root,0));
        int level=0;
        int ans=root.val;
        while(!q.isEmpty()){
            Pair p = q.poll();
            if(p.level>level){
                System.out.print(ans+" ");
                level++;
            }
            ans=p.node.val;
            if(p.node.left!=null) q.add(new Pair(p.node.left,p.level+1));
            if(p.node.right!=null) q.add(new Pair(p.node.right,p.level+1));
        }
        System.out.print(ans);
    }
   
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] ip = sc.nextLine().split(" ");
        int[] arr = new int[ip.length];
        for(int i=0;i<ip.length;i++){
            arr[i]=Integer.parseInt(ip[i]);
        }
        TreeNode root = build(arr);
        rightsideview(root);
    }
}