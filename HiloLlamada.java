import java.rmi.RemoteException;

public class HiloLlamada extends Thread {

    private MontecarloInterface montecarlo;
    private int pares;
    private int paresDentro;

    public HiloLlamada(String nombre, MontecarloInterface montecarlo, int pares) {
        super(nombre);
        this.montecarlo = montecarlo;
        this.pares = pares;
        this.paresDentro = 0;
        
    }

    @Override
    public void run() {
        try {
            paresDentro = montecarlo.calculatePI(pares);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        
    }

    public int getParesDentro(){
        return this.paresDentro;
    }
}