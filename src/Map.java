import RPG.ontology.AvailableOptions;
import jade.core.AID;

import java.util.ArrayList;

// Class managing the map, can give string of encoded map, ensures movement logic
public class Map {
    public static enum EntType {Player, Bot}

    public static enum Dirs {Up, Left, Down, Right}

    public int N;
    public int M;

    static String Wall = "x";
    static String Ground = ".";
    static String Enemy = "E";

    ArrayList<Entity> entities = new ArrayList<Entity>();

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
        Entity ent = getByAID(id);
        if (ent != null){
            return moveEntity(ent, dir);
        }
        else{
            System.out.println("Entity you tried moving was null");
            return false;
        }
    }

    public AvailableOptions getOptions(AID id){
        AvailableOptions avOpts = new AvailableOptions();
        Entity ent = getByAID(id);



        return avOpts;
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
