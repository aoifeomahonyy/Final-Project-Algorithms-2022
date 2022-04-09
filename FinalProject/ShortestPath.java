import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShortestPath {

	public static final int HIGHEST_STOP_ID = FrontInterface.findHighestStopNumber();
	public static double[][] distTo = new double[HIGHEST_STOP_ID][HIGHEST_STOP_ID];
	public static int edgeTo[][];

	public static void createSystem() throws FileNotFoundException{

		// initialize all distances to infinity
		for (int i = 0; i < distTo.length; i++) {
			for (int j = 0; j < distTo[i].length; j++) {
				distTo[i][j] = Integer.MAX_VALUE;
				if (i == j) {
					distTo[i][j] = 0;
				}
			}
		}
		int startStop = 0;
		int endStop = 0;
		int currentTrip = 0;
		int prevTrip = 0;
		String line = "";
		
		// read in stop_times.txt
	
			File stopTimesFile = new File("stop_times.txt");
			Scanner scanner = new Scanner(stopTimesFile);
			scanner.useDelimiter(",");
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				currentTrip = scanner.nextInt();
				// skipping over the arrival and departure times
				scanner.next();
				scanner.next();
				endStop = scanner.nextInt();
				scanner.nextLine();
			}
			scanner.close();
		
			File transfersFile = new File("transfers.txt");
			scanner = new Scanner(transfersFile);
			scanner.nextLine();
			while (scanner.hasNextLine()) {
				Scanner scanner2 = new Scanner(line);
				scanner2.useDelimiter(",");
				startStop = scanner2.nextInt();
				endStop = scanner2.nextInt();
				scanner2.close();
			}
			scanner.close();
		
		}
	}

	public void shortestPathDijkstra(int start, int end) {
		boolean[] shortestPathSet = new boolean[distTo.length];
		shortestPathSet[edge] = true;

		while (true) {
			int altEdge = -1;
			for (int i = 0; i < distTo.length; i++) {
				if ((distTo[edge][i] != Integer.MAX_VALUE) && (shortestPathSet[i] == false)) {
					altEdge = i;
					break;
				}
			}
			if (altEdge == -1) {
				return;
			}
			shortestPathSet[altEdge] = true;
			for (int j = 0; j < distTo.length; j++) {
				if (distTo[edge][altEdge] + distTo[altEdge][j] < distTo[edge][j]) {
					distTo[edge][j] = distTo[edge][altEdge] + distTo[altEdge][j];
					shortestPathSet[j] = false;
					edgeTo[edge][j] = altEdge;
				}
			}
		}
	}

	public static String printShortestPath() {

	}
}
