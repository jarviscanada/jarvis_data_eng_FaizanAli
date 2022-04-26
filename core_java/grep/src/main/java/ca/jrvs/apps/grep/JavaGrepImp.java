package ca.jrvs.apps.grep;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepImp implements JavaGrep {

  private static final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    logger.info("Starting JvaGrep");

    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JAVAGrep regex rootPath outFile");
    }

    JavaGrepImp javaGrepImp = new JavaGrepImp();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception e) {
      logger.error("Error: unable to process", e);
    }

  }

  @Override
  public void process() throws IOException {
    logger.info("Starting to process");

    ArrayList<String> matchedLines = new ArrayList<>();

    for (File file : listFiles(rootPath)) {
      for (String line : readLines(file)) {
        if (containsPattern(line)) {
          matchedLines.add(line);
        }
      }
    }

    writeToFile(matchedLines);

    logger.info("Processing complete");
  }

  @Override
  public List<File> listFiles(String rootDir) {

    List<File> list = new ArrayList<File>();

    //get file object
    File dr = new File(rootDir);

    //list of all files and directories in root directory
    File[] listFiles = dr.listFiles();

    for(File f : listFiles){
      if(f.isFile()){
        list.add(f);
      }
    }

    return list;
  }

  @Override
  public List<String> readLines(File inputFile) {

    BufferedReader reader;
    List<String> linesList = new ArrayList<>();

    try {
      reader = new BufferedReader(new FileReader(inputFile));
      String line = reader.readLine();
      while (line != null) {
        linesList.add(line);
        line = reader.readLine();
      }

    } catch (FileNotFoundException e) {
      logger.error("File not found", e);
    } catch (IOException e) {
      logger.error("File could not be read", e);
    }
    return linesList;
  }

  @Override
  public boolean containsPattern(String line) {

    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(line);

    return matcher.find();
  }


  @Override
  public void writeToFile(List<String> lines) throws IOException {
    logger.info("Attempting to write matched line to {}",outFile);
    BufferedWriter bw = new BufferedWriter(new FileWriter(outFile));

    for (String line : lines) {
      bw.write(line + "\n");
    }

    bw.close();
    logger.info("File writing completed successfully");
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    logger.info("Setting regex pattern: {}",regex);
    this.regex = regex;
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    logger.info("Setting root directory: {}",rootPath);
    this.rootPath = rootPath;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    logger.info("Setting output file: {}",outFile);
    this.outFile = outFile;
  }
}
