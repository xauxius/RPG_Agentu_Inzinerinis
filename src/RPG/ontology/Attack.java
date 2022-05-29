package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: Attack
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class Attack implements AttackIf {

  private static final long serialVersionUID = -3012293790907132644L;

  private String _internalInstanceName = null;

  public Attack() {
    this._internalInstanceName = "";
  }

  public Attack(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: AttackDescription
   */
   private String attackDescription;
   public void setAttackDescription(String value) { 
    this.attackDescription=value;
   }
   public String getAttackDescription() {
     return this.attackDescription;
   }

   /**
   * Protege name: Range
   */
   private int range;
   public void setRange(int value) { 
    this.range=value;
   }
   public int getRange() {
     return this.range;
   }

   /**
   * Protege name: Accuracy
   */
   private int accuracy;
   public void setAccuracy(int value) { 
    this.accuracy=value;
   }
   public int getAccuracy() {
     return this.accuracy;
   }

   /**
   * Protege name: AttackName
   */
   private String attackName;
   public void setAttackName(String value) { 
    this.attackName=value;
   }
   public String getAttackName() {
     return this.attackName;
   }

   /**
   * Protege name: Damage
   */
   private int damage;
   public void setDamage(int value) { 
    this.damage=value;
   }
   public int getDamage() {
     return this.damage;
   }

}
