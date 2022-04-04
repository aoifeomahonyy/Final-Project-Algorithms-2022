import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FrontInterface {

	public static ArrayList<BusStops> busStops = new ArrayList<BusStops>();
	public static ArrayList<StopTimes> stopTimes = new ArrayList<StopTimes>();

	public static void main(String[] args) {

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
				} else if (userInput.equals("2")) {
					System.out.println("Type your bus stop name: ");
					if (scanner.hasNextLine()) {
						String busStopInput = scanner.nextLine();
						String[] busStopInfo = new String[7];
						for (int i = 0; i < busStops.size(); i++) {
							if (busStopInput == (busStops.get(i).returnStopName())) {
								busStopInfo[0] = busStops.get(i).returnStopId();
								busStopInfo[1] = busStops.get(i).returnStopCode();
								busStopInfo[2] = busStops.get(i).returnStopName();
								busStopInfo[3] = busStops.get(i).returnStopDesc();
								busStopInfo[4] = busStops.get(i).returnStopLat();
								busStopInfo[5] = busStops.get(i).returnStopLon();
								busStopInfo[6] = busStops.get(i).returnZoneId();

								String[] output = new String[7];
								output[0] = "Stop Name: " + busStopInfo[2];
								output[1] = "Stop ID: " + busStopInfo[0];
								output[2] = "Stop Code: " + busStopInfo[1];
								output[3] = "Stop Desc: " + busStopInfo[3];
								output[4] = "Stop Latitude: " + busStopInfo[4];
								output[5] = "Stop Longitude: " + busStopInfo[5];
								output[6] = "Stop Zone ID: " + busStopInfo[6];

								for (int j = 0; j < output.length; j++) {
									System.out.println(output[i]);
								}
								System.out.print(output[1]);
							}

						}

					}
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
}
