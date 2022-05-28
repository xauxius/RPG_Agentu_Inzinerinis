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
import jade.wrapper.*;

import java.util.ArrayList;

public class DungeonMasterAg extends Agent{
    //--Variables--
    boolean isStarted = false;
    Map map;
    ArrayList<AID> activeCharacters = new ArrayList<>();
    ArrayList<AID> activePlayers = new ArrayList<>();
    ArrayList<AID> bots = new ArrayList<>();
    String description;
    String difficulty;
    ArrayList<NPC> viableBots = new ArrayList<>();
    //----


    @Override
    public void setup(){
        processArgs();
        addBehaviour(new AssignService(this, Config.DM));
        addBehaviour(new LaunchGame());

    }

    void processArgs(){
        Object[] args = getArguments();
        if ((args!=null) && (args.length>2))
        {
            difficulty = args[0].toString();
            description = args[1].toString();
        }
    }

    //--Behaviour Classes--
    //Does all the neccesarry thing when game is waiting to be started
    class MainLoop extends CyclicBehaviour{ //Needs implementing: waiting for player, initiating the game
        DungeonMasterAg agent;
        ContentManager cm;
        boolean isStarted;
        public MainLoop(DungeonMasterAg agent){
            this.agent = agent;
            cm = agent.getCM();
        }

        @Override
        public void action() {
            processRequestMessages();
            if (isStarted){ // If the game is started then two possibilities
                if (waitingForResp){ //The dm is waiting for the action of character whose turn it is
                    processActionResponse(); //Process the action that character presents
                }
                else{
                    sendActionRequest(); //If it is not waiting for response then it is time to request someone to take actions
                }
            }
        }

        void processMessages(){
            ACLMessage mess = agent.receive();

            if (mess != null){
                try{
                    ContentElement c = cm.extractContent(mess);
                    if (c instanceof RequestToRegisterDM){
                        manageRegister(mess.getSender());
                    }
                    if (c instanceof GameAction){
                        GameAction ga = (GameAction) c;
                        manageJoin(mess.getSender(), ga);
                    }
                }
                catch (Exception ex){
                    say("Something bad with gotten message");
                }
            }
        }

        
        void processActionResponse(){ //Here we should process the action message from character

        }
        void sendActionRequest(){ //Here we should ask character whose turn it is to make an action, also we should somehow define on what actions can he make in that situation

        }

        void manageRegister(AID sender){
            try{
                RegisterDMResponse resp = new RegisterDMResponse();
                DungeonMaster dm = new DungeonMaster();
                dm.setDescription(agent.description);
                dm.setDifficulty(agent.difficulty);
                dm.setName(agent.getLocalName());
                resp.setDM(dm);
                ACLMessage messResp = agent.formMSG(sender);
                cm.fillContent(messResp, resp);
                agent.send(messResp);
            }
            catch (Exception ex){
                say("could not register");
            }


        }

        void manageJoin(AID sender, GameAction ga){
            if (ga.getWantToJoin() == true){
                GameActionResponse resp = new GameActionResponse();
                if (!isStarted){
                    resp.setSuccess(true);
                    agent.activePlayers.add(sender);
                    agent.addBehaviour(new LaunchGame());
                }
                else{
                    resp.setSuccess(false);
                }
                ACLMessage respMess = agent.formMSG(sender);
                try{
                    cm.fillContent(respMess, resp);
                    send(respMess);
                }
                catch (Exception ex){
                    say("could not send join response");
                }

            }
        }
    }

    //Launches bot agents, prepares map if necesary
    class LaunchGame extends OneShotBehaviour{
        @Override
        public void action(){
            AgentContainer cont = myAgent.getContainerController();
            for (int i = 0; i < 5; i++){
                try{
                    AgentController bot = cont.createNewAgent("Goblin"+bots.size(), "NPC", new Object[0]);
                    bot.start();
                    bots.add(new AID(bot.getName(), AID.ISLOCALNAME));
                }
                catch (Exception e){
                    say("Something ain't right");
                }
            }

            System.out.println(activePlayers.get(0));
            map = new Map(activePlayers, bots);
            System.out.println(map);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Up);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Up);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Right);
            System.out.println(map);


        }
    }
    //----

    //--Simple Methods--

    void addPlayer(){//Bad, will delete later
        AgentContainer cont = this.getContainerController();
        try{
            AgentController player = cont.createNewAgent("Player", "PlayGUI", new Object[0]);
            player.start();
            activePlayers.add(new AID(player.getName(), AID.ISLOCALNAME));
        }
        catch (Exception e){
            say("Something ain't right");
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

    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
    //----
}
