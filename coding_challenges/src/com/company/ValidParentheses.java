package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

//https://www.notion.so/jarvisdev/Valid-Parentheses-4b91cccadb6f4c6ca322618594d44e6b
public class ValidParentheses {

  Map<Character,Character> charMap;
  Stack<Character> stack = new Stack<>();

  public ValidParentheses(Map<Character,Character> charMap){
    this.charMap = charMap;
  }

  public boolean isValid(String s){

    for(char c : s.toCharArray()){

      //if the character is a valid starting character
      if(charMap.containsKey(c)) {
        stack.push(c);
      }
      // if character is not one of the valid starting braces
      else{
        //if empty than there is no matching opening brace available for the current brace
        if(stack.isEmpty()){
          return false;
        }
        char top = stack.pop();
        if(charMap.get(top) != c){
          return false;
        }
      }
    }

    //if stack ie empty at the end than true this is valid
    return(stack.isEmpty());
  }

  public static void main(String[] args) {

    Map<Character,Character> map = new HashMap<>();
    map.put( '(' , ')' );
    map.put( '{' , '}' );
    map.put( '[' , ']' );


    String s = "({[]})()";

    ValidParentheses validP = new ValidParentheses(map);
    System.out.println(validP.isValid(s));
  }
}
