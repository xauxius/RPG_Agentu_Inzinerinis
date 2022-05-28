package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: GameActionResponse
* @author ontology bean generator
* @version 2022/05/28, 20:55:15
*/
public class GameActionResponse implements GameActionResponseIf {

  private static final long serialVersionUID = 1851734369498031622L;

  private String _internalInstanceName = null;

  public GameActionResponse() {
    this._internalInstanceName = "";
  }

  public GameActionResponse(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Success
   */
   private boolean success;
   public void setSuccess(boolean value) { 
    this.success=value;
   }
   public boolean getSuccess() {
     return this.success;
   }

}
