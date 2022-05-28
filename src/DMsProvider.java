import RPG.ontology.*;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Iterator;

import java.util.ArrayList;

public class DMsProvider extends Agent {
    ArrayList<DungeonMaster> allDMS = new ArrayList<>();
    @Override
    public void setup(){
        addBehaviour(new AssignService(this, Config.DMProvider));
    }

    class SubscribeForDM extends OneShotBehaviour{
        @Override
        public void action()
        {
            DFAgentDescription dfd = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(Config.DM);
            dfd.addServices(sd);
            SearchConstraints sc = new SearchConstraints();
            sc.setMaxResults(Long.MAX_VALUE);

            send(DFService.createSubscriptionMessage(this.myAgent, getDefaultDF(), dfd, sc));
        }
    }

    private class DMRegistrationNotification extends CyclicBehaviour
    {
        DMsProvider agent;
        public DMRegistrationNotification(DMsProvider agent){
            this.agent = agent;
        }
        @Override
        public void action()
        {
            ACLMessage msg = receive(MessageTemplate.MatchSender(getDefaultDF()));

            if (msg != null)
            {
                try
                {
                    DFAgentDescription[] dfds = DFService.decodeNotification(msg.getContent());
                    for (int i=0; i<dfds.length; i++)
                    {
                        Iterator iter = dfds[i].getAllServices();
                        while (iter.hasNext()){
                            ServiceDescription serv = (ServiceDescription) iter.next();
                            if (serv.getType().equalsIgnoreCase(Config.DM)){
                                AID dmsID = new AID(serv.getName(), AID.ISLOCALNAME);
                                sendDMRegisterRequest(dmsID);
                            }
                        }
                    }
                }
                catch (Exception ex) { ex.printStackTrace();}
            }
            block();
        }

        void sendDMRegisterRequest(AID dmsID){
            ContentManager cm = agent.getCM();
            ACLMessage msg = agent.formMSG(dmsID);
            RequestToRegisterDM req = new RequestToRegisterDM();
            req.setMagicWord("Pleaseeee!");
            try{
                cm.fillContent(msg, req);
                send(msg);
            }
            catch (Exception ex)
            {
                System.out.println("A["+getLocalName()+"] Error while building message: " +ex.getMessage());
            }

        }
    }

    class WaitForReqResp extends CyclicBehaviour {
        @Override
        public void action() {
            Ontology onto = RPGOntology.getInstance();
            Codec codec = new SLCodec();
            ContentManager cm = getContentManager();
            cm.registerLanguage(codec);
            cm.registerOntology(onto);

            ACLMessage received = myAgent.receive();

            if (received != null){
                try{
                    String cont = received.getContent();
                    ContentElement c = cm.extractContent(received);

                    if (c instanceof FindDungeonMasters){
                        FindDungeonMasters fDM = (FindDungeonMasters) c;
                    }
                    else if (c instanceof  RegisterDMResponse){
                        RegisterDMResponse resp = (RegisterDMResponse) c;
                        allDMS.add(resp.getDM());
                    }

                }
                catch (Exception ex)
                {
                    System.out.println("A["+myAgent.getName()+ "] Ontology parsing error: "+ ex.getMessage());
                }
            }
        }

        DungeonMastersListResponse processRequestForDMs(FindDungeonMasters fDM){
            DungeonMastersListResponse dmResp = new DungeonMastersListResponse();
            for (DungeonMaster dm: allDMS){
                if (dm.getDifficulty().equals(fDM.getDifficulty())){
                    dmResp.addDMsList(dm);
                }
            }
            return dmResp;
        }

    }


    public ContentManager getCM(){
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        return cm;
    }
    public ACLMessage formMSG(AID sendTO){
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ACLMessage omsg = new ACLMessage(ACLMessage.INFORM);
        omsg.setLanguage(codec.getName());
        omsg.setOntology(onto.getName());
        omsg.clearAllReceiver();
        omsg.addReceiver(sendTO);
        return omsg;
    }
}
