package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: MoveAction
* @author ontology bean generator
* @version 2022/05/29, 16:10:17
*/
public class MoveAction implements MoveActionIf {

  private static final long serialVersionUID = -7169497409159083070L;

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
