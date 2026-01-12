import java.util.*;
public class D11{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int k=sc.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int maxi=Integer.MIN_VALUE,sum=0,l=0;
        for(int i=0;i<k && i<n;i++){
            sum+=arr[i];
        }
        maxi=Math.max(sum,maxi);
        for(int i=k;i<n;i++){
            sum-=arr[l];
            l++;
            sum+=arr[i];
            maxi=Math.max(sum,maxi);
        }
        System.out.println(maxi);
    }
}