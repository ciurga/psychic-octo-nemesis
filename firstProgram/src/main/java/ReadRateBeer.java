import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Object;
import java.lang.reflect.Field;


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
				//System.out.println(reviews.get(indexReview).getBeer_beerId());
				line = reader.readLine();
				Object o = reviews.get(indexReview);
				Class<?> c = o.getClass();
				Field f = c.getDeclaredField("beer_name");
				f.setAccessible(true);
				System.out.println((String) f.get(o)+" prova ");
				f.set(o, "some-new-value-for-field-f-in-o");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} /*catch (IOException e) {
			e.printStackTrace();
			System.out.println("File open. I can't read: "+e.getMessage());	
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

	private static void parser(String line, ArrayList<Review> reviews) {
		
	}
	
	
	
}
