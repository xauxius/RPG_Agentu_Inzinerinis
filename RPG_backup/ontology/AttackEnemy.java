package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: AttackEnemy
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class AttackEnemy implements AttackEnemyIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public AttackEnemy() {
    this._internalInstanceName = "";
  }

  public AttackEnemy(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: AttackType
   */
   private Attack attackType;
   public void setAttackType(Attack value) { 
    this.attackType=value;
   }
   public Attack getAttackType() {
     return this.attackType;
   }

   /**
   * Protege name: EnemyID
   */
   private String enemyID;
   public void setEnemyID(String value) { 
    this.enemyID=value;
   }
   public String getEnemyID() {
     return this.enemyID;
   }

}
