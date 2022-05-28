// file: RPGOntology.java generated by ontology bean generator.  DO NOT EDIT, UNLESS YOU ARE REALLY SURE WHAT YOU ARE DOING!
package RPG.ontology;

import jade.content.onto.*;
import jade.content.schema.*;

/** file: RPGOntology.java
 * @author ontology bean generator
 * @version 2022/05/29, 01:52:37
 */
public class RPGOntology extends jade.content.onto.Ontology  {

  private static final long serialVersionUID = -726952151374612226L;

  //NAME
  public static final String ONTOLOGY_NAME = "RPG";
  // The singleton instance of this ontology
  private static Ontology theInstance = new RPGOntology();
  public static Ontology getInstance() {
     return theInstance;
  }


   // VOCABULARY
    public static final String GAMEACTIONRESPONSE_SUCCESS="Success";
    public static final String GAMEACTIONRESPONSE="GameActionResponse";
    public static final String SITUATIONRESPONSE_FINALACTION="FinalAction";
    public static final String SITUATIONRESPONSE="SituationResponse";
    public static final String REQUESTTOREGISTERDM_MAGICWORD="MagicWord";
    public static final String REQUESTTOREGISTERDM="RequestToRegisterDM";
    public static final String DUNGEONMASTERSLISTRESPONSE_DMSLIST="DMsList";
    public static final String DUNGEONMASTERSLISTRESPONSE="DungeonMastersListResponse";
    public static final String FINDDUNGEONMASTERS_DIFFICULTY="Difficulty";
    public static final String FINDDUNGEONMASTERS="FindDungeonMasters";
    public static final String GAMEACTION_WANTTOLEAVE="WantToLeave";
    public static final String GAMEACTION_WANTTOJOIN="WantToJoin";
    public static final String GAMEACTION="GameAction";
    public static final String REGISTERDMRESPONSE_DM="DM";
    public static final String REGISTERDMRESPONSE="RegisterDMResponse";
    public static final String SITUATIONRESPONSEREQUEST_MAP="Map";
    public static final String SITUATIONRESPONSEREQUEST_OPTIONS="Options";
    public static final String SITUATIONRESPONSEREQUEST_PROPMPT="Propmpt";
    public static final String SITUATIONRESPONSEREQUEST="SituationResponseRequest";
    public static final String COMBATACTION_ATTACKENEM="AttackEnem";
    public static final String COMBATACTION="CombatAction";
    public static final String ATTACKOPTIONS_ATTACKENEMYY="AttackEnemyy";
    public static final String ATTACKOPTIONS="AttackOptions";
    public static final String DUNGEONMASTER_DESCRIPTION="Description";
    public static final String DUNGEONMASTER_DIFFICULTY="Difficulty";
    public static final String DUNGEONMASTER_NAME="name";
    public static final String DUNGEONMASTER="DungeonMaster";
    public static final String MOVEACTION_DIRECTION="Direction";
    public static final String MOVEACTION="MoveAction";
    public static final String ATTACKENEMY_ENEMYID="EnemyID";
    public static final String ATTACKENEMY_ATTACKTYPE="AttackType";
    public static final String ATTACKENEMY="AttackEnemy";
    public static final String MOVEOPTIONS_DIR="Dir";
    public static final String MOVEOPTIONS="MoveOptions";
    public static final String AVAILABLEOPTIONS_MVOPTS="MvOpts";
    public static final String AVAILABLEOPTIONS_ATTOPTS="AttOpts";
    public static final String AVAILABLEOPTIONS="AvailableOptions";
    public static final String ATTACK_DAMAGE="Damage";
    public static final String ATTACK_ATTACKNAME="AttackName";
    public static final String ATTACK_ACCURACY="Accuracy";
    public static final String ATTACK_RANGE="Range";
    public static final String ATTACK_ATTACKDESCRIPTION="AttackDescription";
    public static final String ATTACK="Attack";

