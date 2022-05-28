package RPG.ontology;



/**
* Protege name: GameAction
* @author ontology bean generator
* @version 2022/05/28, 12:55:16
*/
public interface GameActionIf extends jade.content.Predicate {

   /**
   * Protege name: WantToJoin
   */
   public void setWantToJoin(boolean value);
   public boolean getWantToJoin();

   /**
   * Protege name: WantToLeave
   */
   public void setWantToLeave(boolean value);
   public boolean getWantToLeave();

}
