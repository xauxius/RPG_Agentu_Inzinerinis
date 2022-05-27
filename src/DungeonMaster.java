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
    ArrayList<AID> activeCharacters;
    ArrayList<AID> activePlayers;
    ArrayList<NPC> viableBots;
    //----


    @Override
    public void setup(){
        AgentContainer cont = this.getContainerController();
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

        }
    }

    //----

    //--Simple Methods--
    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
    //----
}


