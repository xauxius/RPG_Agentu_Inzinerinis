package RPG.ontology;



/**
* Protege name: AttackEnemy
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public interface AttackEnemyIf extends jade.content.Concept {

   /**
   * Protege name: AttackType
   */
   public void setAttackType(Attack value);
   public Attack getAttackType();

   /**
   * Protege name: EnemyID
   */
   public void setEnemyID(String value);
   public String getEnemyID();

}
