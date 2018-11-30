package sarjaohjelma;

import java.util.ArrayList;

/**
 *
 * @author Ville Niemi
 */
public class Team {
    private int Id;
    private String Name;
//    private Team team1;
//    private Team team2;
    public ArrayList<Team> playedH = new ArrayList();
    public ArrayList<Team> playedV = new ArrayList();
        
    public Team(int id, String name){
        Id = id;
        Name = name;
    }
    
    public Team(){};
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
    
    public int GetPlayedNumH(Team t){
        int num = 0;
        for(Team x : playedH){
            if(x.GetName().equals(t.GetName())){
                num ++;
            }
        }
        return num;
    }
    
    public int GetPlayedNumV(Team t){
        int num = 0;
        for(Team x : playedV){
            if(x.GetName().equals(t.GetName())){
                num ++;
            }
        }
        return num;
    }
    
    @Override
    public String toString(){
        return GetName();
    }
}
