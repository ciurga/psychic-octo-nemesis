import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ReadRateBeer {
	static String pathname = "/local/nozza/data/raw/ratebeer.txt";
	
	public static void main(String[] args) {
		int count = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathname));
			String line = reader.readLine();
			while(count<10){
				System.out.println(line);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can't open the file: "+e.getMessage());	
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("File open. I can't read: "+e.getMessage());	
		}
	}
	
	
	
}
