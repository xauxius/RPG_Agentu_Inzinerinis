package RPG.ontology;


import RPG.ontology.*;

/**
* Protege name: SituationResponseRequest
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public class SituationResponseRequest implements SituationResponseRequestIf {

  private static final long serialVersionUID = -3012293790907132644L;

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
   * Protege name: GameWon
   */
   private boolean gameWon;
   public void setGameWon(boolean value) { 
    this.gameWon=value;
   }
   public boolean getGameWon() {
     return this.gameWon;
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
   * Protege name: GameLost
   */
   private boolean gameLost;
   public void setGameLost(boolean value) { 
    this.gameLost=value;
   }
   public boolean getGameLost() {
     return this.gameLost;
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

   /**
   * Protege name: Health
   */
   private int health;
   public void setHealth(int value) { 
    this.health=value;
   }
   public int getHealth() {
     return this.health;
   }

}
