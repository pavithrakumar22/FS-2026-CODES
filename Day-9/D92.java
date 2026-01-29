// You are given a crystal with an energy level n. Your goal is to discover all 
// the different ways this crystal could have been created by combining smaller shards.

// Each combination must:
// - Use only shards with energy values between 2 and n - 1.
// - Be represented as a list of shard values whose product equals n.
// - Use any number of shards (minimum 2), and the order is ascending order.

// Your task is to return all unique shard combinations that can multiply together 
// to recreate the original crystal.

// Example 1:
// ---------
// Input:
// 28

// Output:
// [[2, 14], [2, 2, 7], [4, 7]]

// Example 2:
// ----------
// Input:
// 23

// Output:
// []



// Constraints:
// - 1 <= n <= 10^4
// - Only shards with energy between 2 and n - 1 can be used.

import java.util.*;
class Solution{
    static List<List<Integer>>ans;
    //*******************Solution 1 ************************ */
    // public static void helper(int i,int n,int product,List<Integer>list){
    //     if(product>n) return ;
    //     if(n==product){
    //         ans.add(new ArrayList<>(list));
    //         return ;
    //     }
    //     for(int j=i;j<n;j++){
    //         list.add(j);
    //         helper(j,n,product*j,list);
    //         list.remove(list.size()-1);
    //     }
    // }
    // public static void solve(int n){
    //     List<Integer>list = new ArrayList<>();
    //     helper(2,n,1,list);
    // }
    //***************** Solution 2 ****************** */
    public static void solve(int i,int n,List<Integer>list){
        if(n==1){
            ans.add(new ArrayList<>(list));
            return ;
        }
        for(int j=i;j<=n;j++){
            if(n%j==0){
                list.add(j);
                solve(j,n/j,list);
                list.remove(list.size()-1);
            }
        }
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ans = new ArrayList<>();
        int n=sc.nextInt();
        List<Integer>list = new ArrayList<>();
        solve(2,n,list);
        ans.remove(ans.size()-1);
        System.out.println(ans);
    }
}