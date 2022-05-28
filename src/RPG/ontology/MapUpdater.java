package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: MapUpdater
* @author ontology bean generator
* @version 2022/05/28, 20:55:15
*/
public class MapUpdater implements MapUpdaterIf {

  private static final long serialVersionUID = 1851734369498031622L;

  private String _internalInstanceName = null;

  public MapUpdater() {
    this._internalInstanceName = "";
  }

  public MapUpdater(String instance_name) {
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
