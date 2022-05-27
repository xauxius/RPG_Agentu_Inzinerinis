import jade.core.Agent;
import jade.wrapper.*;

public class AgentLauncher extends Agent{
    @Override
    public void setup(){
        AgentContainer cont = this.getContainerController();
        try{
            cont.createNewAgent("simp", "SimpleAgent", new Object[0]);
            cont.start();
        }
        catch (Exception e){
            say("Something ain't right");
        }
        doDelete();
        say("yo", "nana");
    }
    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }
    void say(String text, String beh){
        System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    }
}
