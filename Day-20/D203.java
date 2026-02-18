// JVS Infra Pvt Ltd purchased a flatland of size M*N, and it is divided into 1*1 cells.
// They mave marked some cells with red colors indicated with 1.
// and other cells with blue color indicated with 0.

// They want build the walls in the form of rectangles by connecting four distinct 
// red colored cells on the flatland that forms an axis-aligned rectangle.
// And only the corners of the walls need to be red colored.

// Your task is to help, JVS Infra to count the number of rectangular walls
// can be built by connecting the red colored cells on the flatland.

// Input Format:
// -------------
// Line-1: Two space separated integers, M and N
// Next M lines: N space separated integers, either 0 or 1 only.

// Output Format:
// --------------
// Print an integer result.


// Sample Input-1:
// ---------------
// 3 4
// 1 0 1 0
// 1 1 1 1
// 0 1 1 1

// Sample Output-1:
// ----------------
// 4

// Explanation:
// -----------
// Given flatland is:
// 1 0 1 0
// 1 1 1 1
// 0 1 1 1
// Number of rectngular walls are: 4
// rectangle-1: by connecting 1's at (0, 0), (1, 0), (0, 2), (1, 2)
// rectangle-2: by connecting 1's at (1, 1), (1, 2), (2, 1), (2, 2)
// rectangle-3: by connecting 1's at (1, 1), (1, 3), (2, 1), (2, 3)
// rectangle-4: by connecting 1's at (1, 2), (2, 2), (1, 3), (2, 3)


// Sample Input-2:
// ---------------
// 4 5
// 1 0 1 0 1
// 0 0 0 1 0
// 0 1 1 0 1
// 1 0 1 0 0

// Sample Output-2:
// ----------------
// 2



import java.util.*;
class Solution{
    private static int solve(int[][] arr){
        int ans=0;
        int m=arr.length,n=arr[0].length;
        for(int row=0;row<m;row++){
            for(int col=0;col<n;col++){
                if(arr[row][col]==1){
                    for(int i=col+1;i<n;i++){
                        if(arr[row][i]==1){
                            for(int j=row+1;j<m;j++){
                                if(arr[j][col]==1 && arr[j][i]==1) ans++;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m,n;
        m=sc.nextInt();
        n=sc.nextInt();
        int[][] arr=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                arr[i][j]=sc.nextInt();
            }
        }
        System.out.println(solve(arr));
    }
}