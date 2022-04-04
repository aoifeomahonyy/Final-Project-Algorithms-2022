import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;

public class Transfers {
	
	public String from_stop_id;
	public String to_stop_id;
	public String transfer_type;
	public int min_transfer_time;
	
	Transfers(String from_stop_id, String to_stop_id, String transfer_type, int min_transfer_time)
	{
		this.from_stop_id = from_stop_id;
		this.to_stop_id = to_stop_id;
		this.transfer_type = transfer_type;
		this.min_transfer_time = min_transfer_time;
	}

}
