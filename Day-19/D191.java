// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(-1))(6(5)(7))
// Output: [4, 2, 6, 3, -1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.


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
    private static Node build(String input,int start,int end){
        if(start > end) return null;
        int i=start;
        boolean negative=false;
        if(input.charAt(i)=='-'){
            negative=true;
            i++;
        }
        int num=0;
        while(i<=end && Character.isDigit(input.charAt(i))){
            num=num*10+(input.charAt(i)-'0');
            i++;
        }
        if(negative) num=-num;
        Node root=new Node(num);
        if(i<=end && input.charAt(i)=='('){
            int leftStart=i;
            int balanced=1;
            i++;
            while(i<=end && balanced!=0){
                if(input.charAt(i)=='(') balanced++;
                else if(input.charAt(i)==')') balanced--;
                i++;
            }
            root.left=build(input,leftStart+1,i-2);
        }
        if(i<=end && input.charAt(i)=='('){
            int rightStart=i;
            int balanced=1;
            i++;
            while(i<=end && balanced!=0){
                if(input.charAt(i)=='(') balanced++;
                else if(input.charAt(i)==')') balanced--;
                i++;
            }
            root.right=build(input,rightStart+1,i-2);
        }
        return root;
    }
    public static void printTree(Node root){
        if(root==null) return ;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node= q.poll();
            System.out.print(node.data+" ");
            if(node.left!=null) q.add(node.left);
            if(node.right!=null) q.add(node.right);
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Node root = build(input,0,input.length()-1);
        printTree(root);
    }
}