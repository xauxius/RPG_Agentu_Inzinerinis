package RPG.ontology;



/**
* Protege name: Attack
* @author ontology bean generator
* @version 2022/05/28, 20:55:15
*/
public interface AttackIf extends jade.content.Concept {

   /**
   * Protege name: AttackDescription
   */
   public void setAttackDescription(String value);
   public String getAttackDescription();

   /**
   * Protege name: Accuracy
   */
   public void setAccuracy(String value);
   public String getAccuracy();

   /**
   * Protege name: AttackName
   */
   public void setAttackName(String value);
   public String getAttackName();

   /**
   * Protege name: Damage
   */
   public void setDamage(int value);
   public int getDamage();

}
