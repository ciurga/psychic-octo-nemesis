import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ReadRateBeer {
	static String pathname = "/local/nozza/data/raw/ratebeer.txt";
	static int indexReview = -1;
	
	public static void main(String[] args) {
		int count = 0;
		ArrayList<Review> reviews = new ArrayList<Review>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(pathname));
			String line = reader.readLine();
			while(count<14){
				System.out.println(line);
				count++;
				parser(line,reviews);
				reviews.add(new Review());
				indexReview++;
				System.out.println(reviews.get(indexReview).getBeer_beerId());
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

	private static void parser(String line, ArrayList<Review> reviews) {
		
	}
	
	
	
}
