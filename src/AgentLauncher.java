import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class AgentLauncher extends Agent {
    @Override
    public void setup() {
        AgentContainer cont = this.getContainerController();
        try {
            cont.createNewAgent("Player", "PlayGUI", new Object[0]).start();
            String[] args1 = {"Easy", "As nepiktas masteris"};
            cont.createNewAgent("DM1", "DungeonMasterAg", args1).start();
            cont.createNewAgent("DMsProvider", "DMsProvider", new Object[0]).start();
        } catch (Exception e) {
            say("Something ain't right");
        }
        doDelete();
    }

    void say(String text) {
        System.out.println("A[" + getLocalName() + "]: " + text);
    }
}
