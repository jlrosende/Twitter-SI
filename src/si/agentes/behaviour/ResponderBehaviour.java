package si.agentes.behaviour;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import si.interfaz.Interfaz;

public class ResponderBehaviour extends SimpleBehaviour
{
    // Establecemos un filtro para leer mensajes de tipo REQUEST
    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.INFORM);

    private Interfaz interfaz;

    public ResponderBehaviour(Agent agent, Interfaz interfaz) {
        super(agent);
        this.interfaz = interfaz;
    }

    public void action()
    {
        while (true) {
            ACLMessage aclMessage = this.getAgent().receive(mt);
            if (aclMessage != null) {
                System.out.println();
                System.out.println(this.getAgent().getLocalName() + ": Recibo el mensaje: \n" + aclMessage);
                this.interfaz.output.append(aclMessage.getContent());
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
