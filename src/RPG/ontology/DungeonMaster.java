package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: DungeonMaster
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class DungeonMaster implements DungeonMasterIf {

  private static final long serialVersionUID = 4465120161782918442L;

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
