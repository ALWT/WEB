import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Farmacie;
import classes.Med_Farmacie;
import implement.DBManageReal;
import interfaces.DBManageinter;

import classes.*;
import interfaces.*;
import implement.*;
import java.rmi.registry.*;
import java.sql.SQLException;

@WebServlet("/Cauta_Med")
public class Cauta_Med extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cauta_Med() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nume = request.getParameter("nume");
		
		String resp="";
		
		for(Server s: Connection.getServ_list())
        {
			try 
			{  
				/*Registry r= LocateRegistry.getRegistry(s.getHost());
				DBManageinter db =(DBManageinter)r.lookup("DBManage-"+s.getDbase());*/
	            DBManageinter db = new DBManageReal("localhost","pad_bd","root","");
	            MedicamentReal medd = new MedicamentReal("localhost","pad_bd","root","");
	            
	            Medicament med = db.getMedicamentName(nume);//caut numele medicamentului in tabela Medicament
	            int id = med.getID();
	            int cantitate = Integer.parseInt(request.getParameter("nr"));//cantitate medicament
	            int ok=0; //pp ca nu exista medicament in nicio farmacie
	            
	            if (med!=null)
	            {
	            	resp +="<div class=\"poza\"><img src=img\\"+med.getPoza()+" alt=medicament height=\"148\" width=\"248\"></div>";
    				resp +="<div class=\"descriere\">"+med.getDescriere()+"<br><br>"+"<div style=\"font-size: 25px\"><b> Pret :"+med.getPret()+" lei</b>"+"</div></div>";

    				List<Farmacie> fl = medd.getFarm(id);
	            	if(fl != null)
            		{
	            		String farmacie = ""; 
            			for(Farmacie f : fl)
        				{
            				Med_Farmacie  meddd = medd.getMed_Farm(id, f.getID());
        					if(meddd.getCantitate() >= cantitate)
        					{
        						farmacie += f.getNume()+"<br>";
        						ok=1;
        					}
        				}
            			
            			if (ok==0)
            				resp +="<div class=\"disponibilitate\">"+"Nu exista in stoc "+"<br>"+"</div>";
            			else
            			resp +="<div class=\"disponibilitate\">"+"Disponibil in :<div style=\"margin-left: 125px\">"+farmacie+"<br>"+"</div></div>";
            		}
	            } 
            } 
			catch(NumberFormatException e)
	      	{
				resp+="<div class=\"disponibilitate\">Cantitate invalida</div>";
				System.err.println("Cantitate invalida");
	      	}
			catch(NullPointerException e)
	      	{
				resp+="<div class=\"disponibilitate\">Medicament invalid</div>";
				System.err.println("Medicament invalid");
	      	}
			catch (Exception e) 
			{
                System.err.println("ComputeEngine exception:");
                e.printStackTrace();
            }
        }
		response.getWriter().println(resp);	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
