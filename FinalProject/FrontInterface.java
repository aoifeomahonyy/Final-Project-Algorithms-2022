import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
public class FrontInterface {
	
	ArrayList<BusStops> busStops = new ArrayList<BusStops>();

	public static void main(String[] args) {
		
		
	}

	//Read in stops.txt file
	public void readInStopsFile() throws FileNotFoundException
	{
		try {
			File stopsFile = new File("stops.txt");
			Scanner scanner = new Scanner(stopsFile);
			scanner.nextLine();
			while(scanner.hasNextLine())
			{
				String inputData = scanner.nextLine();
				busStops.add(new BusStops(inputData));
			}
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Error. This file could not be found");
		}
		
	}
}
