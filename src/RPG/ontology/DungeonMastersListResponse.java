package RPG.ontology;


import jade.util.leap.*;
import RPG.ontology.*;

/**
* Protege name: DungeonMastersListResponse
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class DungeonMastersListResponse implements DungeonMastersListResponseIf {

  private static final long serialVersionUID = 4465120161782918442L;

  private String _internalInstanceName = null;

  public DungeonMastersListResponse() {
    this._internalInstanceName = "";
  }

  public DungeonMastersListResponse(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: DMsList
   */
   private List dMsList = new ArrayList();
   public void addDMsList(DungeonMaster elem) { 
     dMsList.add(elem);
   }
   public boolean removeDMsList(DungeonMaster elem) {
     boolean result = dMsList.remove(elem);
     return result;
   }
   public void clearAllDMsList() {
     dMsList.clear();
   }
   public Iterator getAllDMsList() {return dMsList.iterator(); }
   public List getDMsList() {return dMsList; }
   public void setDMsList(List l) {dMsList = l; }

}
