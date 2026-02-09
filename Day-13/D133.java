// Imagine you are a secret agent tasked with sending encoded messages. 
// Each original message is a string of lowercase letters, and you can disguise 
// it by replacing selected, non-adjacent segments with the count of characters 
// in those segments. However, the encoding must follow strict rules: only 
// non-empty segments can be replaced, replacements cannot be adjacent, and any 
// numbers used must not have leading zeros.

// For instance, the message "substitution" can be encoded in various ways, such as:

// • "s10n" (keeping 's', replacing the next 10 characters with 10, and ending with 'n')  
// • "sub4u4" (keeping "sub", replacing 4 characters, then 'u', and replacing 4 more characters)  
// • "12" (replacing the entire message with its length)  
// • "su3i1u2on" (using a different pattern of replacements)  
// • "substitution" (leaving the message unaltered)

// Invalid encodings include:

// • "s55n" (because the replaced segments are adjacent)  
// • "s010n" (the number 010 has a leading zero)  
// • "s0ubstitution" (attempts to replace an empty segment)

// Your task is: given an original message and an encoded version, 
// determine if the encoded version is a valid secret code for the message.


// Sample Input-1: 
// ---------------
// internationalization
// i12iz4n
  
// Sample Output-1: 
// ----------------
// true  

// Explanation: 
// ------------
// "internationalization" can be encoded as "i12iz4n" by keeping 'i', 
// replacing 12 letters, keeping "iz", replacing 4 letters, and then ending with 'n'.


// Sample Input-2: 
// ---------------
// apple
// a2e
  
// Sample Output-2:
// ----------------
// false  

// Explanation: 
// ------------
// The encoding "a2e" does not correctly represent "apple".

// Time Complexity: O(n) where n=max(len(word),len(abbr))
// Auxiliary Space:  O(1).


import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s1=sc.nextLine();
        String s2=sc.nextLine();
        int i=0,j=0,n1=s1.length(),n2=s2.length();
        boolean flag=true;
        while(i<n1 && j<n2){
            if(Character.isLetter(s2.charAt(j))){
                if(s1.charAt(i)!=s2.charAt(j)){
                    flag=false;
                    break;
                }
            }
            else{
                int x=0;
                int mul=1;
                while(j<n2 && !Character.isLetter(s2.charAt(j))){
                    x=x*mul+(s2.charAt(j)-'0');
                    mul*=10;
                    if(x==0){
                        flag=false;
                        break;
                    }
                    j++;
                }
                i=i+x;
                if(i==n1 && j==n2) break;
               if(i<n1 && s1.charAt(i)!=s2.charAt(j)){
                    flag=false;
                    break;
                }
            }
            i++;
            j++;
        }
        if(!flag){
            System.out.println(false);
        }
        else{
            if(i==n1 && j==n2){
                System.out.println(true);
            }
            else{
                System.out.println(false);
            }
        }
    }
}