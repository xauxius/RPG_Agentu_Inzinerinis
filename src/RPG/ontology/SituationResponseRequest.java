package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: SituationResponseRequest
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public class SituationResponseRequest implements SituationResponseRequestIf {

  private static final long serialVersionUID = -726952151374612226L;

  private String _internalInstanceName = null;

  public SituationResponseRequest() {
    this._internalInstanceName = "";
  }

  public SituationResponseRequest(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Propmpt
   */
   private String propmpt;
   public void setPropmpt(String value) { 
    this.propmpt=value;
   }
   public String getPropmpt() {
     return this.propmpt;
   }

   /**
   * Protege name: Options
   */
   private AvailableOptions options;
   public void setOptions(AvailableOptions value) { 
    this.options=value;
   }
   public AvailableOptions getOptions() {
     return this.options;
   }

   /**
   * Protege name: Map
   */
   private String map;
   public void setMap(String value) { 
    this.map=value;
   }
   public String getMap() {
     return this.map;
   }

}
