package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: SituationResponse
* @author ontology bean generator
* @version 2022/05/29, 16:10:17
*/
public class SituationResponse implements SituationResponseIf {

  private static final long serialVersionUID = -7169497409159083070L;

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
