import java.io.*;
import java.rmi.*;

public class Cliente {

   public static void main(String args[]) {
      String hostName, numPuerto, registryURL;
      int numHilos = 0, pares;
      InputStreamReader is = new InputStreamReader(System.in);
      BufferedReader br = new BufferedReader(is);
      MontecarloInterface montecarlo;
      Thread[] hilos;
      int paresTotales = 0;
      
      try {
         System.out.println("Introduzca el nº de servidores con los que quiere trabajar:");
         numHilos = Integer.parseInt(br.readLine());
      
         hilos = new Thread[numHilos];
         for( int i = 0; i < numHilos; i++){
            System.out.print("\n-Servidor["+i+"] Introduzca el dominio: ");
            hostName = br.readLine();

            System.out.print("\n-Servidor["+i+"] Introduzca el nº de puerto: ");
            numPuerto = br.readLine();

            registryURL = "rmi://" + hostName+ ":" + numPuerto + "/pi";
            montecarlo = (MontecarloInterface)Naming.lookup(registryURL);

            System.out.print("\n-Servidor["+i+"] Introduzca el nº de pares ordenados: ");
            pares = Integer.parseInt(br.readLine());
            paresTotales += pares;

            hilos[i] = new HiloLlamada(new String("Hilo"+i), montecarlo, pares);
            hilos[i].start();
         }
         //Hace que el hilo principal espere a terminar cuando se acabe la 
         //ejecucion de todos los hilos que se han creado
         for( int i = 0; i < numHilos; i++){
            hilos[i].join();
         }

         //Calculamos Pi cogiendo el resultado de cada hilo 
         //paresDentro será el nº total de pares que han verificado la desigualdad
         int paresDentro = 0;

         for( int i = 0; i < numHilos; i++){
            paresDentro+= ((HiloLlamada) hilos[i]).getParesDentro();
            System.out.println(((HiloLlamada)hilos[i]).getParesDentro());
         }

         double PI=(double)paresDentro/(double)paresTotales;
         System.out.println("PI: "+4*PI);

      }catch (NumberFormatException | IOException | NotBoundException | InterruptedException e) {
         e.printStackTrace();
      }
   }
}
