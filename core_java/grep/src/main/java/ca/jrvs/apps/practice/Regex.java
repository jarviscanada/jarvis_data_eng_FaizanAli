package ca.jrvs.apps.practice;

import java.util.regex.*;

public class Regex implements RegexExc{

  @Override
  public boolean matchJpeg(String filename) {
    String regexPattern = "\\.(jpg|jpeg)$";
    Pattern pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(filename);

    return matcher.find();
  }

  @Override
  public boolean matchIp(String ip) {
    String regexPattern = "([0-9]{1,3}\\.){3}[0-9]{1,3}$";
    Pattern pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(ip);

    return matcher.find();
  }

  @Override
  public boolean isEmptyLine(String line) {
    String regexPattern = "^\\s*$";
    Pattern pattern = Pattern.compile(regexPattern,Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(line);

    return matcher.find();
  }

  public static void main(String[] args) {
    Regex regex = new Regex();

    //jpeg file check
    String filename = "testing.jpg";
    boolean checkJpeg = regex.matchJpeg(filename);
    if(checkJpeg){
      System.out.println(filename + " is a Jpeg image");
    }
    else{
      System.out.println(filename + " is not a Jpeg image");
    }

    //valid ip check
    String ip = "0.0.1.999";
    boolean checkIP = regex.matchIp(ip);
    if(checkIP){
      System.out.println(ip + " is a valid IP address");
    }
    else{
      System.out.println(ip + " is not a valid IP address");
    }

    //empty line check
    String line = "         \n      \n 132156  ";
    boolean checkEmptyLine = regex.isEmptyLine(line);
    if(checkEmptyLine){
      System.out.println("This is a Empty line");
    }
    else{
      System.out.println(line + " is Not an Empty line");
    }

  }
}
