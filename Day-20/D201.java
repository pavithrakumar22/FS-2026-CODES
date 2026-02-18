// Write a program that takes an IP address and subnet mask (in CIDR notation, 
// e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

// Input Format:
// ---------------
// A String, IPAddress
// An integer, CIDR

// Output Format:
// ---------------
// Space separated IP addresses, network IP and broadcast IP.


// Sample Input-1:
// -----------------
// 192.168.1.10
// 24

// Sample Output-1:
// ------------------
// 192.168.1.0 192.168.1.255


// Sample Input-2:
// -----------------
// 192.0.2.1
// 24

// Sample Output-2:
// ------------------
// 192.0.2.0 192.0.2.255



import java.util.*;
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] arr=sc.nextLine().split("\\.");
        int cidr=sc.nextInt();
        String octet1=String.format("%8s",Integer.toBinaryString(Integer.parseInt(arr[0]))).replace(' ','0');
        String octet2=String.format("%8s",Integer.toBinaryString(Integer.parseInt(arr[1]))).replace(' ','0');
        String octet3=String.format("%8s",Integer.toBinaryString(Integer.parseInt(arr[2]))).replace(' ','0');
        String octet4=String.format("%8s",Integer.toBinaryString(Integer.parseInt(arr[3]))).replace(' ','0');
        String octet=octet1+octet2+octet3+octet4;
        StringBuilder sb = new StringBuilder(octet);
        int hosts=32-cidr;
        for(int i=31;i>31-hosts;i--){
            sb.setCharAt(i,'0');
        }
        String start=sb.toString();
        for(int i=31;i>31-hosts;i--){
            sb.setCharAt(i,'1');
        }
        String end=sb.toString();
        int[] broadcast1= new int[4];
        int[] broadcast2 = new int[4];
        for(int i=0;i<4;i++){
            broadcast1[i]=Integer.parseInt(start.substring(i*8,(i+1)*8),2);
        }
        for(int i=0;i<4;i++){
            broadcast2[i]=Integer.parseInt(end.substring(i*8,(i+1)*8),2);
        }
        for(int i=0;i<4;i++){
            if(i==3) System.out.println(broadcast1[i]+" ");
            else System.out.print(broadcast1[i]+".");
        }
        for(int i=0;i<4;i++){
            if(i==3) System.out.println(broadcast2[i]+" ");
            else System.out.print(broadcast2[i]+".");
        }
        
    }
}





// sol 2



import java.util.*;

public class Solution {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Read IP
        String ip = sc.nextLine();
        String[] parts = ip.split("\\.");

        // Convert IP to 32-bit integer
        long ipNum = 0;
        for (int i = 0; i < 4; i++) {
            ipNum = (ipNum << 8) | Integer.parseInt(parts[i]);
        }

        // Read CIDR
        int cidr = sc.nextInt();

        // Create subnet mask
        long mask = (0xFFFFFFFFL << (32 - cidr)) & 0xFFFFFFFFL;

        // Network address
        long network = ipNum & mask;

        // Broadcast address
        long broadcast = network | (~mask & 0xFFFFFFFFL);

        // Print results
        System.out.println("Network Address: " + toIP(network));
        System.out.println("Broadcast Address: " + toIP(broadcast));
    }

    // Convert 32-bit number back to dotted IP
    static String toIP(long num) {
        return ((num >> 24) & 255) + "." +
               ((num >> 16) & 255) + "." +
               ((num >> 8) & 255) + "." +
               (num & 255);
    }
}