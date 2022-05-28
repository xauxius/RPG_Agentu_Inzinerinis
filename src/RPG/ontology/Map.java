package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: Map
* @author ontology bean generator
* @version 2022/05/28, 19:07:58
*/
public class Map implements MapIf {

  private static final long serialVersionUID = 4465120161782918442L;

  private String _internalInstanceName = null;

  public Map() {
    this._internalInstanceName = "";
  }

  public Map(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: Content
   */
   private String content;
   public void setContent(String value) { 
    this.content=value;
   }
   public String getContent() {
     return this.content;
   }

}
