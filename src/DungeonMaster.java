import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.*;

import java.util.ArrayList;

public class DungeonMaster extends Agent{
    //--Variables--
    boolean isStarted = false;
    Map map;
    ArrayList<AID> activeCharacters = new ArrayList<>();
    ArrayList<AID> activePlayers = new ArrayList<>();
    ArrayList<AID> bots = new ArrayList<>();
    ArrayList<NPC> viableBots = new ArrayList<>();
    //----


    @Override
    public void setup(){
        addPlayer();
        addBehaviour(new LaunchGame());
    }


    //--Behaviour Classes--
    //Does all the neccesarry thing when game is waiting to be started
    class processWaiting extends CyclicBehaviour{ //Needs implementing: waiting for player, initiating the game

        @Override
        public void action() { // If player wants to join add to the players,

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

            //System.out.println(activePlayers.get(0).);
            map = new Map(activePlayers, bots);
            System.out.println(map);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Up);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Up);
            map.moveEntityByAID(activePlayers.get(0), Map.Dirs.Right);
            System.out.println(map);


        }
    }

    class AddBot extends OneShotBehaviour{
        @Override
        public void action(){
            AgentContainer cont = myAgent.getContainerController();
            try{
                AgentController bot = cont.createNewAgent("Goblin"+bots.size(), "NPC", new Object[0]);
                bot.start();
                bots.add(new AID(bot.getName(), AID.ISLOCALNAME));
            }
            catch (Exception e){
                say("Something ain't right");
            }

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

    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
    //----
}


