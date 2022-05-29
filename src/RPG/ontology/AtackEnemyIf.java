package RPG.ontology;



/**
* Protege name: AtackEnemy
* @author ontology bean generator
* @version 2022/05/29, 14:57:40
*/
public interface AtackEnemyIf extends jade.content.Concept {

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
