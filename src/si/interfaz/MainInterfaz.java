package si.interfaz;

import jade.core.Agent;
import si.agentes.TwitterListerner;

public class MainInterfaz extends Thread {

    private TwitterListerner listener;

    public MainInterfaz(TwitterListerner listener) {
        this.listener = listener;
    }
    @Override
    public void run() {
        Interfaz interfaz = new Interfaz(listener);
    }
}
