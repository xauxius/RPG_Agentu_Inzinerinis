package RPG.ontology;


import jade.util.leap.*;

/**
* Protege name: AttackOptions
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public interface AttackOptionsIf extends jade.content.Concept {

   /**
   * Protege name: AttackEnemyy
   */
   public void addAttackEnemyy(AttackEnemy elem);
   public boolean removeAttackEnemyy(AttackEnemy elem);
   public void clearAllAttackEnemyy();
   public Iterator getAllAttackEnemyy();
   public List getAttackEnemyy();
   public void setAttackEnemyy(List l);

}
