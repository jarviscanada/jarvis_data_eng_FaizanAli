# Introduction
This program allows you to have Linux grep functionality in any OS. 
It allows the user to search for a regex pattern in all files in a given directory and its subdirectories, outputting all matching lines into a text file.

For this app, a JavaGrep interface was created specifying the functionality the app must-have. JavaGrepImp class implments this interface and uses only features available in Java 7 and lower. JavaGrepLambdaImp class extends the JavaGrepImp class but overides some methods so they made use of Java 8 functionality - streams and Lambda for getting the list of files in directories and reading them.

The app was created using Maven to allow easy project builds and a Standard Directory Layout. It was deployed to DockerHub for easy access.

# Quick Start
If you got source code from this git repository:

    JavaGrepLambdaImp:
    	Use Maven clean and package command to build Uber Jar file:
    		mvn clean package
       	
    	You can now use the jar file to run the program:
  			- USAGE: JavaGrepLambdaImp regex rootPath outFile
  			- ex: java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepLambdaImp .*Romeo.*Juliet.* ./data ./src/main/resources/output.txt
       
       

    JavaGrepImp:
    	Use Maven clean and package command to build Uber Jar file:
    		mvn clean package
       	
    	You can now use the jar file to run the program:
  			- USAGE: JavaGrepImp regex rootPath outFile
  			- ex: java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepLambdaImp .*Romeo.*Juliet.* ./data ./src/main/resources/output.txt

Docker:

    Get image from Docker Hub:
        docker pull faizan1394/grep
    Run docker container:
        docker run --rm -v pwd /data:/data pwd /log:/log faizan1394/grep .*Romeo.*Juliet.* /data /log/grep.out

#Implemenation
	
	Class JavaGrepLambdaImp:
		  /**
		   * Traverse a given directory and return all files
		   *
		   * @param rootDir input directory
		   * @return files under the rootDir
		   */
		  List<File> listFiles(String rootDir);
		
		
		  /**
		   * Read a file and return all the lines in it
		   *
		   * @param inputFile file to be read
		   * @return lines
		   * @throws IllegalArgumentException if a given inputFile is not a file
		   */
		  List<String> readLines(File inputFile);	
		

		  /**
		   * check if a line contains the regex pattern (passed by user)
		   *
		   * @param line input string
		   * @return true if there is a match
		   */
		  boolean containsPattern(String line);
		  
		  
		  /**
		   * Write lines to a file
		   *
		   * @param lines matched line
		   * @throws IOException if write failed
		   */
		  void writeToFile(List<String> lines) throws IOException;
		    

#Performance Issue
Reading an entire file into memory can result in an out of memory error as there may not be enough memory allocated to your application to read in such a big file.

Instead of reading the entire file at once we can use BufferReader or the Stream API to read the data from file in chunks. 
They will result in only a small portion of data/file being loaded into memory at a time and the next portion wont be loaded into memory untill this portion is done being processed.

# Test
- Prepared a small number of text files that were placed into the root directory and checked if all files are read correctly with expected output.

- Tested to see if program works as intended if wrong directory was provided or if no files existed.

# Deployment
- Created a docker image with openjdk:8-alpine image as the base image
- The uber jar file for our app was added to the image
- The jar file was set as the ENTRYPOINT for the docker app
- The final image was than deployed to docker under the name faizan1394/grep

# Improvement
- Instead of reading all the line from the file into an array than comparing it we can compare each line to regex as it is read and only save it if there is a match
- A GUI can be added to select root directory as well as location to save the output file
- There can be a separate output file corresponding to the file the data came from instead of all outputs in 1 file
