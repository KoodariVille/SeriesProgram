package sarjaohjelma;

import java.util.ArrayList;

/**
 *
 * @author Omistaja
 */
public class Team {
    private final int Id;
    private final String Name;
//    private Team team1;
//    private Team team2;
    public ArrayList<Team> played = new ArrayList();
        
    public Team(int id, String name){
        Id = id;
        Name = name;
    }
    
//    public Team(Team team1){
//        this.team1 = team1;
//        //this.team2 = team2;
//    }
    
    public int GetId(){
        return Id;
    }
    
    public String GetName(){
        return Name;
    }
    
    @Override
    public String toString(){
        return GetName();
    }
}
