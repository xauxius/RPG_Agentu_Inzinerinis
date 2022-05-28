package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: RequestToRegisterDM
* @author ontology bean generator
* @version 2022/05/28, 14:21:01
*/
public class RequestToRegisterDM implements RequestToRegisterDMIf {

  private static final long serialVersionUID = -8611740379689045507L;

  private String _internalInstanceName = null;

  public RequestToRegisterDM() {
    this._internalInstanceName = "";
  }

  public RequestToRegisterDM(String instance_name) {
    this._internalInstanceName = instance_name;
  }

  public String toString() {
    return _internalInstanceName;
  }

   /**
   * Protege name: MagicWord
   */
   private String magicWord;
   public void setMagicWord(String value) { 
    this.magicWord=value;
   }
   public String getMagicWord() {
     return this.magicWord;
   }

}
