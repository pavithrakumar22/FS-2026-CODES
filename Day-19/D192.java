// Mr Suresh is working with the plain text P, a list of words w[], 
// He is converting P into C [the cipher text], C is valid cipher of P, 
// if the following rules are followed:
// 	- The cipher-text C is a string ends with '$' character.
// 	- Every word, w[i] in w[], should be a substring of C, and 
// 	the substring should have $ at the end of it.

// Your task is to help Mr Suresh to find the shortest Cipher C,  
// and return its length.

// Input Format:
// -------------
// Single line of space separated words, w[].

// Output Format:
// --------------
// Print an integer result, the length of the shortest cipher.


// Sample Input-1:
// ---------------
// kmit it ngit

// Sample Output-1:
// ----------------
// 10

// Explanation:
// ------------
// A valid cipher C is "kmit$ngit$".
// w[0] = "kmit", the substring of C, and the '$' is the end character after "kmit"
// w[1] = "it", the substring of C, and the '$' is the end character after "it"
// w[2] = "ngit", the substring of C, and the '$' is the end character after "ngit"


// Sample Input-2:
// ---------------
// ace

// Sample Output-2:
// ----------------
// 4

// Explanation:
// ------------
// A valid cipher C is "ace$".
// w[0] = "ace", the substring of C, and the '$' is the end character after "ace"


import java.util.*;
class Node{
    Node[] children;
    boolean eow;
    public Node(){
        this.children= new Node[26];
        eow=false;
    }
}
class Trie{
    Node root;
    public Trie(){
        root=new Node();
    }
    int build(String str){
        if(search(str)) return 0;
        int i=str.length()-1;
        Node node=root;
        for(i=str.length()-1;i>=0;i--){
            if(node.children[str.charAt(i)-'a']==null) node.children[str.charAt(i)-'a']= new Node();
            node=node.children[str.charAt(i)-'a'];
        }
        node.eow=true;
        return str.length()+1;
    }
    boolean search(String str){
        int i=str.length()-1;
        Node node=root;
        for(i=str.length()-1;i>=0;i--){
            if(node.children[str.charAt(i)-'a']==null) return false;
            node=node.children[str.charAt(i)-'a'];
        }
        return true;
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int ans=0;
        String[] input= sc.nextLine().split(" ");
        Set<String>set = new HashSet<>();
        for(String s: input) set.add(s);
        List<String>words = new ArrayList<>();
        for(String s: set){
            words.add(s);
        }
        Collections.sort(words,(a,b)->{
            return Integer.compare(b.length(),a.length());
        });
        Trie trie = new Trie();
        for(String str: words){
            ans+=trie.build(str);
        }
        System.out.println(ans);
    }
}