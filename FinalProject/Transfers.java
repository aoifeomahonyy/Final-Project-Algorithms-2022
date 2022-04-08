
public class Transfers {

	public int from_stop_id;
	public int to_stop_id;
	public String transfer_type;
	public int min_transfer_time;

	public Transfers(String input) {
		String[] inputVals = input.split(",");
		this.from_stop_id = Integer.parseInt(inputVals[0]);
		this.to_stop_id = Integer.parseInt(inputVals[1]);
		this.transfer_type = inputVals[3];
		this.min_transfer_time = Integer.parseInt(inputVals[3]);
	}
	
	public String returnFromStopId()
	{
		return "" + this.from_stop_id;
	}
	
	public String returnToStopId()
	{
		return "" + this.to_stop_id;
	}
	
	public String returnTransferType()
	{
		return "" + this.transfer_type;
	}
	
	public String returnMinTransferType()
	{
		return "" + this.min_transfer_time;
	}
}
