import RPG.ontology.*;
import jade.content.ContentElement;
import jade.content.ContentManager;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Iterator;
import java.util.Random;

//Class for bots
public class NPC extends Agent {
    Random rand = new Random();

    @Override
    public void setup() {
        say("Graghh, i am a Goblin");
        addBehaviour(new ProcessActionRequest());
    }

    class ProcessActionRequest extends CyclicBehaviour {
        @Override
        public void action() {
            ACLMessage mess = receive();
            if (mess != null) {
                try {
                    ContentManager cm = getCM();
                    ContentElement c = cm.extractContent(mess);
                    if (c instanceof SituationResponseRequest) {
                        AvailableOptions opts = ((SituationResponseRequest) c).getOptions();
                        Integer attOptsN = opts.getAttOpts().getAttackEnemyy().size();
                        SituationResponse response = new SituationResponse();
                        Object[] allOpts = new Object[opts.getMvOpts().getDir().size() + opts.getAttOpts().getAttackEnemyy().size()];
                        int i = 0;
                        Iterator mvOpts = opts.getMvOpts().getAllDir();
                        Iterator atOpts = opts.getAttOpts().getAllAttackEnemyy();
                        while (mvOpts.hasNext()) {
                            MoveAction mvAct = new MoveAction();
                            mvAct.setDirection((String) mvOpts.next());
                            allOpts[i] = mvAct;
                            i++;
                        }
                        while (atOpts.hasNext()) {
                            allOpts[i] = atOpts.next();
                            i++;
                        }
                        Object randOb = allOpts[rand.nextInt(i)];
                        response.setFinalAction(randOb);

                        ACLMessage oms = formMSG(mess.getSender());
                        cm.fillContent(oms, response);
                        send(oms);
                    }
                } catch (Exception ex) {
                    SituationResponse response = new SituationResponse();
                    MoveAction moveAct = new MoveAction();
                    moveAct.setDirection("Stay");
                    response.setFinalAction(moveAct);
                    ACLMessage oms = formMSG(mess.getSender());
                    try {
                        getCM().fillContent(oms, response);
                    } catch (Exception exe) {
                        say("Welp, idk now");
                    }
                    send(oms);
                    say("Could not take action, that will freeze the game: " + ex.getMessage());
                }

            }
        }
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
}
