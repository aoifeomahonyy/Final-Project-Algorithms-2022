import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FrontInterface {

	public static ArrayList<BusStops> busStops = new ArrayList<BusStops>();
	public static ArrayList<StopTimes> stopTimes = new ArrayList<StopTimes>();
	public static TST<BusStops> ternarySearchTree = new TST<BusStops>();
	public static ArrayList<BusStops> tstResults = new ArrayList<BusStops>();

	public static void main(String[] args) throws FileNotFoundException {

		readInStopsFile();
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;

		while (exit == false) {
			System.out.println("\nWelcome to the Vancouver Public Transport System. \n");
			System.out.println("Please choose an option, or exit the system by typing 'exit'. ");
			System.out.println("1. Find the shortest route between two bus stops. ");
			System.out.println("2. Search for a bus stop by name. ");
			System.out.println("3. Search for all trips with a given arrival time. \n");
			System.out.println("Type 1, 2, 3 or 'exit': ");

			if (scanner.hasNextLine()) {
				String userInput = scanner.nextLine();

				if (userInput.equalsIgnoreCase("exit")) {
					exit = true;
					System.out.println("You are now exiting the system. Thank you! ");
					break;

				} else if (userInput.equals("2")) {
					System.out.println("Type your bus stop name: ");
					if (scanner.hasNextLine()) {
						String input = scanner.nextLine();
						String busStopInput = input.toUpperCase();
						String[] busStopInfo = new String[7];
						createResultsList(busStopInput);
						
						for (int i = 0; i < busStops.size(); i++) {

							if (busStopInput.equals(busStops.get(i).returnStopName())) {
								busStopInfo[0] = busStops.get(i).returnStopId();
								busStopInfo[1] = busStops.get(i).returnStopCode();
								busStopInfo[2] = busStops.get(i).returnStopName();
								busStopInfo[3] = busStops.get(i).returnStopDesc();
								busStopInfo[4] = busStops.get(i).returnStopLat();
								busStopInfo[5] = busStops.get(i).returnStopLon();
								busStopInfo[6] = busStops.get(i).returnZoneId();

								String[] outputInfo = new String[7];
								outputInfo[0] = "Stop Name: " + busStopInfo[2] + "\n";
								outputInfo[1] = "Stop ID: " + busStopInfo[0] + "\n";
								outputInfo[2] = "Stop Code: " + busStopInfo[1] + "\n";
								outputInfo[3] = "Stop Desc: " + busStopInfo[3] + "\n";
								outputInfo[4] = "Stop Latitude: " + busStopInfo[4] + "\n";
								outputInfo[5] = "Stop Longitude: " + busStopInfo[5] + "\n";
								outputInfo[6] = "Stop Zone ID: " + busStopInfo[6] + "\n";

								for (int j = 0; j < outputInfo.length; j++) {
									System.out.println(outputInfo[j]);
								}
							}
						}

					} else {
						System.out.print("Bus stop was not found. Please try again.");
					}
				} else {
					System.out.print("Error. Please enter 1, 2, 3 or exit!");
				}
			}
		}
	}

	// Read in stops.txt file
	public static void readInStopsFile() {
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

	// Read in stop_times.txt
	public static void readInStopTimesFile() {
		try {
			File stopTimesFile = new File("stop_times.txt");
			Scanner scanner = new Scanner(stopTimesFile);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				String inputData = scanner.nextLine();
				stopTimes.add(new StopTimes(inputData));
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.print("Error. This file could not be found");
			e.printStackTrace();
		}
	}

	public static String test(String input) {
		String ans = "";
		for (int i = 0; i < busStops.size(); i++) {
			ternarySearchTree.put(input, busStops.get(i));
			Iterable<String> result = ternarySearchTree.keysWithPrefix(busStops.get(i).returnStopName());
			for (String s : result) {
				ans += s + ternarySearchTree.get(s);
			}
		}
		return ans;
	}
	public static void createResultsList(String userInput)
	{
		for(int i = 0; i < busStops.size(); i++)
		{
			ternarySearchTree.put(userInput, busStops.get(i));
			Iterable<String> results = ternarySearchTree.keysWithPrefix(busStops.get(i).returnStopName());
			for(String s : results) {
				System.out.println(s);
			}
		}
	}
}
