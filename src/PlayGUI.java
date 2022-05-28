import jade.core.Agent;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.wrapper.*;

public class PlayGUI extends GuiAgent {
    @Override
    public void setup(){
        addBehaviour(new AssignService(this, "Player"));
        say("Hello, i am the player :)");
    }


    //void say(String text){
    //    System.out.println("A["+getLocalName()+"]: "+text);
    //}
    //void say(String text, String beh){
    //    System.out.println("A["+getLocalName()+"|"+beh+"]: "+text);
    //}

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }


    void say(String text){
        System.out.println("A["+getLocalName()+"]: "+text);
    }

}
