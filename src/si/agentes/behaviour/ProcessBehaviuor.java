package si.agentes.behaviour;

import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessBehaviuor extends SimpleBehaviour {

    private static final MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);

    public ProcessBehaviuor(Agent agent) {
        super(agent);
    }

    @Override
    public void action() {
        while (true) {
            ACLMessage aclMessage = myAgent.receive(mt);
            if (aclMessage != null)  {
                // System.out.println();
                // System.out.println(this.getAgent().getLocalName()+": Recibo el mensaje: \n" + aclMessage.getContent());

                String returnMsg = null;
                returnMsg = executeScript(aclMessage.getContent());

                ACLMessage mr = aclMessage.createReply();
                if (returnMsg == null) {
                    mr.setContent("Error");
                } else {
                    mr.setContent(returnMsg);
                }

                mr.setPerformative(ACLMessage.INFORM);
                this.getAgent().send(mr);
            } else {
                this.block();
            }
        }
    }

    @Override
    public boolean done() {
        return false;
    }

    private String executeScript(String msg) {
        String[] cmd = {
                "/bin/bash",
                "-c",
                "source /home/rosende95/Workspace/python/virt/ia/bin/activate && python /home/rosende95/Workspace/java/SI/Twitter-SI/twittersvm.py \"" + msg + "\""
        };
        String output = msg + "\n";
        Process proc;
        try {
            proc = Runtime.getRuntime().exec(cmd);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = stdInput.readLine()) != null) {
                if (!(line.compareTo("") == 0))
                    output += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return output;
    }
}
