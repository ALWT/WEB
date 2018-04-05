import java.util.ArrayList;
import java.util.List;

public class Connection {
private static List<Server> serv_list=null;
public static List<Server> getServ_list() {
	if(serv_list==null)
	{serv_list=new ArrayList<Server>();
	serv_list.add(new Server("localhost","Test"));}
	return serv_list;}
}
