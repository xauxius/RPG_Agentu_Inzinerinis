package RPG.ontology;



/**
* Protege name: SituationResponseRequest
* @author ontology bean generator
* @version 2022/05/29, 01:52:37
*/
public interface SituationResponseRequestIf extends jade.content.Predicate {

   /**
   * Protege name: Propmpt
   */
   public void setPropmpt(String value);
   public String getPropmpt();

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

}
