package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: SituationResponse
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class SituationResponse implements SituationResponseIf {

  private static final long serialVersionUID = -726952151374612226L;

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
   private Object finalAction;
   public void setFinalAction(Object value) { 
    this.finalAction=value;
   }
   public Object getFinalAction() {
     return this.finalAction;
   }

}
