package si.agentes;

import jade.core.Agent;
import si.agentes.behaviour.ProcessBehaviuor;

public class ProcessAgent extends Agent {

    @Override
    public void setup() {
        System.out.println("Inicializacion del agente ProccessAgent.");
        System.out.println("[ProccessAgent] AID: " + this.getAID());
        System.out.println("[ProccessAgent] Nombre AID: " + this.getAID().getName());
        addBehaviour(new ProcessBehaviuor(this));
    }

    @Override
    protected void takeDown()
    {
        System.out.println("[ProccessAgent]: Bye.....");
        this.doDelete();
    }
}
