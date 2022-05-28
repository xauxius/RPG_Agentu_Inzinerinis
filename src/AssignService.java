import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AssignService extends OneShotBehaviour {
    DFAgentDescription dfd;
    String serv;

    public AssignService(Agent a, String serv) {
        super(a);
        this.serv = serv;
    }

    @Override
    public void action() {
        dfd = new DFAgentDescription();
        addService();
        try {
            DFService.register(myAgent, dfd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    void addService() {
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serv);
        sd.setName(serv + "-" + myAgent.getLocalName());
        dfd.addServices(sd);
    }

}
