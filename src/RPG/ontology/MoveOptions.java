package RPG.ontology;


import jade.util.leap.*;
import RPG.ontology.*;

/**
* Protege name: MoveOptions
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class MoveOptions implements MoveOptionsIf {

  private static final long serialVersionUID = -3012293790907132644L;

  private String _internalInstanceName = null;

  public MoveOptions() {
    this._internalInstanceName = "";
  }

  public MoveOptions(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Dir
   */
   private List dir = new ArrayList();
   public void addDir(String elem) { 
     dir.add(elem);
   }
   public boolean removeDir(String elem) {
     boolean result = dir.remove(elem);
     return result;
   }
   public void clearAllDir() {
     dir.clear();
   }
   public Iterator getAllDir() {return dir.iterator(); }
   public List getDir() {return dir; }
   public void setDir(List l) {dir = l; }

}
