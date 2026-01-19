// Birbal is creating a new binary code system BBC (Birbal Binary Code) as follows:

// I	f(I)
// -------
// 0	""
// 1	"0"
// 2	"1"
// 3	"00"
// 4	"01"
// 5	"10"
// 6	"11"
// 7	"000"

// You are given an integer value I, where I is positive number.
// Your task is to find BBC representation of  the given number I.

// Input Format:
// -------------
// An integer I

// Output Format:
// --------------
// Print the BBC representation of I.


// Sample Input-1:
// ---------------
// 23

// Sample Output-1:
// ----------------
// 1000


// Sample Input-2:
// ---------------
// 45

// Sample Output-2:
// ----------------
// 01110


import java.util.*;
class Solution{
    // public static String binary(int n){
    //     StringBuilder sb = new StringBuilder();
    //     while(n>0){
    //         int x=n%2;
    //         n=n/2;
    //         if(x==0) sb.append('0');
    //         else sb.append('1');
    //     }
    //     sb.reverse();
    //     return sb.toString();
    // }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        // String s=binary(n+1);
        String s = Integer.toBinaryString(n);
        System.out.println(s.substring(1));
    }
}