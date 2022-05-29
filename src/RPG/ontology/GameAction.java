package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: GameAction
* @author ontology bean generator
* @version 2022/05/29, 16:10:17
*/
public class GameAction implements GameActionIf {

  private static final long serialVersionUID = -7169497409159083070L;

  private String _internalInstanceName = null;

  public GameAction() {
    this._internalInstanceName = "";
  }

  public GameAction(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: WantToJoin
   */
   private boolean wantToJoin;
   public void setWantToJoin(boolean value) { 
    this.wantToJoin=value;
   }
   public boolean getWantToJoin() {
     return this.wantToJoin;
   }

   /**
   * Protege name: WantToLeave
   */
   private boolean wantToLeave;
   public void setWantToLeave(boolean value) { 
    this.wantToLeave=value;
   }
   public boolean getWantToLeave() {
     return this.wantToLeave;
   }

}
