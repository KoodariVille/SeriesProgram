/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarjaohjelma;

/**
 *
 * @author VPC
 */
public class Game {
//    private final int ID;
    private Team home;
    private Team visitor;
    
    public Game(Team h, Team v){
//        this.ID = ID;
        home = h;
        visitor = v;
    }
    
//    public int GetId(){
//        return ID;
//    }
    
    public void SetHome(Team t){
        home = t;
    }
    
    public void SetVisitor(Team t){
        visitor = t;
    }
    
    public Team GetHome(){
        return home;
    }
    
    public Team GetVisitor(){
        return visitor;
    }
    
    @Override
    public String toString(){
        return "["+ home + " vs " + visitor + "]";
    }
}
