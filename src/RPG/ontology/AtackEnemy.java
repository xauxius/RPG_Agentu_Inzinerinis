package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: AtackEnemy
* @author ontology bean generator
* @version 2022/05/29, 14:57:40
*/
public class AtackEnemy implements AtackEnemyIf {

  private static final long serialVersionUID = -4516224460155243061L;

  private String _internalInstanceName = null;

  public AtackEnemy() {
    this._internalInstanceName = "";
  }

  public AtackEnemy(String instance_name) {
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
