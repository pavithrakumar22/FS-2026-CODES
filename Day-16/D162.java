// Imagine you're playing a fantasy video game where secret level codes unlock new 
// worlds. These codes are strings made up of letters, and a level code is only 
// considered valid if every shorter code formed by its leading characters has been
// discovered along the journey. In other words, a code is unlockable only when 
// all of its prefixes are also present in your collection.

// Given a list of strings representing the level codes you’ve collected, find the 
// longest valid level code such that every prefix of that code is in the list. If 
// there is more than one valid code of the same length, choose the one that comes 
// first in alphabetical order. If no such code exists, return an empty string.

// Input Format
// -------------
// Line1: Space separated codes (strings)
 
// Output Format
// --------------
// string 


// Example 1:
// ----------
// Input:
// m ma mag magi magic magici magicia magician magicw
// Output: 
// "magician"

// Explanation: The code "magician" is unlockable because every prefix—"m", "ma", 
// "mag", "magi", "magic", "magici", and "magicia"—is present in the list. Although 
// "magicw" appears too, it fails since its prefix "magica" is missing.


// Example 2:
// ----------
// Input:
// a banana app appl ap apply apple
// Output: 
// "apple"  

// Explanation: Both "appl fe" and "apply" have every prefix in the list, but "apple" 
// is comes first in alphabetical order.

// Example 3:
// ----------
// Input: 
// abc bc ab abcd
// Output: 
// ""

import java.util.*;
class Node{
    Node[] children;
    boolean eow;
    public Node(){
        this.children = new Node[26];
        this.eow=false;
    }
}
class Trie{
    Node root;
    public Trie(){
        this.root= new Node();
    }
    void insert(String word){
        Node node=root;
        for(char ch: word.toCharArray()){
            if(node.children[ch-'a']==null){
                node.children[ch-'a']= new Node();
            }
            node=node.children[ch-'a'];
        }
        node.eow=true;
    }
    boolean valid(String word){
        Node node = root;
        for(char ch: word.toCharArray()){
            if(node.children[ch-'a']==null || node.children[ch-'a'].eow==false) return false;
            node=node.children[ch-'a'];
        }
        return true;
    }
}

class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] input =sc.nextLine().split(" ");
        String ans="";
        Trie trie = new Trie();
        for(String word: input){
            trie.insert(word);
        }
        Arrays.sort(input,(a,b)->{
            if(a.length()==b.length()) return a.compareTo(b);
            return Integer.compare(b.length(),a.length());
        });
        for(String word: input){
            if(trie.valid(word)){
                    if(ans.length()<word.length()) ans=word;
            }
        }
        if(ans.length()>0) System.out.println(ans);
    }
}
