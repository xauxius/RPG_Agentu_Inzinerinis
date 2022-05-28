import jade.core.Agent;

//Class for bots
public class NPC extends Agent {
    @Override
    public void setup(){
        say("Graghh, i am a Goblin");
    }


    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
}
