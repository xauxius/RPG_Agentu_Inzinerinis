package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: GameActionResponse
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class GameActionResponse implements GameActionResponseIf {

  private static final long serialVersionUID = -3012293790907132644L;

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
