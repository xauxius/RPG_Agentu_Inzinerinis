import jade.core.AID;

import java.util.ArrayList;

// Class managing the map, can give string of encoded map, ensures movement logic
public class Map {
    public static enum EntType{Player, Bot}
    public static enum Dirs{Up, Left, Down, Right}
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

    public Map(ArrayList<AID> players, ArrayList<AID> bots){
        N = mapSkelet.length;
        M = mapSkelet[0].length;
        int playI = 8;
        for (int i = 0; i < players.size(); i++){
            int j = 1;
            switch (i){
                case 0:
                    j = 1;
                    break;
                case 1:
                    j = 3;
                    break;
                case 2:
                    j =5;
                    break;
            }
            entities.add(new Entity(players.get(i), EntType.Player, playI, j, Integer.toString(i)));
        }
        entities.add(new Entity(bots.get(0), EntType.Bot, 1, 1, Enemy));
        entities.add(new Entity(bots.get(1), EntType.Bot, 1, 3, Enemy));
        entities.add(new Entity(bots.get(2), EntType.Bot, 1, 5, Enemy));
        entities.add(new Entity(bots.get(3), EntType.Bot, 2, 2, Enemy));
        entities.add(new Entity(bots.get(4), EntType.Bot, 2, 4, Enemy));
    }


    boolean canMoveTo(int i, int j){
        if (mapSkelet[i][j] == Wall){
            return false;
        }
        for (Entity ent: entities){
            if (ent.i == i && ent.j == j){
                return false;
            }
        }
        return true;
    }

    boolean moveEntityByAID(AID id, Dirs dir){
        for (Entity ent: entities){
            if (ent.agent.equals(id)){
                moveEntity(ent, dir);
            }
        }
        return false;
    }

    void needsUpdate(){

    }

    boolean moveEntity(Entity entity, Dirs dir){
        int i = entity.i;
        int j = entity.j;
        switch(dir){
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

        if (!canMoveTo(i, j)){
            return false;
        }

        entity.setCords(i, j);

        return true;
    }

    class Entity{
        public int i;
        public int j;
        public AID agent;
        public EntType entType;
        public boolean isDead = false;
        public String mark;
        public Entity(AID agent, EntType tp, int i, int j, String mark){
            this.agent = agent;
            this.entType = tp;
            this.i = i;
            this.j = j;
            this.mark = mark;
        }
        public void setCords(int i, int j){
            this.i = i;
            this.j = j;
        }
    }

    String getEntity(int i, int j){
        if (mapSkelet[i][j].equals(Wall)){
            return Wall;
        }
        else{
            for (Entity ent: entities){
                if (ent.i == i && ent.j == j && !ent.isDead){
                    return ent.mark;
                }
            }
            return Ground;
        }
    }
    

    @Override
    public String toString(){
        String str = "";
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                str+=" "+getEntity(i, j)+" ";
            }
            str+="\n";
        }
        return str;
    }

    public static void main(String[] args) {
        ArrayList<AID> players = new ArrayList<>();
        ArrayList<AID> bots = new ArrayList<>();
        players.add(null);
        players.add(null);
        bots.add(null);
        bots.add(null);
        bots.add(null);
        bots.add(null);
        bots.add(null);
        Map map = new Map(players, bots);

        System.out.println(map);
    }
}
