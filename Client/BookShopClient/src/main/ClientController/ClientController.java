package ClientController;

import java.rmi.RemoteException;
import java.util.List;

import dto.FlightDTO;
import Client.RMIServiceLocator;
import ClientUI.ClientConsole;

public class ClientController {
	@SuppressWarnings("unused")
	private RMIServiceLocator rsl;
	public  List<bookDTO> books;
	
	public ClientController(String[] args) throws RemoteException {
		// Add your code HERE - Related to the Service Locator
		rsl=new RMIServiceLocator();
		rsl.setService(args[0], args[1], args[2]);
		
	}
	
    public void getBooks(){
    	try{
    		
    		// Add your code HERE - Related to getting the service and requesting creation of TVProgram	
    		
    		flights = rsl.getServiceB().getFlights();
    		
    		
    	} catch (Exception e){
    		System.err.println("$ Error sending new TV program: " + e.getMessage());
    	}
    }
    
    public boolean login(String name,String pass,int type){
    	try {
			return rsl.getServiceB().checkUserF(name ,pass);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
    	
    }
    
    public Boolean pay(int resinf, int type){
    	try {
			return rsl.getServiceB().payV(1, 2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    public void createAccount(String name,String pass,int type){
    	
    }
    
    public void addPaymentMethod(int number,int code, int type){
    	
    }
    
    public int book(FlightDTO flight){
    	System.out.println("making Reservation");
    	String[] pass=new String[2];
    	pass[0]="Joel";
    	pass[1]="Ander";
    	try {
			return rsl.getServiceB().makeRReservation(flight.getAcronym(), pass);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
    
    public void exit(){
    	System.exit(0);
    }
    
}