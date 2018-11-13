package sarjaohjelma;

/**
 *
 * @author Omistaja
 */
public class Team {
    private int Id;
    private String Name;
    private Team team1;
    private Team team2;
        
    public Team(int id, String name){
        Id = id;
        Name = name;
    }
    
    public Team(Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
    }
    
    public int GetId(){
        return Id;
    }
    
    public String GetName(){
        return Name;
    }
    
    @Override
    public String toString(){
        return "["+team1.GetName() + " vs " + team2.GetName()+"]";
    }
}
