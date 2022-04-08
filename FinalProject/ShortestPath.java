
public class ShortestPath {

	public double[][] distTo;
	public double[][] edgeTo;
	public int edgeCount;
	
	public void shortestPathDijkstra(int edge) {
		boolean[] shortestPathSet = new boolean[distTo.length];
		shortestPathSet[edge] = true;
		
		while(true) {
			int altEdge = -1;
			for(int i = 0; i < distTo.length; i++) {
				if((distTo[edge][i] != Integer.MAX_VALUE) && (shortestPathSet[i] == false)) {
					altEdge = i;
					break;
				}
			}
			if(altEdge == -1) {
				return;
			}
			shortestPathSet[altEdge] = true;
			for(int j = 0; j < distTo.length; j++) {
				if(distTo[edge][altEdge] + distTo[altEdge][j] < distTo[edge][j]) {
					distTo[edge][j] = distTo[edge][altEdge] + distTo[altEdge][j];
					shortestPathSet[j] = false;
					edgeTo[edge][j] = altEdge;
				}
			}
		}
	}
}
