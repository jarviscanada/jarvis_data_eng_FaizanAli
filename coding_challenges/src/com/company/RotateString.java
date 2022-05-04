package com.company;

//https://www.notion.so/jarvisdev/Rotate-String-952107e8f3594307851e34a6173c076a
public class RotateString {

  //s+s must contain goal in it if s can become gaol after some shift
  public boolean rotatedString(String s, String goal){
    if(s.length() == goal.length() &&  (s+s).contains(goal)){
      return true;
    }
    return false;
  }

  public static void main(String[] args) {
    String s = "abcde";
    String goal = "cdeab";

    RotateString rotate = new RotateString();

    System.out.println(rotate.rotatedString(s,goal));
  }
}
