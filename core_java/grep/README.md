# Introduction
This program allows you to have linux grep functionality in any OS. 

It allows user to search for a regex pattern in all files in a given directory and its subdirectories, outputting all matching lines into a txt file.

This app was implemented in 2 different ways:

    - JavaGrepImp
        - This version uses for loops and file library to get list of files and read them
    - JavaGrepLambdaImp
        - This version uses JAVA 8 functionality - streams and Lamabda for getting list of files in directroires and reading them

The app was deployed to DockerHub so anyone can easily get it and use it.

# Quick Start
If you got source code from github and compiled it yourself:

    Lambda Version:
       - USAGE: JavaGrepLambdaImp regex rootPath outFile

    non-lambda version:
       - USAGE: JavaGrepImp regex rootPath outFile

Docker:

    Get image from Docker Hub:
        docker pull faizan1394/grep
    Run docker container:
        docker run --rm -v pwd /data:/data pwd /log:/log faizan1394/grep .*Romeo.*Juliet.* /data /log/grep.out

#Implemenation
## Pseudocode
    fucntion process():
        matchedLines = new ArrayList<String>()
        
        For each file in root directory:
            For each line in file:
                If line matches regex pattern:
                    Add to matchedLines
        
        Write matched Lines to output file
    
    

## Performance Issue
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