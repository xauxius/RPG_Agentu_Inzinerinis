import RPG.ontology.*;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
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
    public static final int DIFFICULTY = 1;
    public static final int DM = 2;
    public static final int GAMING = 3;
    //--Variables--

    GameGUI myGui = null;
    ArrayList<DungeonMaster> availableDMs = new ArrayList<>();
    AID DMsProvider;
    String[] DifficultiesSelection = new String[]{"Easy", "Medium", "Hard"};
    AttackOptions atOpt;
    MoveOptions mvOpt;
    int aLen;
    int mLen;
    AID DungeonMasterAID;

    @Override
    public void setup() {
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
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        int cmd = ge.getType();
        if (cmd == PlayGUI.DIFFICULTY) {
            // Kas ivyksta pasirinkus difficulty
            availableDMs = new ArrayList<>();
            addBehaviour(new InitiateDMSearch(this, ge.getParameter(0).toString()));
        }
        else if (cmd == PlayGUI.DM) {
            // Kas ivyksta pasirinkus Dungeon Masteri
            //Siunciam dungeon master zinute, kad priimtu zaisti
            String name = ge.getParameter(0).toString();
            AID DmAID = new AID(name, AID.ISLOCALNAME);
            ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
            request.setLanguage(codec.getName());
            request.setOntology(onto.getName());
            request.clearAllReceiver();
            request.addReceiver(DmAID);
            GameAction msg = new GameAction();
            msg.setWantToJoin(true);
            try {
                cm.fillContent(request, msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            send(request);
        }
        else if (cmd == PlayGUI.GAMING) {
            // Kas ivyksta pasirinkus ejima zaidime
            int index = (int) ge.getParameter(0); // Pasirinktas action
            if (index < mLen) {
                MoveAction action = new MoveAction();
                action.setDirection((String) mvOpt.getDir().get(index));
                ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                response.setLanguage(codec.getName());
                response.setOntology(onto.getName());
                response.clearAllReceiver();
                response.addReceiver(DungeonMasterAID);
                SituationResponse msg = new SituationResponse();
                msg.setFinalAction(action);
                try {
                    cm.fillContent(response, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                send(response);
            }
            else {
                int attackInd = index - mLen;
                AttackOptions action = new AttackOptions();
                ACLMessage response = new ACLMessage(ACLMessage.INFORM);
                response.setLanguage(codec.getName());
                response.setOntology(onto.getName());
                response.clearAllReceiver();
                response.addReceiver(DungeonMasterAID);
                SituationResponse msg = new SituationResponse();
                msg.setFinalAction(((AttackEnemy) atOpt.getAttackEnemyy().get(attackInd)));
                try {
                    cm.fillContent(response, msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                send(response);
            }
        }
    }

    //--Behaviours--
    class InitiateDMSearch extends OneShotBehaviour {
        PlayGUI agent;
        String difficulty = "";

        public InitiateDMSearch(PlayGUI agent, String difficulty) {
            this.agent = agent;
            this.difficulty = difficulty;
        }

        @Override
        public void action() {
            ContentManager cm = agent.getCM();
            ACLMessage msg = agent.formMSG(agent.DMsProvider, ACLMessage.INFORM);
            FindDungeonMasters fDM = new FindDungeonMasters();
            fDM.setDifficulty(difficulty);
            try {
                cm.fillContent(msg, fDM);
                send(msg);
            } catch (Exception ex) {
                System.out.println("A[" + getLocalName() + "] Error while building message: " + ex.getMessage());
            }
        }
    }

    class SearchForProvider extends OneShotBehaviour {
        @Override
        public void action() {
            while (DMsProvider == null) { //Kebloka vieta gali but su situo, nes nezinia kada null kada ne jis gali but
                try {
                    SearchForService();
                } catch (FIPAException ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void SearchForService() throws FIPAException {
            DFAgentDescription dfd = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(Config.DMProvider);
            dfd.addServices(sd);

            try {
                DFAgentDescription[] result = DFService.search(myAgent, dfd);
                for (int i = 0; i < result.length; i++) {
                    DMsProvider = result[i].getName();
                }
            } catch (FIPAException ex) {
                ex.printStackTrace();
            }
        }
    }

    class ProcessDMList extends CyclicBehaviour {
        PlayGUI agent;

        public ProcessDMList(PlayGUI agent) {
            this.agent = agent;
        }

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive();

            if (msg != null) {
                ContentManager cm = agent.getCM();
                try {
                    ContentElement c = cm.extractContent(msg);
                    if (c instanceof DungeonMastersListResponse) {
                        DungeonMastersListResponse dmsList = (DungeonMastersListResponse) c;
                        Iterator dmIter = dmsList.getAllDMsList();
                        String[] DungeonMastersNames = new String[dmsList.getDMsList().size()];
                        int i = 0;
                        while (dmIter.hasNext()) {
                            availableDMs.add((DungeonMaster) dmIter.next());
                            DungeonMastersNames[i] = availableDMs.get(i).getName();
                            i++;
                        }
                        if (i == 0) {
                            System.out.println("Dungeon masters nerasta");
                            myGui.ChangeSelection(new String[]{"Dungeon masters pasirinkto lygio nera, Pasirinkite kita..."});
                        }
                        else {
                            myGui.ChangeSelection(DungeonMastersNames);
                            System.out.println("Dungeon masters names listed");
                        }

                    }
                    if (c instanceof SituationResponseRequest) {
                        SituationResponseRequest response = (SituationResponseRequest) c;
                        myGui.ChangeMap(response.getMap());
                        myGui.ChangePrompt(response.getPropmpt());
                        AvailableOptions opt = response.getOptions();
                        atOpt = opt.getAttOpts();
                        mvOpt = opt.getMvOpts();
                        String[] aopt = attOptsStr(atOpt);
                        String[] mopt = moveOptsStr(mvOpt);
                        aLen = aopt.length;
                        mLen = mopt.length;
                        String[] result = new String[aLen + mLen];
                        System.arraycopy(mopt, 0, result, 0, mLen);
                        System.arraycopy(aopt, 0, result, mLen, aLen);
                        myGui.ChangeLiveNumber(response.getHealth());
                        DungeonMasterAID = msg.getSender();
                        boolean won = response.getGameWon();
                        boolean lost = response.getGameLost();
                        myGui.ChangeSelection(result);
                        if (won) {
                            myGui.ChangeMap("\n\n\n\n\n    Congratulations \n        You won!");
                            String[] strings = {""};
                            myGui.ChangeSelection(strings);
                        }
                        else if (lost) {
                            myGui.ChangeMap("\n\n\n\n\n      Game over :(");
                            String[] strings = {""};
                            myGui.ChangeSelection(strings);
                        }


                    }
                } catch (Exception ex) {
                    System.out.println("Sad thing at dm list");
                }
            }
        }
    }
    //----


    //--Simple Methods--
    public ContentManager getCM() {
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        return cm;
    }

    public ACLMessage formMSG(AID sendTO, int aclType) {
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ACLMessage omsg = new ACLMessage(ACLMessage.INFORM);
        omsg.setLanguage(codec.getName());
        omsg.setOntology(onto.getName());
        omsg.clearAllReceiver();
        omsg.addReceiver(sendTO);
        return omsg;
    }

    void say(String text) {
        System.out.println("A[" + getLocalName() + "]: " + text);
    }

    String[] moveOptsStr(MoveOptions opts) {
        String[] mvOpts = new String[opts.getDir().size()];
        Iterator optIter = opts.getAllDir();
        int i = 0;
        while (optIter.hasNext()) {
            mvOpts[i] = "Move " + optIter.next().toString();
            i++;
        }
        return mvOpts;
    }

    String[] attOptsStr(AttackOptions opts) {
        String[] attOpts = new String[opts.getAttackEnemyy().size()];
        Iterator attIter = opts.getAllAttackEnemyy();
        int i = 0;
        while (attIter.hasNext()) {
            AttackEnemy attEn = (AttackEnemy) attIter.next();
            attOpts[i] = "Attack " + attEn.getEnemyID() + ", with attack: " + attEn.getAttackType().getAttackName() + ", Damage: " + attEn.getAttackType().getDamage() + ", Accuracy: " + attEn.getAttackType().getAccuracy();
            i++;
        }
        return attOpts;
    }
    //----
}
