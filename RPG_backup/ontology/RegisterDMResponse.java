package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: RegisterDMResponse
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class RegisterDMResponse implements RegisterDMResponseIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public RegisterDMResponse() {
    this._internalInstanceName = "";
  }

  public RegisterDMResponse(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: DM
   */
   private DungeonMaster dM;
   public void setDM(DungeonMaster value) { 
    this.dM=value;
   }
   public DungeonMaster getDM() {
     return this.dM;
   }

}
