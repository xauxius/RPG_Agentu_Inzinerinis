import RPG.ontology.*;
import jade.core.AID;

import java.util.ArrayList;

// Class managing the map, can give string of encoded map, ensures movement logic
public class Map {
    public static enum EntType {Player, Bot}

    public static enum Dirs {Up, Left, Down, Right, Stay}

    public int N;
    public int M;

    static String Wall = "x";
    static String Ground = ".";
    static String Enemy = "E";

    ArrayList<Entity> entities = new ArrayList<Entity>();
    ArrayList<Attack> attacks = new ArrayList<Attack>();

    public String[][] mapSkelet = {
            {Wall, Wall, Wall, Wall, Wall, Wall, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Ground, Ground, Ground, Ground, Ground, Wall},
            {Wall, Wall, Wall, Wall, Wall, Wall, Wall}
    };

    public Map(AID player, ArrayList<AID> bots){
        this.attacks = formAttacks();
        N = mapSkelet.length;
        M = mapSkelet[0].length;
        int playI = 8;
        int playJ = 3;
        entities.add(new Entity(player, EntType.Player, playI, playJ, "*"));
        entities.add(new Entity(bots.get(0), EntType.Bot, 1, 1, "a"));
        entities.add(new Entity(bots.get(1), EntType.Bot, 1, 3, "b"));
        entities.add(new Entity(bots.get(2), EntType.Bot, 1, 5, "c"));
        entities.add(new Entity(bots.get(3), EntType.Bot, 2, 2, "d"));
        entities.add(new Entity(bots.get(4), EntType.Bot, 2, 4, "e"));
    }

    ArrayList<Attack> formAttacks(){
        ArrayList<Attack> atts = new ArrayList<>();
        Attack kick = new Attack();
        kick.setAccuracy(90);
        kick.setDamage(8);
        kick.setRange(1);
        kick.setAttackName("Kick");

        Attack punch = new Attack();
        punch.setAccuracy(50);
        punch.setDamage(15);
        punch.setRange(1);
        punch.setAttackName("Face punch");

        Attack spell = new Attack();
        spell.setAccuracy(80);
        spell.setDamage(50);
        spell.setRange(5);
        spell.setAttackName("Deadly spell");
        atts.add(kick);
        atts.add(punch);
        atts.add(spell);
        return atts;
    }


    boolean canMoveTo(int i, int j) {
        if (mapSkelet[i][j] == Wall) {
            return false;
        }
        for (Entity ent : entities) {
            if (ent.i == i && ent.j == j) {
                return false;
            }
        }
        return true;
    }

    boolean moveEntityByAID(AID id, Dirs dir){
        if (dir == Dirs.Stay){
            return true;
        }
        Entity ent = getByAID(id);
        if (ent != null){
            return moveEntity(ent, dir);
        }
        else{
            System.out.println("Entity you tried moving was null");
            return false;
        }
    }

    boolean isEnemiesCleared(){
        for (Entity ent: entities){
            if (ent.entType == EntType.Bot && !ent.isDead){
                return false;
            }
        }
        return true;
    }

    public AvailableOptions getOptions(AID id){
        AvailableOptions avOpts = new AvailableOptions();
        Entity ent = getByAID(id);

        MoveOptions mvOpts = getMoveOptions(ent);
        AttackOptions attOpts = getAttOpts(ent);

        avOpts.setMvOpts(mvOpts);
        avOpts.setAttOpts(attOpts);

        return avOpts;
    }


    MoveOptions getMoveOptions(Entity ent){
        MoveOptions mvOpts = new MoveOptions();
        int i = ent.i;
        int j = ent.j;
        if (canMoveTo(i+1, j)){
            mvOpts.addDir(Config.Down);
        }
        if (canMoveTo(i-1, j)){
            mvOpts.addDir(Config.Up);
        }
        if (canMoveTo(i, j+1)){
            mvOpts.addDir(Config.Right);
        }
        if (canMoveTo(i, j-1)){
            mvOpts.addDir(Config.Left);
        }
        return mvOpts;
    }

    AttackOptions getAttOpts(Entity ent){
        AttackOptions attOpts = new AttackOptions();
        for (Attack att: attacks){
            int range = att.getRange();
            for (Entity otherEnt: entities){
                if (ent.entType != otherEnt.entType && canReach(ent, otherEnt, range)){
                    AttackEnemy attEnemy = new AttackEnemy();
                    attEnemy.setEnemyID(otherEnt.mark);
                    attEnemy.setAttackType(att);
                    attOpts.addAttackEnemyy(attEnemy);
                }
            }
        }
        return attOpts;
    }

    boolean canReach(Entity one, Entity other, int range){
        int di = other.i-one.i;
        int dj = other.j-one.j;
        int sqrd = di*di+dj*dj;
        int dist = (int)Math.round(Math.sqrt(sqrd));
        return dist <= range;
    }


    Entity getByAID(AID id){
        for (Entity ent: entities){
            if (ent.agent.equals(id)) {
                return ent;
            }
        }
        return null;
    }


    boolean moveEntity(Entity entity, Dirs dir) {
        int i = entity.i;
        int j = entity.j;
        switch (dir) {
            case Up:
                i--;
                break;
            case Down:
                i++;
                break;
            case Right:
                j++;
                break;
            case Left:
                j--;
                break;
        }

        if (!canMoveTo(i, j)) {
            return false;
        }

        entity.setCords(i, j);

        return true;
    }

    void killEntity(AID id){
        Entity ent = getByAID(id);
        ent.isDead = true;
        entities.remove(ent);
    }

    class Entity {
        public int i;
        public int j;
        public AID agent;
        public EntType entType;
        public boolean isDead = false;
        public String mark;

        public Entity(AID agent, EntType tp, int i, int j, String mark) {
            this.agent = agent;
            this.entType = tp;
            this.i = i;
            this.j = j;
            this.mark = mark;
        }

        public void setCords(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    String getEntity(int i, int j) {
        if (mapSkelet[i][j].equals(Wall)) {
            return Wall;
        }
        else {
            for (Entity ent : entities) {
                if (ent.i == i && ent.j == j && !ent.isDead) {
                    return ent.mark;
                }
            }
            return Ground;
        }
    }


    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                str += " " + getEntity(i, j) + " ";
            }
            str += "\n";
        }
        return str.substring(0, str.length()-1);
    }

}
