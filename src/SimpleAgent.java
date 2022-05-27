import jade.core.Agent;
import jade.wrapper.*;

public class SimpleAgent extends Agent{
    @Override
    public void setup(){
        say("hello");
    }
    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
}
