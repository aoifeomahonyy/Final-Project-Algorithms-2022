
public class BusStops {

	public int stop_id;
	public String stop_code;
	public String stop_name;
	public String stop_desc;
	public double stop_lat;
	public double stop_lon;
	public String zone_id;

	BusStops(String input) {

		String[] inputVals = input.split(",");
		this.stop_id = Integer.parseInt(inputVals[0]);
		this.stop_code = inputVals[1];
		this.stop_name = changeStopNames(inputVals[2]);
		this.stop_desc = inputVals[3];
		this.stop_lat = Double.parseDouble(inputVals[4]);
		this.stop_lon = Double.parseDouble(inputVals[5]);
		this.zone_id = inputVals[6];
	}

	/*
	 * In order for this to provide meaningful search functionality please move
	 * keywords flagstop, wb, nb, sb, eb from start of the names to the end of the
	 * names of the stops when reading the file into a TST (eg “WB HASTINGS ST FS
	 * HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)
	 */
	public static String changeStopNames(String input) {
		// for 'WB'
		if (input.charAt(0) == 'W' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " WB";
		}
		// for 'NB'
		if (input.charAt(0) == 'N' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " NB";
		}
		// for 'SB'
		if (input.charAt(0) == 'S' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " SB";
		}
		// for 'EB'
		if (input.charAt(0) == 'E' && input.charAt(1) == 'B' && input.charAt(2) == ' ') {
			return input.substring(3) + " EB";
		}
		// for 'FLAGSTOP'
		if (input.charAt(0) == 'F' && input.charAt(1) == 'L' && input.charAt(2) == 'A' && input.charAt(3) == 'G'
				&& input.charAt(4) == 'S' && input.charAt(5) == 'T' && input.charAt(6) == 'O'
				&& input.charAt(7) == 'P') {
			return input.substring(8) + " FLAGSTOP";
		}
		return input;
	}

	// Functions returning stop data for part 2 (making them all strings so that an
	// array list of strings can be outputted to user)

	public String returnStopId() {
		return "" + this.stop_id;
	}

	public String returnStopCode() {
		return this.stop_code;
	}

	public String returnStopName() {
		return this.stop_name;
	}

	public String returnStopDesc() {
		return this.stop_desc;
	}

	public String returnStopLat() {
		return "" + this.stop_lat;
	}

	public String returnStopLon() {
		return "" + this.stop_lon;
	}

	public String returnZoneId() {
		return this.zone_id;
	}

}