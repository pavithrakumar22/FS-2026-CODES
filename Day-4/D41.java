// Amogh is an Antiquarian, The person who collects antiques.
// He found a rear keyboard which has following keys,
// Keys are 'N', 'S', 'C' and 'P'

// 1st Key - 'N': Print one character 'N' on Console.
// 2nd Key - 'S': Select the whole Console.
// 3rd Key - 'C': Copy selected content to buffer.
// 4th Key - 'P': Print the buffer on Console, and append it after what has 
// already been printed.

// Now, your task is to find out maximum numbers of 'N's you can print
// after K keystrokes . 

// Input Format:
// -------------
// An integer K

// Output Format:
// --------------
// Print an integer, maximum numbers of 'N's you can print.


// Sample Input-1:
// -------------------
// 3

// Sample Output-1:
// -------------------- 
// 3

// Explanation: 
// ---------------
// We can print at most get 3 N's on console by pressing following key sequence:
// N, N, N



// Sample Input-2:
// -------------------
// 7

// Sample Output-2:
// ---------------------
// 9

// Explanation: 
// ---------------
// We can print at most get 9 N's on console by pressing following key sequence:
// N, N, N, S, C, P, P

// import java.util.*;

public class D41 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        
        System.out.println(maxNs(k));
        sc.close();
    }
    
    static int maxNs(int k) {
        // Base cases
        if (k <= 0) return 0;
        if (k <= 6) return k;  // For small k, just press N
        
        int max = k;  // Option 1: Just press N, k times
        
        // Option 2: Press N for j times, then Select-Copy-Paste
        for (int j = 1; j < k - 2; j++) {
            // After j keystrokes, we have maxNs(j) N's
            // Use 2 keystrokes for Select-Copy
            // Remaining keystrokes for Paste: k - j - 2
            int pastes = k - j - 2;
            int result = maxNs(j) * (pastes + 1);
            max = Math.max(max, result);
        }
        
        return max;
    }
}

