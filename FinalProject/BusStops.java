
public class BusStops {

	public int stop_id;
	public int stop_code;
	public String stop_name;
	public String stop_desc;
	public double stop_lat;
	public double stop_lon;
	public String zone_id;
	public String stop_url;

	BusStops(String input) {

		String[] inputVals = input.split(",");
		try {
			this.stop_id = Integer.parseInt(inputVals[0]);
		} catch (Exception e) {
			this.stop_id = -1;
		}

		try {
			this.stop_code = Integer.parseInt(inputVals[1]);
		} catch (Exception e) {
			this.stop_id = -1;
		}

		this.stop_name = changeStopNames(inputVals[3]);
		this.stop_desc = inputVals[4];

		try {
			this.stop_lat = Integer.parseInt(inputVals[5]);
		} catch (Exception e) {
			this.stop_id = -1;
		}

		try {
			this.stop_lon = Integer.parseInt(inputVals[6]);
		} catch (Exception e) {
			this.stop_id = -1;
		}

		this.zone_id = inputVals[7];
		this.stop_url = inputVals[8];
	}

	/*
	 * In order for this to provide meaningful search functionality please move
	 * keywords flagstop, wb, nb, sb, eb from start of the names to the end of the
	 * names of the stops when reading the file into a TST (eg “WB HASTINGS ST FS
	 * HOLDOM AVE” becomes “HASTINGS ST FS HOLDOM AVE WB”)
	 */
	public String changeStopNames(String input) {
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
		return "" + this.stop_code;
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

	public String returnStopUrl() {
		return this.stop_url;
	}

}