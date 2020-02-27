import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;


public class MontecarloImpl extends UnicastRemoteObject implements MontecarloInterface {
  
   private static final long serialVersionUID = 1L;

   public MontecarloImpl() throws RemoteException {
      super( );
   }
  
   public int calculatePI(int nPares) throws RemoteException {
      Random seed = new Random(System.currentTimeMillis());
      double x;
      double y;
      int contadorPares = 0;

      for( int i = 0 ; i < nPares ; i++ ){
         x =  seed.nextDouble();
         y =  seed.nextDouble();

         if((x*x+y*y) <= 1){
            contadorPares++;
         }
      }
      return contadorPares;
   }

} 
