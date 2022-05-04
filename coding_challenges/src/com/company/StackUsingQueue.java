package com.company;

import java.util.LinkedList;
import java.util.Queue;

//https://www.notion.so/jarvisdev/Implement-Stack-using-Queue-2b5637e90e55469687831297176d6e73
public class StackUsingQueue {

  Queue<Integer> myQueue = new LinkedList<>();
  Queue<Integer> myQueue2 = new LinkedList<>();

  public StackUsingQueue() {
  }

  public void push(int x) {

    if (myQueue.isEmpty()) {
      myQueue.add(x);
    }
    else {
      myQueue2.add(x);

      while(!myQueue.isEmpty()){
        myQueue2.add(myQueue.remove());
      }

      //swap queue 1 and 2
      Queue<Integer> temp = myQueue;
      myQueue = myQueue2;
      myQueue2 = myQueue;
    }
  }

  public int pop() {
    return(myQueue.remove());
  }

  public int top() {
    return(myQueue.peek());
  }

  public boolean empty() {
    if(myQueue.isEmpty()){
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    StackUsingQueue myStack = new StackUsingQueue();
    myStack.push(1);
    myStack.push(2);
    System.out.println(myStack.top());
    System.out.println(myStack.pop());
    System.out.println(myStack.empty());

    System.out.println(myStack.top());
    System.out.println(myStack.pop());
    System.out.println(myStack.empty());

  }
}
