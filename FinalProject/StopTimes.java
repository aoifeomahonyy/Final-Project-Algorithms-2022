
public class StopTimes {

	public int trip_id;
	public String arrival_time;
	public String departure_time;
	public int stop_id;
	public int stop_sequence;
	public String stop_headsign;
	public int pickup_type;
	public int drop_off_type;
	public double shape_dist_travelled;

	public StopTimes(String input) {
		String[] inputVals = input.split(",");
		this.trip_id = Integer.parseInt(inputVals[0]);
		this.arrival_time = inputVals[1];
		this.departure_time = inputVals[2];
		this.stop_id = Integer.parseInt(inputVals[3]);
		this.stop_sequence = Integer.parseInt(inputVals[4]);
		this.stop_headsign = null;
		this.pickup_type = Integer.parseInt(inputVals[6]);
		this.drop_off_type = Integer.parseInt(inputVals[7]);
		try {
			this.shape_dist_travelled = Double.parseDouble(inputVals[8]);
		} catch (Exception e) {
			this.shape_dist_travelled = -1;
		}

	}

	public String returnTripId() {
		return "" + this.trip_id;
	}

	public String returnArrivalTime() {
		return this.arrival_time;
	}

	public String returnDepTime() {
		return this.departure_time;
	}

	public String returnStopId() {
		return "" + this.stop_id;
	}

	public String returnStopSequence() {
		return "" + this.stop_sequence;
	}

	public String returnStopHeadsign() {
		if (this.stop_headsign == null) {
			return null;
		} else {
			return this.stop_headsign;
		}
	}

	public String returnPickupType() {
		return "" + this.pickup_type;
	}

	public String returnDropoffType() {
		return "" + this.drop_off_type;
	}

	public String returnShapeDistTravelled() {
		return "" + this.shape_dist_travelled;
	}

}
