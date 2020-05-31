package si.interfaz;

import jade.core.Agent;
import si.agentes.TwitterListerner;

public class MainInterfaz extends Thread {

    private TwitterListerner listener;
    private Interfaz interfaz;

    public MainInterfaz(TwitterListerner listener) {
        this.listener = listener;
    }
    @Override
    public void run() {
        this.interfaz = new Interfaz(listener);
    }

    public Interfaz getInterfaz() {
        return this.interfaz;
    }
}
