import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Work1 {

	public static void main(String[] args) {
		File students = new File("CS.txt");
		File course = new File("hedva.txt");
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
		String curHedvaLine = studentsScanner.nextLine();
		String curCSline = courseScanner.nextLine();
		
		while (studentsScanner.hasNextLine() || courseScanner.hasNextLine()) {
			String CSwords[] = curHedvaLine.split(" ");
			String hedvaWords[] = curCSline.split(" ");
			
			int id1 = Integer.parseInt(CSwords[2]);
			int id2 = Integer.parseInt(hedvaWords[0]);
			
			if (id1 == id2) {
				avg = (Integer.parseInt(hedvaWords[1]) + Integer.parseInt(hedvaWords[2])) / 2;
				toWrite = CSwords[2] + " " + CSwords[0] + " " + CSwords[1] + " " + avg;
				try {
					out.write(toWrite + "\n");
				} catch (IOException e) {
					System.out.println("IO exception");
				}
				if (studentsScanner.hasNextLine())
					curHedvaLine = studentsScanner.nextLine();
				if (courseScanner.hasNextLine())
					curCSline = courseScanner.nextLine();
			}
			
			else if (id1 < id2) {
				if (studentsScanner.hasNextLine())
					curHedvaLine = studentsScanner.nextLine();
			}
			
			else {
				if (courseScanner.hasNextLine())
					curCSline = courseScanner.nextLine();
			}
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
