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
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

import java.util.ArrayList;
import java.util.Random;

public class DungeonMasterAg extends Agent {
    //--Variables--
    boolean isStarted = false;
    Map map;
    ArrayList<ActiveChar> activeCharacters = new ArrayList<>();
    AID player;
    ArrayList<AID> bots = new ArrayList<>();
    String description;
    String difficulty;
    int turn = 0;
    boolean waitingForResp;
    Random random = new Random();
    static int botAll = 0;
    //----


    @Override
    public void setup() {
        processArgs();
        addBehaviour(new AssignService(this, Config.DM));
        addBehaviour(new MainLoop(this));
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
        String prompt = "Welcome to the dungeon of lost souls, prepare for the mighty fight";

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
                    ContentElement c = cm.extractContent(mess);
                    if (c instanceof RequestToRegisterDM) {
                        manageRegister(mess.getSender());
                    }
                    if (c instanceof GameAction) {
                        GameAction ga = (GameAction) c;
                        manageJoin(mess.getSender(), ga);
                    }
                } catch (Exception ex) {
                    say("Something bad with gotten message: " + ex.getMessage());
                }
            }
        }


        void processActionResponse() { //Here we should process the action message from character
            ACLMessage mess = agent.receive();

            if (mess != null) {
                try {
                    ContentElement c = cm.extractContent(mess);
                    if (c instanceof SituationResponse && mess.getSender().getName().equalsIgnoreCase(activeCharacters.get(turn).id.getName())) {
                        SituationResponse response = (SituationResponse) c;
                        Object action = response.getFinalAction();

                        if (action instanceof AttackEnemy) {
                            AttackEnemy combatAction = (AttackEnemy) action;
                            String attPromptMess = "";
                            String user = mess.getSender().getLocalName().equalsIgnoreCase(player.getLocalName()) ? "Player" : "Goblin " + getMarker(mess.getSender());
                            String usedOn = combatAction.getEnemyID().equals("*") ? "Player" : "Goblin " + combatAction.getEnemyID();
                            attPromptMess += user + " Used " + combatAction.getAttackType().getAttackName() + " on " + usedOn + ", and it";

                            if (random.nextInt(100) < combatAction.getAttackType().getAccuracy()) {
                                attPromptMess += " did " + combatAction.getAttackType().getDamage() + " damage\n";
                                ActiveChar actChar = DamageCharacter(combatAction.getEnemyID(), combatAction.getAttackType().getDamage());

                                if (actChar.health <= 0 && actChar.id != player) {
                                    prompt += "Goblin " + actChar.marker + " have suffered a horrible death\n";
                                    activeCharacters.remove(actChar);
                                    map.killEntity(actChar.id);
                                    try
                                    {
                                        AgentContainer mc = agent.getContainerController();
                                        AgentController actrl = mc.getAgent(actChar.id.getLocalName(), AID.ISLOCALNAME);
                                        actrl.kill();
                                    }
                                    catch (Exception ex) {System.out.println("Could not kill agent");}
                                    //Prompt about killing a goblin
                                }
                            }
                            else {
                                attPromptMess += " missed really badly :/\n";
                            }
                            prompt += attPromptMess;
                        }
                        else {
                            MoveAction mvAction = (MoveAction) action;
                            Map.Dirs dir = Map.Dirs.Stay;
                            switch (mvAction.getDirection()) {
                                case Config.Up:
                                    dir = Map.Dirs.Up;
                                    break;
                                case Config.Down:
                                    dir = Map.Dirs.Down;
                                    break;
                                case Config.Left:
                                    dir = Map.Dirs.Left;
                                    break;
                                case Config.Right:
                                    dir = Map.Dirs.Right;
                                    break;
                                case Config.Stay:
                                    dir = Map.Dirs.Stay;
                                    break;
                            }
                            map.moveEntityByAID(mess.getSender(), dir);
                        }
                        turn = (turn + 1) % activeCharacters.size();
                        waitingForResp = false;
                    }
                } catch (Exception ex) {
                    say("processing action response error: " + ex.getMessage());
                }

            }
        }

        void sendActionRequest() { //Here we should ask character whose turn it is to make an action, also we should somehow define on what actions can he make in that situation
            ActiveChar turnOf = activeCharacters.get(turn);
            if (prompt.equalsIgnoreCase("")) {
                prompt = "Nothing interesting happened :(\n";
            }
            sendSituationMessage(turnOf.id, map, map.getOptions(turnOf.id), prompt, turnOf.health, getIsWon(), getIsLost());
            if (turnOf.id.getLocalName().equalsIgnoreCase(player.getLocalName())) {
                prompt = "";
            }
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

    boolean getIsWon() {
        ActiveChar actChar = activeCharacters.get(turn);
        if (actChar.id == player) {
            return map.isEnemiesCleared();
        }
        return false;
    }

    boolean getIsLost() {
        ActiveChar actChar = activeCharacters.get(turn);
        if (actChar.health <= 0) {
            return true;
        }
        return false;
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
            int health = difficulty.equals(Config.Easy) ? 200 : difficulty.equals(Config.Medium) ? 100 : 50;
            activeCharacters.add(new ActiveChar(player, "*", health));
            String[] markers = new String[]{"a", "b", "c", "d", "e"};
            for (int i = 0; i < 5; i++) {
                try {
                    AgentController bot = cont.createNewAgent("Goblin" + botAll++, "NPC", new Object[0]);
                    bot.start();
                    AID botId = new AID(bot.getName(), AID.ISGUID);
                    bots.add(botId);
                    activeCharacters.add(new ActiveChar(botId, markers[i], 20));
                } catch (Exception e) {
                    say("Something ain't right");
                }
            }
            map = new Map(player, bots);
            isStarted = true;

        }
    }
    //----

    public void sendSituationMessage(AID sendTO, Map map, AvailableOptions options, String promt, Integer health, boolean won, boolean lost) {
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
        msg.setHealth(health);
        msg.setGameWon(won);
        msg.setGameLost(lost);
        try {
            cm.fillContent(response, msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        send(response);
        if (sendTO == player) {
            if (won || lost) {
                Reset();
            }
        }
        waitingForResp = true;
    }
    //--Simple Methods--

    public void Reset() {
        activeCharacters = new ArrayList<>();
        isStarted = false;
        turn = 0;
        for (AID bot : bots) {
            try {
                AgentContainer mc = this.getContainerController();
                AgentController actrl = mc.getAgent(bot.getLocalName(), AID.ISLOCALNAME);
                actrl.kill();
            } catch (Exception ex) {
            }
        }
        bots = new ArrayList<>();
    }

    public String getMarker(AID id) {
        for (ActiveChar actChar : activeCharacters) {
            if (actChar.id.getLocalName().equalsIgnoreCase(id.getLocalName())) {
                return actChar.marker;
            }
        }
        return " ";
    }

    public ActiveChar DamageCharacter(String marker, Integer damage) {
        for (ActiveChar actChar : activeCharacters) {
            if (actChar.marker.equals(marker)) {
                actChar.health -= damage;
                return actChar;
            }
        }
        return null;
    }

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

    class ActiveChar {
        public AID id;
        public String marker;
        public Integer health;

        public ActiveChar(AID id, String marker, Integer health) {
            this.id = id;
            this.marker = marker;
            this.health = health;
        }
    }
}
