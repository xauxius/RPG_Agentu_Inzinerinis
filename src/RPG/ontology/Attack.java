package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: Attack
* @author ontology bean generator
* @version 2022/05/28, 14:21:01
*/
public class Attack implements AttackIf {

  private static final long serialVersionUID = -8611740379689045507L;

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
   * Protege name: Accuracy
   */
   private String accuracy;
   public void setAccuracy(String value) { 
    this.accuracy=value;
   }
   public String getAccuracy() {
     return this.accuracy;
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
