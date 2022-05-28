package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: FindDungeonMasters
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class FindDungeonMasters implements FindDungeonMastersIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public FindDungeonMasters() {
    this._internalInstanceName = "";
  }

  public FindDungeonMasters(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Difficulty
   */
   private String difficulty;
   public void setDifficulty(String value) { 
    this.difficulty=value;
   }
   public String getDifficulty() {
     return this.difficulty;
   }

}
