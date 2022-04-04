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
				System.out.println("Type your bus stop name: ");
				Scanner scanner2 = new Scanner(System.in);
				String busStopInput = scanner2.nextLine();
				String [] busStopInfo = new String[7];
				for(int i = 0; i < busStops.size(); i++)
				{
					if(busStopInput.equalsIgnoreCase(busStops.get(i).returnStopName()))
					{
						busStopInfo[0] = busStops.get(i).returnStopId();
						busStopInfo[1] = busStops.get(i).returnStopCode();
						busStopInfo[2] = busStops.get(i).returnStopName();
						busStopInfo[3] = busStops.get(i).returnStopDesc();
						busStopInfo[4] = busStops.get(i).returnStopLat();
						busStopInfo[5] = busStops.get(i).returnStopLon();
						busStopInfo[6] = busStops.get(i).returnZoneId();
					}
				}
				String [] output = new String[7];
				output[0] = "Stop Name: " + busStopInfo[2] + "\n";
				output[1] = "Stop ID: " + busStopInfo[0] + "\n";
				output[2] = "Stop Code: " + busStopInfo[1] + "\n";
				output[3] = "Stop Desc: " + busStopInfo[3] + "\n";
				output[4] = "Stop Latitude: " + busStopInfo[4] + "\n";
				output[5] = "Stop Longitude: " + busStopInfo[5] + "\n";
				output[6] = "Stop Zone ID: " + busStopInfo[6] + "\n";
				
				for(int i = 0; i < output.length; i++)
				{
					System.out.println(output[i]);
				}
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
