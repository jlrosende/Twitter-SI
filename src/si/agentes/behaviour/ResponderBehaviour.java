package si.agentes.behaviour;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class ResponderBehaviour extends SimpleBehaviour
{
    // Establecemos un filtro para leer mensajes de tipo REQUEST
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);
    public ResponderBehaviour(Agent agent) {
        super(agent);
    }

    public void action()
    {
        while (true) {
            ACLMessage aclMessage = this.getAgent().receive(mt);
            if (aclMessage != null) {
                System.out.println();
                System.out.println(this.getAgent().getLocalName() + ": Recibo el mensaje: \n" + aclMessage);
            } else {
                this.block();
            }
        }
    }
    public boolean done()
    {
        return false;
    }
}
