package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: DungeonMaster
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class DungeonMaster implements DungeonMasterIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public DungeonMaster() {
    this._internalInstanceName = "";
  }

  public DungeonMaster(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: name
   */
   private String name;
   public void setName(String value) { 
    this.name=value;
   }
   public String getName() {
     return this.name;
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

   /**
   * Protege name: Description
   */
   private String description;
   public void setDescription(String value) { 
    this.description=value;
   }
   public String getDescription() {
     return this.description;
   }

}
