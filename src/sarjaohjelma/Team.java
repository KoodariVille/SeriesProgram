package sarjaohjelma;

/**
 *
 * @author Ville Niemi
 */
public class Team {
    private int Id;
    private String Name;
        
    public Team(int id, String name){
        Id = id;
        Name = name;
    }
    
    public Team(){};
    
    public int GetId(){
        return Id;
    }
    
    public String GetName(){
        return Name;
    }   
    
    @Override
    public String toString(){
        return Name;
    }
}
