package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: MoveAction
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class MoveAction implements MoveActionIf {

  private static final long serialVersionUID = 4465120161782918442L;

  private String _internalInstanceName = null;

  public MoveAction() {
    this._internalInstanceName = "";
  }

  public MoveAction(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Direction
   */
   private String direction;
   public void setDirection(String value) { 
    this.direction=value;
   }
   public String getDirection() {
     return this.direction;
   }

}
