package RPG.ontology;



/**
* Protege name: AvailableOptions
* @author ontology bean generator
* @version 2022/05/29, 15:28:18
*/
public interface AvailableOptionsIf extends jade.content.Concept {

   /**
   * Protege name: AttOpts
   */
   public void setAttOpts(AttackOptions value);
   public AttackOptions getAttOpts();

   /**
   * Protege name: MvOpts
   */
   public void setMvOpts(MoveOptions value);
   public MoveOptions getMvOpts();

}
