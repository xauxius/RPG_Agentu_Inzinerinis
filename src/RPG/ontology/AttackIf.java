package RPG.ontology;



/**
* Protege name: Attack
* @author ontology bean generator
* @version 2022/05/28, 12:55:16
*/
public interface AttackIf extends jade.content.Concept {

   /**
   * Protege name: Accuracy
   */
   public void setAccuracy(String value);
   public String getAccuracy();

   /**
   * Protege name: Damage
   */
   public void setDamage(int value);
   public int getDamage();

}
