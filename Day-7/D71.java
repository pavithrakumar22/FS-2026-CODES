// "Emphatic Pronunciation" of a given word is where we take the word and
// replicate some of the letter to emphasize their impact.

// Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
// We define emphatic pronunciation of a word, which is derived by replicating 
// a group (or single) of letters in the original word. 

// So that the replicated group is atleast 3 characters or more and 
// greater than or equal to size of original group. 
// For example Good -> Goood is an emphatic pronunciation,
// but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
// In the question you are given the "Emphatic pronunciation" word, 
// you have to findout how many words can legal result in the 
// "emphatic pronunciation" word.

// Input Format:
// -------------
// Line-1 -> A String contains a single word, Emphatic Pronunciation word
// Line-2 -> Space seperated word/s

// Output Format:
// --------------
// Print an integer as your result


// Sample Input-1:
// ---------------
// goood
// good goodd

// Sample Output-1:
// ----------------
// 1


// Sample Input-2:
// ---------------
// heeelllooo
// hello hi helo

// Sample Output-2:
// ----------------
// 2



import java.util.*;
class Solution{
    public static boolean valid(String word,String target){
        int m=word.length(),n=target.length();
        int i=0,j=0;
        while(i<m && j<n){
            char ch1=word.charAt(i);
            char ch2=target.charAt(j);
            if(ch1!=ch2) return false;
            int c1=0,c2=0;
            while(i<m && word.charAt(i)==ch1){
                i++;
                c1++;
            }
            while(j<n && target.charAt(j)==ch2){
                j++;
                c2++;
            }
            if(c1!=c2 && c2<3) return false;
            if(c1>c2) return false;
        }
        if(i==m && j==n) return true;
        return false;
    }
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String target=sc.nextLine();
        String input = sc.nextLine();
        String[] arr = input.split(" ");
        Map<Character,Integer>map = new HashMap<>();
        for(char ch: target.toCharArray()) map.put(ch,map.getOrDefault(ch,0)+1);
        int n=arr.length;
        int res=0;
        for(int i=0;i<n;i++){
            if(valid(arr[i],target)) res++;
        }
        System.out.println(res);
    }
}