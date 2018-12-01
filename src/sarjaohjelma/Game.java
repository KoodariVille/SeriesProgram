package sarjaohjelma;

/**
 *
 * @author Ville NIemi
 */
public class Game {
    private Team home;
    private Team visitor;
    
    public Game(Team h, Team v){
        home = h;
        visitor = v;
    }
    
    public Game(){}
    
    public void SetHome(Team t){
        home = t;
    }
    
    public void SetVisitor(Team t){
        visitor = t;
    }
       
    public String GetHome(){
        return home.GetName();
    }
    
    public String GetVisitor(){
        return visitor.GetName();
    }
    
    @Override
    public String toString(){
        return "["+ home + " vs " + visitor + "]";
    }
}
