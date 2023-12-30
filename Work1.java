// This program gets 2 files, 1 with all students and one with the grades of students in a specific course, and outputs a new file with shared students.
// Ron Bitan & Noam Muchnik

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work1 {

	public static void main(String[] args) {
		String studentsFile = "CS.txt";
		String courseFile = "hedva.txt";
		
		// Opening the required files and initializing the scanners and FileWriter
		File students = new File(studentsFile);
		File course = new File(courseFile);
		Scanner studentsScanner = null;
		Scanner courseScanner = null;
		FileWriter out = null;
		
		// Giving values to the scanners and FileWriter
		try {
			studentsScanner = new Scanner(students);
			courseScanner = new Scanner(course);
			out = new FileWriter("report.txt");
		}
		
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} 
		
		catch (IOException e) {
			System.out.println("IO exception");
			System.exit(0);
		}
		
		String toWrite; // Lines to write to the output file
		double avg;
		String curStudentsLine = studentsScanner.nextLine(); // To store the current line from the students file
		String curCourseLine = courseScanner.nextLine(); // To store the current line from the course file
		boolean continueLoop = true;
		
		while (continueLoop) {
			// Splitting the lines to arrays of words
			String studentsWords[] = curStudentsLine.split(" ");
			String courseWords[] = curCourseLine.split(" ");
			
			// Turning the ids to integers
			int id1 = Integer.parseInt(studentsWords[2]);
			int id2 = Integer.parseInt(courseWords[0]);
			
			// If an equal id is found in both files, prints the student name, id and average to the output file
			if (id1 == id2) {
				avg = ((double)Integer.parseInt(courseWords[1]) + (double)Integer.parseInt(courseWords[2])) / 2;
				toWrite = studentsWords[2] + " " + studentsWords[0] + " " + studentsWords[1] + " " + avg;
				try {
					out.write(toWrite + "\n");
				} catch (IOException e) {
					System.out.println("IO exception");
				}
				if (studentsScanner.hasNextLine())
					curStudentsLine = studentsScanner.nextLine();
				if (courseScanner.hasNextLine())
					curCourseLine = courseScanner.nextLine();
				if (!(studentsScanner.hasNextLine()) && !(courseScanner.hasNextLine()))
					continueLoop = false;
			}
			
			// Goes to the next line in one of the files (depends on where the id is smaller)
			else if ((id1 < id2 && studentsScanner.hasNextLine()) || (studentsScanner.hasNextLine() && !(courseScanner.hasNextLine()))) {
					curStudentsLine = studentsScanner.nextLine();
			}
			
			else if (courseScanner.hasNextLine())
					curCourseLine = courseScanner.nextLine();
			
			else
				continueLoop = false;
		}
		
		studentsScanner.close();
		courseScanner.close();
		try {
			out.close();
		} catch (IOException e) {
			System.out.println("IO exception");
		}
	}

}
