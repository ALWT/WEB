import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.*;
import interfaces.*;
import implement.*;
import java.rmi.registry.*;

@WebServlet(description = "ceva", urlPatterns = { "/Start" })
public class Start extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public Start() 
    {
        super();    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String resp="";
		for(Server s: Connection.getServ_list())
        {
			try 
			{  
				/*Registry r= LocateRegistry.getRegistry(s.getHost());
				DBManageinter db =(DBManageinter)r.lookup("DBManage-"+s.getDbase());*/
	            
				DBManageinter db = new DBManageReal("localhost","pad_bd","root","");

	            List<Farmacie> fl = db.getFarmacii();
	            if(fl!=null)
	            	
	            	for(Farmacie f : fl)
			        {
	            			resp+="<div class=\"floating-box\">"+f.getNume()+"<br>"+f.getAdresa()+"<br>"+f.getNrtel()+"<br>"+"</div>";
			        }   	
            } 
			catch (Exception e) 
			{
                System.err.println("ComputeEngine exception:");
                e.printStackTrace();
            }
		
        }
		response.getWriter().println(resp);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
