package RPG.ontology;


import jade.util.leap.*;

/**
* Protege name: MoveOptions
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public interface MoveOptionsIf extends jade.content.Concept {

   /**
   * Protege name: Dir
   */
   public void addDir(String elem);
   public boolean removeDir(String elem);
   public void clearAllDir();
   public Iterator getAllDir();
   public List getDir();
   public void setDir(List l);

}
