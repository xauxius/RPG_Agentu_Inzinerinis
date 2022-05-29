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
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.*;

import java.util.ArrayList;

public class DungeonMasterAg extends Agent {
    //--Variables--
    boolean isStarted = false;
    Map map;
    ArrayList<AID> activeCharacters = new ArrayList<>();
    AID player;
    ArrayList<AID> bots = new ArrayList<>();
    String description;
    String difficulty;
    ArrayList<NPC> viableBots = new ArrayList<>();
    boolean waitingForResp;
    //----


    @Override
    public void setup() {
        processArgs();
        addBehaviour(new AssignService(this, Config.DM));
        addBehaviour(new MainLoop(this));
        say(difficulty);
    }

    void processArgs() {
        Object[] args = getArguments();
        if ((args != null) && (args.length >= 2)) {
            difficulty = args[0].toString();
            description = args[1].toString();
        }
    }

    //--Behaviour Classes--
    //Does all the neccesarry thing when game is waiting to be started
    class MainLoop extends CyclicBehaviour { //Needs implementing: waiting for player, initiating the game
        DungeonMasterAg agent;
        ContentManager cm;
        boolean isStarted;

        public MainLoop(DungeonMasterAg agent) {
            this.agent = agent;
            cm = agent.getCM();
        }

        @Override
        public void action() {
            processRequestMessages();
            if (isStarted) { // If the game is started then two possibilities
                if (waitingForResp) { //The dm is waiting for the action of character whose turn it is
                    processActionResponse(); //Process the action that character presents
                }
                else {
                    sendActionRequest(); //If it is not waiting for response then it is time to request someone to take actions
                }
            }
        }

        void processRequestMessages() {
            MessageTemplate template = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
            ACLMessage mess = agent.receive(template);

            if (mess != null) {
                try {
                    System.out.println(mess.getContent());
                    ContentElement c = cm.extractContent(mess);
                    if (c instanceof RequestToRegisterDM) {
                        manageRegister(mess.getSender());
                    }
                    if (c instanceof GameAction) {
                        GameAction ga = (GameAction) c;
                        manageJoin(mess.getSender(), ga);
                    }
                }
                catch (Exception ex){
                    say("Something bad with gotten message: "+ex.getMessage());
                }
            }
        }


        void processActionResponse() { //Here we should process the action message from character

        }

        void sendActionRequest() { //Here we should ask character whose turn it is to make an action, also we should somehow define on what actions can he make in that situation

        }

        void manageRegister(AID sender) {
            try {
                RegisterDMResponse resp = new RegisterDMResponse();
                DungeonMaster dm = new DungeonMaster();
                dm.setDescription(agent.description);
                dm.setDifficulty(agent.difficulty);
                dm.setName(agent.getLocalName());
                resp.setDM(dm);
                ACLMessage messResp = agent.formMSG(sender);
                cm.fillContent(messResp, resp);
                agent.send(messResp);
            } catch (Exception ex) {
                say("could not register");
            }


        }

        void manageJoin(AID sender, GameAction ga) {
            if (ga.getWantToJoin() == true) {
                GameActionResponse resp = new GameActionResponse();
                if (!isStarted) {
                    resp.setSuccess(true);
                    player = sender;
                    agent.addBehaviour(new LaunchGame(agent));
                }
                else {
                    resp.setSuccess(false);
                }
                ACLMessage respMess = agent.formMSG(sender);
                try {
                    cm.fillContent(respMess, resp);
                    send(respMess);
                } catch (Exception ex) {
                    say("could not send join response");
                }

            }
        }
    }

    //Launches bot agents, prepares map if necesary
    class LaunchGame extends OneShotBehaviour {
        DungeonMasterAg dm;

        public LaunchGame(DungeonMasterAg dm) {
            this.dm = dm;
        }

        @Override
        public void action() {
            AgentContainer cont = myAgent.getContainerController();
            for (int i = 0; i < 5; i++) {
                try {
                    AgentController bot = cont.createNewAgent("Goblin" + bots.size(), "NPC", new Object[0]);
                    bot.start();
                    bots.add(new AID(bot.getName(), AID.ISLOCALNAME));
                } catch (Exception e) {
                    say("Something ain't right");
                }
            }

            map = new Map(player, bots);
            AvailableOptions options = new AvailableOptions();
            MoveOptions move = new MoveOptions();
            AttackOptions attack = new AttackOptions();
            move.addDir("UP");
            move.addDir("LEFT");
            move.addDir("RIGHT");
            options.setMvOpts(move);
            options.setAttOpts(attack);
            sendSituationMessage(player, map, options, "Game is starting");

        }
    }
    //----

    public void sendSituationMessage(AID sendTO, Map map, AvailableOptions options, String promt){
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        ACLMessage response = new ACLMessage(ACLMessage.INFORM);
        response.setLanguage(codec.getName());
        response.setOntology(onto.getName());
        response.clearAllReceiver();
        response.addReceiver(sendTO);
        SituationResponseRequest msg = new SituationResponseRequest();
        msg.setPropmpt(promt);
        msg.setOptions(options);
        msg.setMap(map.toString());
        try {
            cm.fillContent(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        send(response);
        System.out.println("Zinute issiusta su mapais"+response);
    }
    //--Simple Methods--

    public ContentManager getCM() {
        Ontology onto = RPGOntology.getInstance();
        Codec codec = new SLCodec();
        ContentManager cm = getContentManager();
        cm.registerLanguage(codec);
        cm.registerOntology(onto);
        return cm;
    }

    public ACLMessage formMSG(AID sendTO) {
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

    void say(String text, String beh) {
        System.out.println("A[" + getLocalName() + "|" + beh + "]: " + text);
    }
    //----
}
