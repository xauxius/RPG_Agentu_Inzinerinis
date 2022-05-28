package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: UpdateMap
* @author ontology bean generator
* @version 2022/05/28, 20:54:04
*/
public class UpdateMap implements UpdateMapIf {

  private static final long serialVersionUID = -2632440280682242471L;

  private String _internalInstanceName = null;

  public UpdateMap() {
    this._internalInstanceName = "";
  }

  public UpdateMap(String instance_name) {
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
