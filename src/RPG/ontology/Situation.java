package RPG.ontology;


import jade.util.leap.*;
import RPG.ontology.*;

/**
* Protege name: Situation
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class Situation implements SituationIf {

  private static final long serialVersionUID = 4465120161782918442L;

  private String _internalInstanceName = null;

  public Situation() {
    this._internalInstanceName = "";
  }

  public Situation(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Prompt
   */
   private String prompt;
   public void setPrompt(String value) { 
    this.prompt=value;
   }
   public String getPrompt() {
     return this.prompt;
   }

   /**
   * Protege name: Actions
   */
   private List actions = new ArrayList();
   public void addActions(Object elem) { 
     actions.add(elem);
   }
   public boolean removeActions(Object elem) {
     boolean result = actions.remove(elem);
     return result;
   }
   public void clearAllActions() {
     actions.clear();
   }
   public Iterator getAllActions() {return actions.iterator(); }
   public List getActions() {return actions; }
   public void setActions(List l) {actions = l; }

}
