package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: AvailableOptions
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class AvailableOptions implements AvailableOptionsIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public AvailableOptions() {
    this._internalInstanceName = "";
  }

  public AvailableOptions(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: AttOpts
   */
   private AttackOptions attOpts;
   public void setAttOpts(AttackOptions value) { 
    this.attOpts=value;
   }
   public AttackOptions getAttOpts() {
     return this.attOpts;
   }

   /**
   * Protege name: MvOpts
   */
   private MoveOptions mvOpts;
   public void setMvOpts(MoveOptions value) { 
    this.mvOpts=value;
   }
   public MoveOptions getMvOpts() {
     return this.mvOpts;
   }

}
