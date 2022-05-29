package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: SituationResponse
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class SituationResponse implements SituationResponseIf {

  private static final long serialVersionUID = -3012293790907132644L;

  private String _internalInstanceName = null;

  public SituationResponse() {
    this._internalInstanceName = "";
  }

  public SituationResponse(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: FinalAction
   */
   private MoveAction finalAction;
   public void setFinalAction(MoveAction value) { 
    this.finalAction=value;
   }
   public MoveAction getFinalAction() {
     return this.finalAction;
   }

}
