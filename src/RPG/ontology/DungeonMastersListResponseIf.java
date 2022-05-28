package RPG.ontology;


import jade.util.leap.*;

/**
* Protege name: DungeonMastersListResponse
* @author ontology bean generator
* @version 2022/05/28, 20:55:15
*/
public interface DungeonMastersListResponseIf extends jade.content.Predicate {

   /**
   * Protege name: DMsList
   */
   public void addDMsList(DungeonMaster elem);
   public boolean removeDMsList(DungeonMaster elem);
   public void clearAllDMsList();
   public Iterator getAllDMsList();
   public List getDMsList();
   public void setDMsList(List l);

}
