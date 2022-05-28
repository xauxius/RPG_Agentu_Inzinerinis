import RPG.ontology.FindDungeonMasters;
import RPG.ontology.RPGOntology;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.*;

import java.util.ArrayList;

public class PlayGUI extends GuiAgent {
    //--variables--
    ArrayList<AID> availableDMs = new ArrayList<>();
    AID DMsProvider;
    //----

    @Override
    public void setup(){
        addBehaviour(new AssignService(this, Config.Player));
        addBehaviour(new SearchForProvider());
        say("Hello, i am the player :)");
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }


    //--Behaviours--
    class InitiateDMSearch extends OneShotBehaviour { //Reiks su GUI sujungt jog diff pasirinkt (maybe)
        PlayGUI agent;
        public InitiateDMSearch(PlayGUI agent){
            this.agent = agent;
        }

        @Override
        public void action() {
            ContentManager cm = agent.getCM();
            ACLMessage msg = agent.formMSG(agent.DMsProvider);
            FindDungeonMasters fDM = new FindDungeonMasters();
            fDM.setDifficulty(Config.Easy);
            try{
                cm.fillContent(msg, fDM);
                send(msg);
            }
            catch (Exception ex)
            {
                System.out.println("A["+getLocalName()+"] Error while building message: " +ex.getMessage());
            }
        }
    }
    class SearchForProvider extends OneShotBehaviour{
        @Override
        public void action() {
            try{
                SearchForService();
            }
            catch (FIPAException ex){
                ex.printStackTrace();
            }
        }
        private void SearchForService() throws FIPAException
        {
            DFAgentDescription dfd = new DFAgentDescription();
            ServiceDescription sd  = new ServiceDescription();
            sd.setType(Config.DMProvider);
            dfd.addServices(sd);

            try{
                DFAgentDescription[] result = DFService.search(myAgent, dfd);
                for (int i=0; i<result.length; i++)
                {
                    DMsProvider = result[i].getName();
                }
            }
            catch (FIPAException ex){
                ex.printStackTrace();
            }
        }
    }
    //----


    //--Simple Methods--
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
    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    //----
}
