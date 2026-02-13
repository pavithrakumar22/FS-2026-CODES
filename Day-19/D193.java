// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the subnet mask in dotted decimal 
// format.

// Input Format:
// -------------
// An integer, CIDR

// Output Format:
// --------------
// String, Subnet's IP Address


// Sample Input-1:
// ---------------
// 25

// Sample Output-1:
// ----------------
// 255.255.255.128


// Sample Input-2:
// ---------------
// 22

// Sample Output-2:
// ----------------
// 255.255.252.0

import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int hosts=32-n;
        int octet1=255,octet2=255,octet3=255,octet4=255;
        int pos=0;
        while(hosts>0){
            pos%=8;
            if(octet1>0){
                octet1=octet1&~(1<<pos);
            }
            else if(octet2>0){
                octet2=octet2&~(1<<pos);
            }
            else if(octet3>0){
                octet3=octet3&~(1<<pos);
            }
            else if(octet4>0){
                octet4=octet4&~(1<<pos);
            }
            pos++;
            hosts--;
        }
        System.out.println(octet4+"."+octet3+"."+octet2+"."+octet1);
    }
}