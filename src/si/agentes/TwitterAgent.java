package si.agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
import si.agentes.behaviour.ResponderBehaviour;
import si.interfaz.MainInterfaz;
import si.twitter.GetUserTimeline;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

public class TwitterAgent extends Agent implements TwitterListerner {

    @Override
    public void setup() {
        System.out.println("Inicializacion del agente TwitterAgent.");
        System.out.println("[TwitterAgent] AID: " + this.getAID());
        System.out.println("[TwitterAgent] Nombre AID: " + this.getAID().getName());
        MainInterfaz interfaz = new MainInterfaz(this);
        interfaz.run();
        this.addBehaviour(new ResponderBehaviour(this, interfaz.getInterfaz()));
    }

    @Override
    protected void takeDown()
    {
        System.out.println("[TwitterAgent]: Bye.....");
        this.doDelete();
    }

    public void getUserTimeline(String username)
    {
        try {
            List<Status> statuses = GetUserTimeline.getTimeLine(username);
            for (Status status : statuses) {
                this.sendMsg(status.getText());
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    private void sendMsg(String textMsg) {
        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        AID r = new AID();
        r.setName("ProcessAgent@192.168.43.141:1200/JADE");
        r.addAddresses("http://192.168.43.141:7778/acc");
        msg.addReceiver(r);
        msg.setContent(textMsg);
        this.send(msg);
    }
}

