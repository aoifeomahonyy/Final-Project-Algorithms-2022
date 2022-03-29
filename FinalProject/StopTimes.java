
public class StopTimes {

	public String trip_id;
	public String arrival_time;
	public String departure_time;
	public String stop_id;
	public int stop_sequence;
	public String stop_headsign;
	public int pickup_type;
	public int drop_off_type;
	public double shape_dist_travelled;
	
	StopTimes(String trip_id, String arrival_time, String departure_time, String stop_id, int stop_sequence, String stop_headsign,
			int pickup_type, int drop_off_type, double shape_dist_travelled)
	{
		this.trip_id = trip_id;
		this.arrival_time = arrival_time;
		this.departure_time = departure_time;
		this.stop_id = stop_id;
		this.stop_sequence = stop_sequence;
		this.stop_headsign = stop_headsign;
		this.pickup_type = pickup_type;
		this.drop_off_type = drop_off_type;
		this.shape_dist_travelled = shape_dist_travelled;
	}
}