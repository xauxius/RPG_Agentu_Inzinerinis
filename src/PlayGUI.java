import jade.gui.GuiAgent;
import jade.gui.GuiEvent;

public class PlayGUI extends GuiAgent {
    GameGUI myGui = null;

    @Override
    public void setup()
    {
        System.out.println("A[" + getLocalName() + "] Welcome to agent with GUI");
        myGui = new GameGUI();
        myGui.setVisible(true);
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
}
