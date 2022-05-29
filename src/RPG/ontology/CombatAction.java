package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: CombatAction
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class CombatAction implements CombatActionIf {

  private static final long serialVersionUID = -3012293790907132644L;

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
   * Protege name: AttackEnem
   */
   private AttackEnemy attackEnem;
   public void setAttackEnem(AttackEnemy value) { 
    this.attackEnem=value;
   }
   public AttackEnemy getAttackEnem() {
     return this.attackEnem;
   }

}
