import RPG.ontology.DungeonMaster;
import RPG.ontology.DungeonMastersListResponse;
import RPG.ontology.FindDungeonMasters;
import RPG.ontology.RPGOntology;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.Iterator;

public class PlayGUI extends GuiAgent {
    public static int STATUS = 0;
    public static final int DIFFICULTY=1;
    public static final int DM=2;
    public static final int GAMING=3;
    public static final int WIN=4;
    public static final int LOST=5;
    //--Variables--

    GameGUI myGui = null;
    ArrayList<DungeonMaster> availableDMs = new ArrayList<>();
    AID DMsProvider;
    String[] DifficultiesSelection = new String[]{"Easy", "Medium", "Hard"};
    @Override
    public void setup()
    {
        addBehaviour(new AssignService(this, Config.Player));
        addBehaviour(new SearchForProvider());
        System.out.println("A[" + getLocalName() + "] Welcome to agent with GUI");
        myGui = new GameGUI(this);
        myGui.setVisible(true);
        myGui.ChangeSelection(DifficultiesSelection);
        STATUS = 1;
        addBehaviour(new ProcessDMList(this));
    }
    //----

    @Override
    protected void onGuiEvent(GuiEvent ge) {
        int cmd = ge.getType();
        if (cmd == PlayGUI.DIFFICULTY){
            // Kas ivyksta pasirinkus difficulty
            availableDMs = new ArrayList<>();
            System.out.println("A["+getLocalName()+"] Difficulty: " +ge.getParameter(0).toString());
            addBehaviour(new InitiateDMSearch(this, ge.getParameter(0).toString()));

        }
        
        else if (cmd == PlayGUI.DM){
            // Kas 5vyksta pasirinkus Dungeon Masteri
        }
        else if (cmd == PlayGUI.GAMING){
            // Kas 5vyksta pasirinkus ejima zaidime
        }

    }


    //--Behaviours--
    class InitiateDMSearch extends OneShotBehaviour { //Reiks su GUI sujungt jog diff pasirinkt (maybe)
        PlayGUI agent;
        String difficulty = "";
        public InitiateDMSearch(PlayGUI agent, String difficulty){
            this.agent = agent;
            this.difficulty = difficulty;
        }

        @Override
        public void action() {
            ContentManager cm = agent.getCM();
            ACLMessage msg = agent.formMSG(agent.DMsProvider);
            FindDungeonMasters fDM = new FindDungeonMasters();
            fDM.setDifficulty(difficulty);
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
            while (DMsProvider == null){ //Kebloka vieta gali but su situo, nes nezinia kada null kada ne jis gali but
                try{
                    SearchForService();
                }
                catch (FIPAException ex){
                    ex.printStackTrace();
                }
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
    class ProcessDMList extends CyclicBehaviour{
        PlayGUI agent;
        public ProcessDMList(PlayGUI agent){
            this.agent = agent;
        }

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive();

            if (msg != null){
                ContentManager cm = agent.getCM();
                try{
                    ContentElement c = cm.extractContent(msg);

                    System.out.println("A["+getLocalName()+"] Message received " +c);
                    if (c instanceof DungeonMastersListResponse){
                        DungeonMastersListResponse dmsList = (DungeonMastersListResponse) c;
                        Iterator dmIter = (Iterator) dmsList.getDMsList();
                        String[] DungeonMastersNames = new String[]{};
                        int i = 0;
                        while (dmIter.hasNext()){
                            availableDMs.add((DungeonMaster) dmIter.next());
                            DungeonMastersNames[i] = availableDMs.get(i).getName() ;
                            i++;
                        }
                        if(i == 0){
                            System.out.println("Dungeon masters nerasta");
                            myGui.ChangeSelection(new String[] {"Dungeon masters pasirinkto lygio nera, Pasirinkite kita..."});
                        }
                        else{
                            myGui.ChangeSelection(DungeonMastersNames);
                            System.out.println("Dungeon masters names listed");
                        }

                    }
                }
                catch (Exception ex){
                    System.out.println("Sad thing at dm list");
                }
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
