package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: RegisterDMResponse
* @author ontology bean generator
* @version 2022/05/28, 14:21:01
*/
public class RegisterDMResponse implements RegisterDMResponseIf {

  private static final long serialVersionUID = -8611740379689045507L;

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
