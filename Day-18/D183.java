// A smart factory has a central alarm system that coordinates three independent 
// subsystems:
//     - Thread A – Alarm Controller -> Activates a warning beep (prints 0).
//     - Thread B – Even Sensor Unit -> Reports only even-numbered machine IDs.
//     - Thread C – Odd Sensor Unit -> Reports only odd-numbered machine IDs.

// The system must produce an alternating sequence:
// 0 1 0 2 0 3 0 4 0 5 ...


// The total length of the sequence must be 2n, where: n is the total number of 
// machine IDs (from 1 to n). Each machine report must always be preceded by a 
// warning beep (0).

// Implement a class FactoryAlarmSystem that ensures proper synchronization between 
// three threads so that: The alarm thread always prints 0, Then either the odd or 
// even thread prints the next correct number. Continue this pattern until all 
// numbers from 1 to n are printed

// only

// 🧾 Input Format
// ---------------
// A single integer: N

// 🖨 Output Format
// ----------------
// A continuous string representing the alarm and machine sequence.

// Sample Input-1:
// ---------------
// 2

// Sample Output-1:
// ----------------
// 0102

// Sample Input-2:
// ---------------
// 5

// Sample Output-2:
// ----------------
// 0102030405


import java.util.*;
class Shared{
    int i,n;
    boolean even,zero;
    public Shared(int n){
        this.n=n;
        this.i=1;
        zero=true;
        even=false;
    }
    public synchronized int getVal(){
        return i;
    }
    public synchronized int getFinal(){
        return n;
    }
    public synchronized boolean isEven(){
        return even;
    }
    public synchronized boolean isZero(){
        return zero;
    }
    public synchronized void printEven(){
        System.out.print(i);
        zero=true;
        i++;
        notifyAll();
    }
    public synchronized void printOdd(){
        System.out.print(i);
        zero=true;
        i++;
        notifyAll();
    }
    public synchronized void printZero(){
        System.out.print(0);
        zero=false;
        even=(i%2==0);
        notifyAll();
    }
}
class Thread1 implements Runnable{
    Shared sh;
    public Thread1(Shared sh){
        this.sh=sh;
    }
    public void run(){
        while(true){
            synchronized(sh){
            if(sh.getVal()>sh.getFinal()) break;
            if(sh.isEven() && !sh.isZero()){
                sh.printEven();
            }
            else{
                try{
                    sh.wait();
                }
                catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
          }
        }
    }
}
class Thread2 implements Runnable{
    Shared sh;
    public Thread2(Shared sh){
        this.sh=sh;
    }
    public void run(){
        while(true){
            synchronized(sh){
            if(sh.getVal()>sh.getFinal()) break;
            if(!sh.isEven() && !sh.isZero()){
                sh.printOdd();
            }
            else{
                try{
                    sh.wait();
                }
                catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }
      }
    }
}
class Thread3 implements Runnable{
    Shared sh;
    public Thread3(Shared sh){
        this.sh=sh;
    }
    public void run(){
        while(true){
            synchronized(sh){
            if(sh.getVal()>sh.getFinal()) break;
            if(sh.isZero()){
                sh.printZero();
            }
            else{
               try{
                    sh.wait();
                }
                catch(InterruptedException e){
                    throw new RuntimeException(e);
                }
            }
        }
      }
    }
}
class Solution{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        Shared sh = new Shared(n);
        Thread t1 =  new Thread(new Thread1(sh));
        Thread t2 =  new Thread(new Thread2(sh));
        Thread t3 = new Thread(new Thread3(sh));
        t1.start();
        t2.start();
        t3.start();
    }
}
