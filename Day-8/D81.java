// You are participating in a futuristic space exploration mission where you must 
// navigate through a series of planets aligned in a straight line. The planets 
// are numbered from 0 to n-1, and you start your journey on planet 0.

// You are given a 0-indexed array planets, where each element planets[i] represents 
// the maximum number of planets you can move forward from planet i. In simpler terms, 
// standing on planet i, you can move to any planet from i+1 to i+planets[i], as 
// long as you don't exceed the last planet.

// Your goal is to reach the final planet (planet n-1) in the fewest number of moves 
// possible.

// It is guaranteed that a path to the final planet always exists.

// Return the minimum number of moves needed to reach planet n-1.

// Sample Input:
// -------------
// 5
// 2 3 1 0 4

// Sample Output:
// --------------
// 2

// Explanation:
// ------------
// - Move from planet 0 to planet 1.
// - Move from planet 1 to planet 4 (last planet).




import java.util.*;
class Solution{
    // public static int solve(int i,int n,int[] planets,int count){
    // if(i==n-1) return count;
    // if(i>=n) return Integer.MAX_VALUE;
    // int mini=Integer.MAX_VALUE;
    // for(int j=i+1;j<=i+planets[i];j++){
    //     mini=Math.min(mini,solve(j,n,planets,count+1));
    // }
    // return mini;
    // }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int[]planets = new int[n];
        for(int i=0;i<n;i++) planets[i]=sc.nextInt();
        // System.out.println(solve(0,n,planets,0));
        int[] dp = new int[n+1];
        for(int i=0;i<=n;i++){
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=0;
        for(int i=1;i<n;i++){
            for(int j=0;j<i;j++){
                if(j+planets[j]>=i && dp[j]!=Integer.MAX_VALUE) dp[i]=Math.min(dp[i],dp[j]+1);
            }
        }
        System.out.println(dp[n-1]);
    }
}