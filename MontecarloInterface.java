
import java.rmi.*;


public interface MontecarloInterface extends Remote {

   public int calculatePI(int nPares) throws java.rmi.RemoteException;

} 
