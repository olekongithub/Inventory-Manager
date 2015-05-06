import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class FileReader {
	
	public static String[] readFile(String fileLocation) throws FileNotFoundException{
		String[] fileContents = new String[100];
		Scanner scan = new Scanner(new File(fileLocation));
		int x = 0;
		while (scan.hasNext()){
			fileContents[x] = scan.next();
			x++;
		}
		
		return fileContents;
		
	}
	
}