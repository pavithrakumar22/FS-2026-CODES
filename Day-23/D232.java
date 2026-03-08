// There are some pages in a website, each page links with atmost two other pages.
// Each page displays a number on it. The complete website is given as binary tree 
// using the level order insertion technique.

// You need to return the number of pages where the number in the page is equal to 
// the sum of the numbers of its descendants. A descendant refers to any page that 
// is linked but lower down the tree stucture of the website, no matter how many 
// levels lower.

// Input Format:
// -------------
// Single line of space separated integers, numbers displayed in web-pages as Tree.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 11 3 5 2 1

// Sample Output-1:
// ----------------
// 2


// Sample Input-2:
// ---------------
// 3 2 1 0 0

// Sample Output-2:
// ----------------
// 3

// Explanation:
// ------------
// For the pages diplaying the number 0: The sum of descendants is 0,
// since they have no descendant pages.


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
    static int ans;
    private static int solve(Node root){
        if(root==null) return 0;
        int left=solve(root.left);
        int right=solve(root.right);
        if(root.data==left+right) ans++;
        return left+right+root.data;
    }
    private static Node build(String[] arr){
        int i=0,n=arr.length;
        Node root = new Node(Integer.parseInt(arr[i]));
        i++;
        Queue<Node>q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            Node node=q.poll();
            if(i<n && !arr[i].equals("-1")){
                node.left= new Node(Integer.parseInt(arr[i]));
                q.add(node.left);
            }
            i++;
            if(i<n && !arr[i].equals("-1")){
                node.right= new Node(Integer.parseInt(arr[i]));
                q.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        ans=0;
        Node root= build(input);
        solve(root);
        System.out.println(ans);
    }
}