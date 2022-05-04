package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  private static final Logger logger = LoggerFactory.getLogger(JavaGrepLambdaImp.class);

  public static void main(String[] args) {
    logger.info("Starting JvaGrepLambdaImp");

    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JAVAGrepLambdaImp regex rootPath outFile");
    }

    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception e) {
      logger.error("Error: unable to process", e);
    }
  }

  @Override
  public List<String> readLines(File inputFile) {
    List<String> linesList = new ArrayList<>();

    try (Stream<String> stream = Files.lines(inputFile.toPath())) {
      linesList = stream.collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("File could not be read", e);
    }

    return linesList;
  }


  @Override
  public List<File> listFiles(String rootDir) {
    List<File> list = new ArrayList<File>();

    try (Stream<Path> filepath = Files.walk(Paths.get(rootDir))) {

      // convert each value from path to file and add it to the list of files
      list = filepath.map(mapFile -> mapFile.toFile()).filter(file -> file.isFile())
          .collect(Collectors.toList());
    } catch (IOException e) {
      logger.error("No such directory", e);
    }

    return list;
  }
}
