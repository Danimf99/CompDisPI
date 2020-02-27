import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.*;

public class Servidor  {

   public static void main(String args[]) {
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      String portNum, registryURL;

      try{     
         System.out.println("Introduzca el nº de puerto:");
         portNum = (br.readLine()).trim();
         int RMIPortNum = Integer.parseInt(portNum);
         startRegistry(RMIPortNum);
         
         MontecarloImpl exportedObj = new MontecarloImpl();
         registryURL = "rmi://localhost:" + portNum + "/pi";
         Naming.rebind(registryURL, exportedObj);
         
         System.out.println("Servidor registrado con éxito");
      }
      catch (Exception re) {
         System.out.println("Exception in HelloServer.main: " + re);
      } 
  }

   // This method starts a RMI registry on the local host, if it
   // does not already exists at the specified port number.
   private static void startRegistry(int RMIPortNum)
      throws RemoteException{
      try {
         Registry registry = LocateRegistry.getRegistry(RMIPortNum);
         registry.list( );
      }
      catch (RemoteException e) { 
         // No valid registry at that port.
         System.out.println("RMI registry cannot be located at port " + RMIPortNum);
         LocateRegistry.createRegistry(RMIPortNum);
         System.out.println("RMI registry created at port " + RMIPortNum);
      }
   }
} 
