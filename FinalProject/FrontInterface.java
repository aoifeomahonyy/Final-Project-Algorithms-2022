import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FrontInterface {

	public static ArrayList<BusStops> busStops = new ArrayList<BusStops>();
	public static ArrayList<StopTimes> stopTimes = new ArrayList<StopTimes>();
	public static ArrayList<StopTimes> stopTimesValidTimes = new ArrayList<StopTimes>();
	public static TST<String> ternarySearchTrie = new TST<String>();

	public static void main(String[] args) throws FileNotFoundException {

		readInStopsFile();
		readInStopTimesFile();
		newStopTimesList();
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

						for (int i = 0; i < busStops.size(); i++) {
							String[] busStopInfo = new String[7];
							busStopInfo[0] = busStops.get(i).returnStopId();
							busStopInfo[1] = busStops.get(i).returnStopCode();
							busStopInfo[2] = busStops.get(i).returnStopName();
							busStopInfo[3] = busStops.get(i).returnStopDesc();
							busStopInfo[4] = busStops.get(i).returnStopLat();
							busStopInfo[5] = busStops.get(i).returnStopLon();
							busStopInfo[6] = busStops.get(i).returnZoneId();
							String stopName = busStopInfo[2];
							String stopInformation = "\n****************************************\nStop Name: "
									+ busStopInfo[2] + "\nStop ID: " + busStopInfo[0] + "\nStop Code: " + busStopInfo[1]
									+ "\nStop Desc: " + busStopInfo[3] + "\nStop Latitude: " + busStopInfo[4]
									+ "\nStop Latitude: " + busStopInfo[5] + "\nZone ID: " + busStopInfo[6]
									+ "\n****************************************\n";
							ternarySearchTrie.put(stopName, stopInformation);

						}
						String outputInfo = "";
						for (String s : ternarySearchTrie.keysWithPrefix(busStopInput)) {
							outputInfo += s + ternarySearchTrie.get(s) + "\n";
							System.out.println(outputInfo);
						}

					} else {
						System.out.print("Bus stop was not found. Please try again.");
					}
				} else if (userInput.equals("3")) {

					System.out.print("Enter your arrival time in the format 'hh:mm:ss':");
					if (scanner.hasNextLine()) {
						String timeInput = scanner.nextLine();
						String[] stopTimesInfo = new String[5];
						int count = 0;
						for (int i = 0; i < stopTimesValidTimes.size(); i++) {
							if (timeInput.equals(stopTimesValidTimes.get(i).returnArrivalTime())) {
								count = count + 1;
								System.out.print("*********************************");
								System.out.print("\nTrip result number " + count + ":\n");
								stopTimesInfo[0] = stopTimesValidTimes.get(i).returnTripId();
								stopTimesInfo[1] = stopTimesValidTimes.get(i).returnArrivalTime();
								stopTimesInfo[2] = stopTimesValidTimes.get(i).returnDepTime();
								stopTimesInfo[3] = stopTimesValidTimes.get(i).returnStopId();
								stopTimesInfo[4] = stopTimesValidTimes.get(i).returnStopSequence();
								// stopTimesInfo[5] = stopTimes.get(i).returnStopHeadsign();
								// stopTimesInfo[6] = stopTimes.get(i).returnPickupType();
								// stopTimesInfo[7] = stopTimes.get(i).returnDropoffType();
								// stopTimesInfo[8] = stopTimes.get(i).returnShapeDistTravelled();

								String[] outputInfo = new String[5];
								outputInfo[0] = "\nSpecified Arrival Time: " + stopTimesInfo[1] + "\n";
								outputInfo[1] = "Trip ID: " + stopTimesInfo[0] + "\n";
								outputInfo[2] = "Departure Time: " + stopTimesInfo[2] + "\n";
								outputInfo[3] = "Stop ID: " + stopTimesInfo[3] + "\n";
								outputInfo[4] = "Stop Sequence: " + stopTimesInfo[4] + "\n";
								// outputInfo[5] = "Stop Headsign: " + stopTimesInfo[5];
								// outputInfo[6] = "Pickup Type: " + stopTimesInfo[6];
								// outputInfo[7] = "Dropoff Type: " + stopTimesInfo[7];
								// outputInfo[8] = "Shape Distance Travelled: " + stopTimesInfo[8];

								for (int j = 0; j < outputInfo.length; j++) {
									System.out.println(outputInfo[j]);
								}
							}
						}
					}
				} else {
					System.out.print("Error. Please enter 1, 2, 3 or exit!");
				}
			}
		}
		scanner.close();
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
	//Function that creates a new array list with only valid arrival times in it
	public static void newStopTimesList() {
		for (int i = 0; i < stopTimes.size(); i++) {
			if ((stopTimes.get(i).returnArrivalTime().charAt(0) == ' '
					|| stopTimes.get(i).returnArrivalTime().charAt(0) == '1')
					|| (stopTimes.get(i).returnArrivalTime().charAt(0) == '2'
							&& (stopTimes.get(i).returnArrivalTime().charAt(1) == '0'
									|| stopTimes.get(i).returnArrivalTime().charAt(1) == '1'
									|| stopTimes.get(i).returnArrivalTime().charAt(1) == '2'
									|| stopTimes.get(i).returnArrivalTime().charAt(1) == '3'))) {
				stopTimesValidTimes.add(stopTimes.get(i));
			}
		}
	}

}
