package com.company;

import static java.lang.Integer.parseInt;

public class StringToInteger {



  public static void main(String[] args) {
    String s = "123";

    try{
      int a = parseInt(s);
    }catch(NumberFormatException e){
      System.out.println("Not a number");
    }
  }

}