  /**
   * Constructor
  */
  private RPGOntology(){ 
    super(ONTOLOGY_NAME, BasicOntology.getInstance());
    try { 

    // adding Concept(s)
    ConceptSchema attackSchema = new ConceptSchema(ATTACK);
    add(attackSchema, RPG.ontology.Attack.class);
    ConceptSchema availableOptionsSchema = new ConceptSchema(AVAILABLEOPTIONS);
    add(availableOptionsSchema, RPG.ontology.AvailableOptions.class);
    ConceptSchema moveOptionsSchema = new ConceptSchema(MOVEOPTIONS);
    add(moveOptionsSchema, RPG.ontology.MoveOptions.class);
    ConceptSchema attackEnemySchema = new ConceptSchema(ATTACKENEMY);
    add(attackEnemySchema, RPG.ontology.AttackEnemy.class);
    ConceptSchema moveActionSchema = new ConceptSchema(MOVEACTION);
    add(moveActionSchema, RPG.ontology.MoveAction.class);
    ConceptSchema dungeonMasterSchema = new ConceptSchema(DUNGEONMASTER);
    add(dungeonMasterSchema, RPG.ontology.DungeonMaster.class);
    ConceptSchema attackOptionsSchema = new ConceptSchema(ATTACKOPTIONS);
    add(attackOptionsSchema, RPG.ontology.AttackOptions.class);
    ConceptSchema combatActionSchema = new ConceptSchema(COMBATACTION);
    add(combatActionSchema, RPG.ontology.CombatAction.class);

    // adding AgentAction(s)

    // adding AID(s)

    // adding Predicate(s)
    PredicateSchema situationResponseRequestSchema = new PredicateSchema(SITUATIONRESPONSEREQUEST);
    add(situationResponseRequestSchema, RPG.ontology.SituationResponseRequest.class);
    PredicateSchema registerDMResponseSchema = new PredicateSchema(REGISTERDMRESPONSE);
    add(registerDMResponseSchema, RPG.ontology.RegisterDMResponse.class);
    PredicateSchema gameActionSchema = new PredicateSchema(GAMEACTION);
    add(gameActionSchema, RPG.ontology.GameAction.class);
    PredicateSchema findDungeonMastersSchema = new PredicateSchema(FINDDUNGEONMASTERS);
    add(findDungeonMastersSchema, RPG.ontology.FindDungeonMasters.class);
    PredicateSchema dungeonMastersListResponseSchema = new PredicateSchema(DUNGEONMASTERSLISTRESPONSE);
    add(dungeonMastersListResponseSchema, RPG.ontology.DungeonMastersListResponse.class);
    PredicateSchema requestToRegisterDMSchema = new PredicateSchema(REQUESTTOREGISTERDM);
    add(requestToRegisterDMSchema, RPG.ontology.RequestToRegisterDM.class);
    PredicateSchema situationResponseSchema = new PredicateSchema(SITUATIONRESPONSE);
    add(situationResponseSchema, RPG.ontology.SituationResponse.class);
    PredicateSchema gameActionResponseSchema = new PredicateSchema(GAMEACTIONRESPONSE);
    add(gameActionResponseSchema, RPG.ontology.GameActionResponse.class);


    // adding fields
    attackSchema.add(ATTACK_ATTACKDESCRIPTION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    attackSchema.add(ATTACK_RANGE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
    attackSchema.add(ATTACK_ACCURACY, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    attackSchema.add(ATTACK_ATTACKNAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    attackSchema.add(ATTACK_DAMAGE, (TermSchema)getSchema(BasicOntology.INTEGER), ObjectSchema.MANDATORY);
    availableOptionsSchema.add(AVAILABLEOPTIONS_ATTOPTS, attackOptionsSchema, ObjectSchema.MANDATORY);
    availableOptionsSchema.add(AVAILABLEOPTIONS_MVOPTS, moveOptionsSchema, ObjectSchema.MANDATORY);
    moveOptionsSchema.add(MOVEOPTIONS_DIR, (TermSchema)getSchema(BasicOntology.STRING), 0, ObjectSchema.UNLIMITED);
    attackEnemySchema.add(ATTACKENEMY_ATTACKTYPE, attackSchema, ObjectSchema.MANDATORY);
    attackEnemySchema.add(ATTACKENEMY_ENEMYID, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    moveActionSchema.add(MOVEACTION_DIRECTION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    dungeonMasterSchema.add(DUNGEONMASTER_NAME, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
    dungeonMasterSchema.add(DUNGEONMASTER_DIFFICULTY, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    dungeonMasterSchema.add(DUNGEONMASTER_DESCRIPTION, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    attackOptionsSchema.add(ATTACKOPTIONS_ATTACKENEMYY, attackEnemySchema, 0, ObjectSchema.UNLIMITED);
    combatActionSchema.add(COMBATACTION_ATTACKENEM, attackEnemySchema, ObjectSchema.MANDATORY);
    situationResponseRequestSchema.add(SITUATIONRESPONSEREQUEST_PROPMPT, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    situationResponseRequestSchema.add(SITUATIONRESPONSEREQUEST_OPTIONS, availableOptionsSchema, ObjectSchema.MANDATORY);
    situationResponseRequestSchema.add(SITUATIONRESPONSEREQUEST_MAP, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    registerDMResponseSchema.add(REGISTERDMRESPONSE_DM, dungeonMasterSchema, ObjectSchema.MANDATORY);
    gameActionSchema.add(GAMEACTION_WANTTOJOIN, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
    gameActionSchema.add(GAMEACTION_WANTTOLEAVE, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.MANDATORY);
    findDungeonMastersSchema.add(FINDDUNGEONMASTERS_DIFFICULTY, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    dungeonMastersListResponseSchema.add(DUNGEONMASTERSLISTRESPONSE_DMSLIST, dungeonMasterSchema, 0, ObjectSchema.UNLIMITED);
    requestToRegisterDMSchema.add(REQUESTTOREGISTERDM_MAGICWORD, (TermSchema)getSchema(BasicOntology.STRING), ObjectSchema.OPTIONAL);
    situationResponseSchema.add(SITUATIONRESPONSE_FINALACTION, new ConceptSchema("Concept"), ObjectSchema.MANDATORY);
    gameActionResponseSchema.add(GAMEACTIONRESPONSE_SUCCESS, (TermSchema)getSchema(BasicOntology.BOOLEAN), ObjectSchema.OPTIONAL);

    // adding name mappings

    // adding inheritance

   }catch (java.lang.Exception e) {e.printStackTrace();}
  }
}
