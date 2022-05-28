package RPG.ontology;


import jade.util.leap.*;

/**
* Protege name: Situation
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public interface SituationIf extends jade.content.Predicate {

   /**
   * Protege name: Prompt
   */
   public void setPrompt(String value);
   public String getPrompt();

   /**
   * Protege name: Actions
   */
   public void addActions(Object elem);
   public boolean removeActions(Object elem);
   public void clearAllActions();
   public Iterator getAllActions();
   public List getActions();
   public void setActions(List l);

}
