package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: MoveAction
* @author ontology bean generator
* @version 2022/05/28, 20:55:15
*/
public class MoveAction implements MoveActionIf {

  private static final long serialVersionUID = 1851734369498031622L;

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
