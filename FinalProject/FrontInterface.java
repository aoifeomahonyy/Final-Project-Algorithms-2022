import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

public class FrontInterface {

	public static ArrayList<BusStops> busStops = new ArrayList<BusStops>();
	public static List<StopTimes> stopTimes = new ArrayList<StopTimes>();
	public static List<StopTimes> stopTimesValidTimes = new ArrayList<StopTimes>();
	public static TST<String> ternarySearchTrie = new TST<String>();

	public static void main(String[] args) throws FileNotFoundException {

		readInStopsFile();
		readInStopTimesFile();
		newStopTimesList();
		StopTimes[] stopTimesNeedSorting = new StopTimes[stopTimesValidTimes.size()];
		stopTimesValidTimes.toArray(stopTimesNeedSorting);
		StopTimes[] sortedStopTimes = new StopTimes[stopTimesNeedSorting.length];
		sortedStopTimes = mergeSortRecursive(stopTimesNeedSorting);
		stopTimesValidTimes = Arrays.asList(sortedStopTimes);
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

				} else if (userInput.equals("1")) {
					System.out.println("Enter the bus stop you are beginning your journey at: ");
					String input1 = scanner.nextLine();
					String stopInput1 = input1.toUpperCase();
					System.out.println("Enter your destination bus stop: ");
					String input2 = scanner.nextLine();
					String stopInput2 = input2.toUpperCase();
					for (int i = 0; i < busStops.size(); i++) {
						if ((stopInput1.equals(busStops.get(i).returnStopId()))
								&& (stopInput1.equals(busStops.get(i).returnStopId()))) {
					
							System.out.print("The shortest path between " + stopInput1 + " and " + stopInput2 + " is: " + ShortestPath.printShortestPath());
						}
						else {
							System.out.println("One of these stops does not exist. Please try again.\n");
						}
					}
				} else if (userInput.equals("2")) {
					System.out.println("Type your bus stop name: ");
					if (scanner.hasNextLine()) {
						String input = scanner.nextLine();
						String busStopInput = input.toUpperCase();
						int count = 0;
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
									+ "\nStop Description: " + busStopInfo[3] + "\nStop Latitude: " + busStopInfo[4]
									+ "\nStop Latitude: " + busStopInfo[5] + "\nZone ID: " + busStopInfo[6]
									+ "\n****************************************\n";
							ternarySearchTrie.put(stopName, stopInformation);

						}
						String outputInfo = "";
						for (String s : ternarySearchTrie.keysWithPrefix(busStopInput)) {
							outputInfo += s + ternarySearchTrie.get(s) + "\n";
							count = count + 1;
						}
						if (outputInfo.equals("")) {
							System.out.println("No bus stops could be found. Please try again!\n");
						} else if (count > 1) {
							System.out.println("We have found " + count + " results for your input '" + input + "':\n"
									+ outputInfo);
						} else {
							System.out.println("We have found 1 result for your input '" + input + "':\n" + outputInfo);
						}

					}
				} else if (userInput.equals("3")) {

					System.out.print("Enter your arrival time in the format 'hh:mm:ss':");
					if (scanner.hasNextLine()) {
						String timeInput = scanner.nextLine();
						invalidUserInputTime(timeInput);
						String[] stopTimesInfo = new String[9];
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
								stopTimesInfo[5] = stopTimesValidTimes.get(i).returnStopHeadsign();
								stopTimesInfo[6] = stopTimesValidTimes.get(i).returnPickupType();
								stopTimesInfo[7] = stopTimesValidTimes.get(i).returnDropoffType();
								stopTimesInfo[8] = stopTimesValidTimes.get(i).returnShapeDistTravelled();

								String[] outputInfo = new String[9];
								outputInfo[0] = "\nSpecified Arrival Time: " + stopTimesInfo[1];
								outputInfo[1] = "\nTrip ID: " + stopTimesInfo[0];
								outputInfo[2] = "\nDeparture Time: " + stopTimesInfo[2];
								outputInfo[3] = "\nStop ID: " + stopTimesInfo[3];
								outputInfo[4] = "\nStop Sequence: " + stopTimesInfo[4];
								outputInfo[5] = "\nStop Headsign: " + stopTimesInfo[5];
								outputInfo[6] = "\nPickup Type: " + stopTimesInfo[6];
								outputInfo[7] = "\nDropoff Type: " + stopTimesInfo[7];
								outputInfo[8] = "\nShape Distance Travelled: " + stopTimesInfo[8] + "\n";

								for (int j = 0; j < outputInfo.length; j++) {
									System.out.print(outputInfo[j]);
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
			System.out.print("Error. This file could not be found.");
			e.printStackTrace();
		}
	}

	// Function that creates a new array list with only valid arrival times in it
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

	// Implementing merge sort to sort the stop times array by trip id
	public static StopTimes[] mergeSortRecursive(StopTimes[] a) {
		if (a == null) {
			return null;
		} else {
			StopTimes[] aux = new StopTimes[a.length];
			sort(a, aux, 0, a.length - 1);
		}
		return a;
	}

	private static void sort(StopTimes[] a, StopTimes[] aux, int low, int high) {
		if (high <= low) {
			return;
		}
		int middle = low + (high - low) / 2;
		sort(a, aux, low, middle);
		sort(a, aux, middle + 1, high);
		merge(a, aux, low, middle, high);
	}

	private static void merge(StopTimes[] a, StopTimes[] aux, int low, int middle, int high) {
		for (int k = low; k <= high; k++) {
			aux[k] = a[k];
		}
		int i = low;
		int j = middle + 1;
		for (int k = low; k <= high; k++) {
			if (i > middle) {
				a[k].trip_id = aux[j++].trip_id;
			} else if (j > high) {
				a[k].trip_id = aux[i++].trip_id;
			} else if (aux[j].trip_id < aux[i].trip_id) {
				a[k].trip_id = aux[j++].trip_id;
			} else {
				a[k].trip_id = aux[i++].trip_id;
			}
		}
	}

	// returns an error message if an invalid time is inputted
	public static void invalidUserInputTime(String input) {
		if (input.length() != 8) {
			System.out.println("\nInvalid time. Make sure to enter in the correct format 'hh:mm:ss'\n");
		} else if (input.length() == 8) {
			char h1 = input.charAt(0);
			char h2 = input.charAt(1);
			String hoursEntered = "";
			if (h1 == ' ') {
				hoursEntered = "" + h2;
			} else {
				hoursEntered = "" + h1 + h2;
			}
			int hoursEntered2 = Integer.parseInt(hoursEntered);
			char min1 = input.charAt(3);
			char min2 = input.charAt(4);
			String minutesEntered = "" + min1 + min2;
			int minutesEntered2 = Integer.parseInt(minutesEntered);
			char sec1 = input.charAt(6);
			char sec2 = input.charAt(7);
			String secondsEntered = "" + sec1 + sec2;
			int secondsEntered2 = Integer.parseInt(secondsEntered);
			if ((hoursEntered2 > 23) || (minutesEntered2 > 59) || (secondsEntered2 > 59)) {
				System.out.println("\nInvalid time. Make sure to enter in the correct format 'hh:mm:ss'\n");
			}
			if (input.charAt(2) != ':' || input.charAt(5) != ':') {
				System.out.print("\nInvalid format. Make sure to include colons ':' in your input.\n");
			}
		}
	}
	// Function that finds highest stop number
	public static int findHighestStopNumber() {
		int highestNumber = 0;
		for (int i = 1; i < busStops.size(); i++) {
			if (busStops.get(i).stop_id > busStops.get(i-1).stop_id) {
				highestNumber = busStops.get(i).stop_id;
			}
		}
		return highestNumber;
	}
}
