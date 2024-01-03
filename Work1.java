// Ron Bitan (ID:315924316) && Noam Muchnik (ID:212472484)
// Github: https://github.com/Subsidy2032/Systems-programing/blob/master/Work1.java

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work1 {
    /**
     * Main method to read from two files, find students present in both files,
     * calculate their average, and write the details into a new report file.
     */

    public static void main(String[] args) {
        // File paths for input files
        String studentsFile = "CS.txt";
        String courseFile = "hedva.txt";

        // Creating File objects for both input files
        File students = new File(studentsFile);
        File course = new File(courseFile);
        Scanner studentsScanner = null;
        Scanner courseScanner = null;
        FileWriter out = null;

        // Initializing Scanners and FileWriter
        try {
            studentsScanner = new Scanner(students);
            courseScanner = new Scanner(course);
            out = new FileWriter("report.txt");
        }
        // Exception handling for file not found
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        // Exception handling for IO issues
        catch (IOException e) {
            System.out.println("IO exception");
            System.exit(0);
        }

        String toWrite;
        double avg;
        // Initializing strings to hold current lines from both files
        String curStudentsLine = studentsScanner.nextLine();
        String curCourseLine = courseScanner.nextLine();
        boolean continueLoop = true;
        int id1 = 0, id2 = 0;

        // Main loop to iterate through both files
        try {
	        while (continueLoop) {
	            // Splitting lines into arrays of words
	            String studentsWords[] = curStudentsLine.split(" ");
	            String courseWords[] = curCourseLine.split(" ");
	
	            // Converting IDs to integers for comparison
	            try {
	            	id1 = Integer.parseInt(studentsWords[2]);
	                id2 = Integer.parseInt(courseWords[0]);
	            } catch (Exception e) {
	            	System.out.println("Wrong type");
	            }
	
	            // Checking if IDs match
	            if (id1 == id2) {
	                // Calculating average
	                avg = ((double)Integer.parseInt(courseWords[1]) + (double)Integer.parseInt(courseWords[2])) / 2;
	                toWrite = studentsWords[2] + " " + studentsWords[0] + " " + studentsWords[1] + " " + avg;
	                
	                // Writing data to report.txt
	                out.write(toWrite + "\n");
	                
	                if (studentsScanner.hasNextLine())
	                    curStudentsLine = studentsScanner.nextLine();
	                if (courseScanner.hasNextLine())
	                    curCourseLine = courseScanner.nextLine();
	                if (!(studentsScanner.hasNextLine()) && !(courseScanner.hasNextLine()))
	                    continueLoop = false;
	            }
	            // Handling cases where IDs don't match
	            else if ((id1 < id2 && studentsScanner.hasNextLine()) || (studentsScanner.hasNextLine() && !(courseScanner.hasNextLine()))) {
	                curStudentsLine = studentsScanner.nextLine();
	            }
	
	            else if (courseScanner.hasNextLine())
	                curCourseLine = courseScanner.nextLine();
	
	            else
	                continueLoop = false;
	        }
        } 
        catch(IOException e) {
        	System.out.println("IO exception");
        }
        
        catch(Exception e) {
        	System.out.println("Something went wrong");
        }

        // Closing scanners and FileWriter
        studentsScanner.close();
        courseScanner.close();
        try {
            out.close();
        }
        catch (IOException e) {
            System.out.println("IO exception");
        }
    }

}