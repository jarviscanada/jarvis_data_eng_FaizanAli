package com.company;

//https://www.notion.so/jarvisdev/Fibonacci-Number-Climbing-Stairs-b626feea76a246f6bc49b872b980f4bb
public class fibonacciNumber {
  private static long[] fibCache;

  public long recursiveSol(int n){

    //base case
    if(n <= 1){
      return n;
    }

    if(fibCache[n] !=0){
      return fibCache[n];
    }

    long nthFibNum = (recursiveSol(n-1) + recursiveSol(n-2));
    fibCache[n] = nthFibNum;

    return(nthFibNum);

  }



  public static void main(String[] args) {
    int n = 50;
    fibCache = new long[n+1];
    fibonacciNumber fib = new fibonacciNumber();

    System.out.println(fib.recursiveSol(n));
  }
}
