// Imagine you're the curator of an ancient manuscript archive. Each manuscript is 
// assigned a unique significance score, and the archive is arranged so that every 
// manuscript on the left has a lower score and every manuscript on the right has 
// a higher score, forming a special ordered display. Now, for an upcoming exhibition, 
// scholars have decided that only manuscripts with significance scores between 
// low and high (inclusive) are relevant. Your task is to update the archive by 
// removing any manuscripts whose scores fall outside the range [low, high]. 

// Importantly, while you remove some manuscripts, you must preserve the relative 
// order of the remaining ones—if a manuscript was originally displayed as a 
// descendant of another, that relationship should remain intact. It can be proven 
// that there is a unique way to update the archive.

// Return the main manuscript of the updated archive. Note that the main manuscript 
// (the root) may change depending on the given score boundaries.

// Input format:
// Line 1: space separated scores to build the manuscript archive
// Line 2: two space seperated integers, low and high.

// Output format:
// space separated scores of the updated archeive

// Example 1:
// input=
// 1 0 2
// 1 2
// output=
// 1 2

// Explanation:
// Initial archieve:
//       1
//      / \
//     0   2


// Updated archieve:
//     1
//      \
//       2
// After removing manuscripts scores below 1 (i.e. 0), only the manuscript with 1
// and its right manuscript 2 remain.

// Example 2:
// input=
// 3 0 4 2 1
// 1 3
// output=
// 3 2 1

// Explanation:
// Initial archieve:
//           3
//          / \
//         0   4
//          \
//           2
//          /
//         1

// Updated archieve:
//       3
//      /
//     2
//    /
//   1


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
    private static void build(int val,Node root){
        if(root.data>val){
            if(root.left==null){
                root.left= new Node(val);
                return ;
            }
            build(val,root.left);
        }
        else{
            if(root.right==null){
                root.right= new Node(val);
                return ;
            }
            build(val,root.right);
        }
    }
   private static Node solve(Node root,int low,int high){
       if(root==null) return root;
       if(root.data<low){
           return solve(root.right,low,high);
       }
       else if(root.data>high){
           return solve(root.left,low,high);
       }
       root.left=solve(root.left,low,high);
       root.right=solve(root.right,low,high);
       return root;
   }
    private static void printTree(Node root){
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
            System.out.print(node.data+" ");
            if(node.left!=null) q.add(node.left);
            if(node.right!=null) q.add(node.right);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input=sc.nextLine().split(" ");
        int n=input.length;
        int a=sc.nextInt();
        int b=sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++)arr[i]=Integer.parseInt(input[i]);
        Node root= new Node(arr[0]);
        for(int i=1;i<n;i++){
            build(arr[i],root);
        }
        root=solve(root,a,b);
        printTree(root);
    }
}