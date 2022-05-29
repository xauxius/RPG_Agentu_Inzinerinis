package RPG.ontology;



/**
* Protege name: SituationResponseRequest
* @author ontology bean generator
* @version 2022/05/29, 16:10:17
*/
public interface SituationResponseRequestIf extends jade.content.Predicate {

   /**
   * Protege name: GameWon
   */
   public void setGameWon(boolean value);
   public boolean getGameWon();

   /**
   * Protege name: Propmpt
   */
   public void setPropmpt(String value);
   public String getPropmpt();

   /**
   * Protege name: GameLost
   */
   public void setGameLost(boolean value);
   public boolean getGameLost();

   /**
   * Protege name: Options
   */
   public void setOptions(AvailableOptions value);
   public AvailableOptions getOptions();

   /**
   * Protege name: Map
   */
   public void setMap(String value);
   public String getMap();

   /**
   * Protege name: Health
   */
   public void setHealth(int value);
   public int getHealth();

}
