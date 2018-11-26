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
    
    public Game(){}
    
//    public int GetId(){
//        return ID;
//    }
    
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
