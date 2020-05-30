package si.agentes;

import jade.core.Agent;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import twitter4j.*;

public class HelloWorldAgent extends Agent {

    protected void setup() {
        System.out.println("Hello world! I'm an agent!");
        System.out.println("My local name is " + getAID().getLocalName());
        System.out.println("My GUID is " + getAID().getName());
        System.out.println("My addresses are " + String.join(",", getAID().getAddressesArray()));
    }
}
