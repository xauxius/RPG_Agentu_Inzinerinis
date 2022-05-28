package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: CombatAction
* @author ontology bean generator
* @version 2022/05/28, 14:21:01
*/
public class CombatAction implements CombatActionIf {

  private static final long serialVersionUID = -8611740379689045507L;

  private String _internalInstanceName = null;

  public CombatAction() {
    this._internalInstanceName = "";
  }

  public CombatAction(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: ActionType
   */
   private String actionType;
   public void setActionType(String value) { 
    this.actionType=value;
   }
   public String getActionType() {
     return this.actionType;
   }

   /**
   * Protege name: AttackAction
   */
   private Attack attackAction;
   public void setAttackAction(Attack value) { 
    this.attackAction=value;
   }
   public Attack getAttackAction() {
     return this.attackAction;
   }

}
