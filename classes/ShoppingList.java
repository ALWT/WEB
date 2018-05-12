package classes;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList 
{
	public static  List<MedicamentShop> buylist=new ArrayList<MedicamentShop>();
	
	public  static void addMedicament(Medicament m,int quant)
	{
		buylist.add(new MedicamentShop(m,quant));
	}
	
	public static List<MedicamentShop> getMedicaments()
	{	
		return buylist;
	}
	
	public static void checkout()
	{
		buylist=new ArrayList<MedicamentShop>();
	}
	
	public static  boolean contains(String  med)
	{
		for(MedicamentShop ms:buylist)
			if(ms.m.getNume().equals(med))
				return true;
		return false;
	}
}
