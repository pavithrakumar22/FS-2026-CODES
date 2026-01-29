// You are a database integrity engineer working for a global cloud company. 
// Your team maintains a distributed database network, where each server either:
//     - Stores equivalent data to another server (serverX == serverY).
//     - Stores different data from another server (serverX != serverY).

// The transitive consistency rule must be followed:
//     - If A == B and B == C, then A == C must be true.
//     - If A == B and B != C, then A != C must be true.

// Your task is to analyze the given constraints and determine whether they 
// follow transitive consistency. If all relations are consistent, return true; 
// otherwise, return false

// Input Format:
// -------------
// Space separated strnigs, list of relations

// Output Format:
// --------------
// Print a boolean value, whether transitive law is obeyed or not.


// Sample Input-1:
// ---------------
// a==b c==d c!=e e==f

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// a==b b!=c c==a

// Sample Output-2:
// ----------------
// false

// Explanation:
// ------------
// {a, b} form one equivalence group.
// {c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
// However, b != c contradicts b == a and c == a.

// Sample Input-3:
// ---------------
// a==b b==c c!=d d!=e f==g g!=d

// Sample Output-3:
// ----------------
// true

import java.util.*;
class DSU{
    static int[] parent;
    static int[] rank;
    public DSU(){
        parent=new int[26];
        rank= new int[26];
        for(int i=0;i<26;i++) parent[i]=i;
        Arrays.fill(rank,0);
    }
    static int find(int x){
        if(parent[x]==x) return parent[x];
        return parent[x]=find(parent[x]);
    }
    static boolean union(int x,int y){
        int px=find(x);
        int py=find(y);
        if(px==py) return false;
        if(rank[px]>rank[py]){
            parent[py]=px;
        }
        else if(rank[py]>rank[px]){
            parent[px]=py;
        }
        else{
            rank[px]++;
            parent[py]=px;
        }
        return true;
        
    }
    
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] arr=input.split(" ");
        DSU dsu = new DSU();
        boolean res=true;
        for(int i=0;i<arr.length;i++){
            String s=arr[i];
            char x=s.charAt(0);
            char op=s.charAt(1);
            char y=s.charAt(3);
           if(op=='='){
               dsu.union(x-'a',y-'a');
           }
        }
        for(int i=0;i<arr.length;i++){
             String s=arr[i];
            char x=s.charAt(0);
            char op=s.charAt(1);
            char y=s.charAt(3);
            if(op=='!'){
                res=(dsu.find(x-'a')!=dsu.find(y-'a'));
            }
            if(res==false) break;
        }
        System.out.println(res);
    }
}