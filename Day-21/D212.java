// Imagine you're the chief curator at a renowned museum that houses a rare collection 
// of ancient artifacts. These artifacts are arranged in a special display that 
// follows a strict rule: any artifact positioned to the left of another has a lower 
// value, and any artifact on the right has a higher value. 

// Your task is to transform this display into what is known as a "Greater Artifact 
// Display." In this new arrangement, each artifact’s new value will be its original 
// value plus the sum of the values of all artifacts that are more valuable than it.

// Example 1:
// ----------
// input=
// 4 2 6 1 3 5 7
// output=
// 22 27 13 28 25 18 7

// Explanation:
// Input structure 
//        4
//       / \
//      2   6
//     / \ / \
//    1  3 5  7

// Output structure:
//         22
//       /   \
//      27   13
//     / \   / \
//    28 25 18  7

// Reverse updates:
// - Artifact 7: 7
// - Artifact 6: 6 + 7 = 13
// - Artifact 5: 5 + 13 = 18
// - Artifact 4: 4 + 18 = 22
// - Artifact 3: 3 + 22 = 25
// - Artifact 2: 2 + 25 = 27
// - Artifact 1: 1 + 27 = 28


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
    static int val=0;
    private static Node build(String[] input){
        int i=0,n=input.length;
        while(i<n && input[i].equals("-1")) i++;
        Node root = new Node(Integer.parseInt(input[i]));
        i++;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node = q.poll();
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
    private static void transform(Node root){
        if(root==null) return ;
        transform(root.right);
        root.data+=val;
        val=root.data;
        transform(root.left);
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
        String[] input = sc.nextLine().split(" ");
        Node root=build(input);
        transform(root);
        printTree(root);
    }
}