package com.company;

import java.util.Stack;

//https://www.notion.so/jarvisdev/Implement-Queue-using-Stacks-34bfa7f2b07240cc88f1643cfae0e427
public class QueueUsingStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();


    public void push(int x) {
      if(stack.isEmpty()){
        stack.push(x);
      }
      else{
        while(!stack.isEmpty()){
          stack2.push(stack.pop());
        }

        stack.push(x);

        while(!stack2.isEmpty()){
          stack.push(stack2.pop());
        }
      }
    }

    public int pop() {
      return(stack.pop());
    }

    public int peek() {
      return(stack.peek());
    }

    public boolean empty() {
      if(stack.isEmpty()){
        return true;
      }
      return false;
    }

  public static void main(String[] args) {
    QueueUsingStack myQueue = new QueueUsingStack();
    myQueue.push(1);
    myQueue.push(2);
    System.out.println(myQueue.peek());
    System.out.println(myQueue.pop());
    System.out.println(myQueue.empty());

    System.out.println(myQueue.peek());
    System.out.println(myQueue.pop());
    System.out.println(myQueue.empty());
  }
}
