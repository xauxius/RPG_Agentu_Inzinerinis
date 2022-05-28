package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: CombatAction
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class CombatAction implements CombatActionIf {

  private static final long serialVersionUID = 4465120161782918442L;

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
