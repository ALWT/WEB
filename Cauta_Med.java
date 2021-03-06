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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String nume = request.getParameter("nume");
		
		String resp="";
		int i=1,contor =0;
		for(Server s: Connection.getServ_list())
        { 
			contor++;
			try 
			{  
				Registry r= LocateRegistry.getRegistry(s.getHost());
				DBManageinter db =(DBManageinter)r.lookup("DBManage-"+s.getDbase());
				Medicamentinter medd =(Medicamentinter)r.lookup("Medicament-"+s.getDbase());
			    //DBManageReal db = new DBManageReal("localhost",s.getDbase(),"root","");
	            //MedicamentReal medd = new MedicamentReal("localhost",s.getDbase(),"root","");
	            System.out.println("med name:"+nume);
	            Medicament med = db.getMedicamentName(nume);//caut numele medicamentului in tabela Medicament
	            System.out.println(med==null);
	            int id = med.getID();
	            int cantitate = Integer.parseInt(request.getParameter("nr"));//cantitate medicament
	            int ok=0; //pp ca nu exista medicament in nicio farmacie
	            
	            if (med!=null&&!ShoppingList.contains(med.getNume()))
	            {  	
	            	if(contor==1)
	            	{
	            		resp +="<div class=\"poza\"><img src=img\\"+med.getPoza()+" alt=medicament height=\"148\" width=\"248\"></div>";
	            		resp +="<div class=\"descriere\">Server : "+(i++)+"<br>"+med.getDescriere()+"<br><br>"+"<div style=\"font-size: 25px\"><b> Pret :"+med.getPret()+" lei</b>"+"</div></div>";

	            	}
    				
    				List<Farmacie> fl = medd.getFarm(id);
    				System.out.println(fl.size());
	            	if(fl != null)
            		{
	            		String farmacie = ""; 
            			for(Farmacie f : fl)
        				{
            				Med_Farmacie  meddd = medd.getMed_Farm(id, f.getID());
        					System.out.println(meddd.getCantitate());
            				if(meddd.getCantitate() >= cantitate)
        					{
        						farmacie += f.getNume()+"<br>";
        						ok=1;
        					}
        				}
            			
            			if (ok==0)
            				resp +="<div class=\"disponibilitate\">"+"Nu exista in stoc "+"<br>"+"</div>";
            			else
            			{
							
							String aux="<button class=\"btn btn-dark\" style=\"margin-left: 1200px\" onClick=\"buymedicament('"+med.getNume()+"','"+s.getDbase()+"','"+s.getHost()+"');\">Adauga in WishList "+med.getPret()+" Ron"+s.getDbase()+"</button>";
							System.out.println(aux);
							resp+=aux;
 
							resp +="<div class=\"disponibilitate\">"+"Disponibil in :<div style=\"margin-left: 125px\">"+farmacie+"<br>"+"</div></div>";
            			}
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
			resp+="<br><br><br>";
        }
		response.getWriter().println(resp);	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
