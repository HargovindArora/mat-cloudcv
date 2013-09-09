
import java.io.IOException;

import org.idevlab.rjc.RedisNode;
import org.idevlab.rjc.RedisOperations;
import org.idevlab.rjc.ds.SimpleDataSource;

import io.socket.*;

public class Sockets_CCV {
	

	
	public Sockets_CCV() 
	{	
				

	}

	public static void main(String[] args) throws IOException 
	{
		//Sockets_CCV obj = new Sockets_CCV("/home/dexter/projects/vt/mcloudcv/jcloudcv/src/config.json", null, null, null);
		
		ConfigParser cp = new ConfigParser("/home/dexter/projects/vt/mcloudcv/config.json");
		cp.readConfigFile();
		cp.parseArguments("","","");
		cp.getParams();
		
		try 
		{
			UploadData udobj =new UploadData(cp);
			
			SocketConnection sock = new SocketConnection(cp.executable_name, cp.output_path);
			sock.socketIOConnection();
		
			RedisOperations redis = new RedisNode(new SimpleDataSource("localhost"));
		    redis.publish("intercomm", "message");  
		    
		    new Thread(udobj).start();
		} 
		catch (Exception e) 
		{
			
			e.printStackTrace();
		}

	}
	
}