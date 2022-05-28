package RPG.ontology;



/**
* Protege name: CombatAction
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public interface CombatActionIf extends jade.content.Predicate {

   /**
   * Protege name: ActionType
   */
   public void setActionType(String value);
   public String getActionType();

   /**
   * Protege name: AttackAction
   */
   public void setAttackAction(Attack value);
   public Attack getAttackAction();

}
