// Coach Arjun is training a cricket team and is experimenting with a new fitness 
// evaluation drill. He provided the fitness scores of N players in the team. As 
// part of the drill, he asked the team manager to keep track and perform these 
// two specific operations on the players' fitness scores:

// 1. bestFitness(start, end) - Report the maximum fitness score among the players 
//    whose jersey numbers lie between the positions start and end inclusive.
// 2. improveFitness(index, newScore) - Update the fitness score of the player at 
//    the specific index position with a new fitness score newScore.

// Your task is to efficiently handle these requests by using a Segment Tree data structure.

// Input Format:  
// -------------
// Line-1: Two integers N and Q, representing the number of players and the total 
//         number of queries respectively.
// Line-2: N space-separated integers representing the initial fitness scores of 
//         the players.
// The next Q lines: Each line contains three integers: 
//     - First integer (option) specifies the type of query:
//       - If option = 1, the next two integers (start, end) represent the range 
//         of jersey numbers (inclusive) for which to report the maximum fitness.
//       - If option = 2, the next two integers (index, newScore) represent the 
//         player's index to update and their new fitness score.

// Output Format:  
// --------------
// - Output an integer value for every bestFitness query, representing the maximum 
//   fitness score within the specified range.

// Sample Input:  
// -------------

// 8 5
// 1 2 13 4 25 16 17 28
// 1 2 6       
// 1 0 7        
// 2 2 18       
// 2 4 36       
// 1 2 7        


// Sample Output:  
// --------------
// 25
// 28
// 36



import java.util.*;
class SegmentTree{
    static int[] tree;
    static int n;
    public SegmentTree(int[] arr){
        this.n=arr.length;
        tree= new int[4*n];
        build(arr,0,0,n-1);
    }
    public static void build(int[] arr,int i,int start,int end){
        if(start==end){
            tree[i]=arr[start];
            return ;
        }
        int mid=(start+end)/2;
        build(arr,2*i+1,start,mid);
        build(arr,2*i+2,mid+1,end);
        tree[i]=Math.max(tree[2*i+1],tree[2*i+2]);
    }
    public static void update(int i,int idx,int val,int start,int end){
        if(start == end){
            tree[idx] = val;
            return ;
        }
        int mid=(start+end)/2;
        if(i<=mid){
            update(i,2*idx+1,val,start,mid);
        }
        else{
            update(i,2*idx+2,val,mid+1,end);
        }
        tree[idx]=Math.max(tree[2*idx+1],tree[2*idx+2]);
    }
    public static int query(int i,int start,int end,int l,int r){
        if(l>end || r<start) return Integer.MIN_VALUE;
        if(start>=l && end<=r) return tree[i];
        int mid=(start+end)/2;
        return Math.max(query(2*i+1,start,mid,l,r),query(2*i+2,mid+1,end,l,r));
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int q=sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int[][] queries= new int[q][3];
        for(int i=0;i<q;i++){
            queries[i][0]=sc.nextInt();
            queries[i][1]=sc.nextInt();
            queries[i][2]=sc.nextInt();
        }
        SegmentTree st = new SegmentTree(arr);
        for(int i=0;i<q;i++){
            if(queries[i][0]==1){
                System.out.println(st.query(0,0,n-1,queries[i][1],queries[i][2]));
            }
            else{
                st.update(queries[i][1],0,queries[i][2],0,n-1);
            }
        }
    }
}