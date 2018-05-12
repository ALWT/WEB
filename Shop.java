import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.Farmacie;
import classes.Medicament;
import classes.ShoppingList;
import implement.DBManageReal;
import interfaces.DBManageinter;
import interfaces.Medicamentinter;

/**
 * Servlet implementation class Shop
 */
@WebServlet("/Shop")
public class Shop extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Shop() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String med=request.getParameter("med");
		String nr=request.getParameter("nr");
		String bd =request.getParameter("bd");
		String host =request.getParameter("host");
			try 
			{
				int quant=Integer.parseInt(nr);
				Registry r= LocateRegistry.getRegistry(host);
			    DBManageinter db =(DBManageinter)r.lookup("DBManage-"+bd);
			    //DBManageReal db = new DBManageReal("localhost",bd,"root","");
				Medicament m=db.getMedicamentName(med);
				
				if(m!=null)
				{
					ShoppingList.addMedicament(m,quant);
					System.out.println(m.getNume());
				}
				//response.getWriter().append("Served at: ").append(request.getContextPath());
		     }catch(Exception e)   
			{
		    	 e.printStackTrace();
			}
		
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
