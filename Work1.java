import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work1 {

	public static void main(String[] args) {
		String studentsFile = "CS.txt";
		String courseFile = "hedva.txt";

		File students = new File(studentsFile);
		File course = new File(courseFile);
		Scanner studentsScanner = null;
		Scanner courseScanner = null;
		FileWriter out = null;
		
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
		
		String toWrite;
		double avg;
		String curStudentsLine = studentsScanner.nextLine();
		String curCourseLine = courseScanner.nextLine();
		boolean continueLoop = true;
		
		while (continueLoop) {
			String studentsWords[] = curStudentsLine.split(" ");
			String courseWords[] = curCourseLine.split(" ");
			
			int id1 = Integer.parseInt(studentsWords[2]);
			int id2 = Integer.parseInt(courseWords[0]);
			
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
