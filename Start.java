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
import java.rmi.registry.*;


@WebServlet(description = "ceva", urlPatterns = { "/Start" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Start() {
        super();}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String docType ="<!doctype html public>\n";
		String nume=request.getParameter("nume");
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
		String resp="";
		for(Server s:Connection.getServ_list())
        {try {String name1 = "DB-"+s.getDbase();
            String name2 = "Farmacie-"+s.getDbase();
            String name3 = "Stoc-"+s.getDbase();
            String name4 = "Produs-"+s.getDbase();
            Registry registry = LocateRegistry.getRegistry(s.getHost());
            DBManageinter db = (DBManageinter) registry.lookup(name1);
            Farmacieinter farm = (Farmacieinter) registry.lookup(name2);
            Stocinter stoc = (Stocinter) registry.lookup(name3);
            Produsinter prod = (Produsinter) registry.lookup(name4);
            List<Farmacie> fl=db.getFarmacii();
            if(fl!=null)
            for(Farmacie f:fl)
            {resp+=f.getNume()+" "+f.getAdresa()+" "+f.getOras()+" "+f.getProgram()+/*" "+f.getDBase()+*/"\n";
            List<Produs> pl=farm.getProductsFarmacie(f);
            if(pl!=null);
            {for(Produs p:pl)
            {resp+=p.getNume()+" "+p.getClasa()+"\n";
            Stoc st=prod.getStoc(p.getID(), f.getID());
            if(st!=null)
            resp+=st.getCantitate()+" "+st.getPret()+"\n";
            }}	
            }
            } catch (Exception e) {
                System.err.println("ComputeEngine exception:");
                e.printStackTrace();
            }
        resp+="\n";
        }
		response.getWriter().append("Served at: ").append(request.getContextPath()).append("\n");
		response.getWriter().println(resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
