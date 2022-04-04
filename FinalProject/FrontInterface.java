import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class FrontInterface {

	public static ArrayList<BusStops> busStops = new ArrayList<BusStops>();

	public static void main(String[] args) throws FileNotFoundException {
		
		readInStopsFile();
		Scanner scanner1 = new Scanner(System.in);
		boolean exit = false;
		while(exit != true)
		{
			System.out.println("Welcome to the Vancouver Public Transport System. ");
			System.out.println("Please choose an option, or exit the system by typing 'exit'. ");
			System.out.println("1. Find the shortest route between two bus stops. ");
			System.out.println("2. Search for a bus stop by name. ");
			System.out.println("3. Search for all trips with a given arrival time. ");
			System.out.println("Type 1, 2, 3 or 'exit': ");
			
			String userInput = scanner1.next();
			if(userInput.equalsIgnoreCase("exit"))
			{
				exit = true;
				System.out.println("You are now exiting the system. ");
			}
			else if(userInput.equals("2"))
			{
				System.out.println("Type your bus stop name (or just the first few characters): ");
				Scanner scanner2 = new Scanner(System.in);
				String busStopInput = scanner2.nextLine();
				
				
			}
		}
		
	}

	// Read in stops.txt file
	public static void readInStopsFile() throws FileNotFoundException {
		try {
			File stopsFile = new File("stops.txt");
			Scanner scanner = new Scanner(stopsFile);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String inputData = scanner.nextLine();
				busStops.add(new BusStops(inputData));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. This file could not be found");
			e.printStackTrace();
		}

	}
}
