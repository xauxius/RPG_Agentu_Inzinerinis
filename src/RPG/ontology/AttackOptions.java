package RPG.ontology;


import jade.util.leap.*;
import RPG.ontology.*;

/**
* Protege name: AttackOptions
* @author ontology bean generator
* @version 2022/05/29, 16:10:17
*/
public class AttackOptions implements AttackOptionsIf {

  private static final long serialVersionUID = -7169497409159083070L;

  private String _internalInstanceName = null;

  public AttackOptions() {
    this._internalInstanceName = "";
  }

  public AttackOptions(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: AttackEnemyy
   */
   private List attackEnemyy = new ArrayList();
   public void addAttackEnemyy(AttackEnemy elem) { 
     attackEnemyy.add(elem);
   }
   public boolean removeAttackEnemyy(AttackEnemy elem) {
     boolean result = attackEnemyy.remove(elem);
     return result;
   }
   public void clearAllAttackEnemyy() {
     attackEnemyy.clear();
   }
   public Iterator getAllAttackEnemyy() {return attackEnemyy.iterator(); }
   public List getAttackEnemyy() {return attackEnemyy; }
   public void setAttackEnemyy(List l) {attackEnemyy = l; }

}
