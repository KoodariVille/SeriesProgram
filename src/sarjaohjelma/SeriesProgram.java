package sarjaohjelma;

import java.util.ArrayList;
//import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
//import java.util.Set;

/**
 *
 * @author Omistaja
 */
public class SeriesProgram {
    private final int rounds;
    private final int teamAmount;
    private final int versusGames;
    private final ArrayList<Team> teamList;
    private ArrayList<Game> games;
//    private Team[][] serieRounds;
    private Game[][] serie;
    
    public SeriesProgram(int m, ArrayList<Team> teamList){
        this.teamList = teamList;       
        versusGames = m;
        if(teamList.size() % 2 == 0){
            teamAmount = teamList.size();
            rounds = (teamAmount - 1) * versusGames;           
        }else{
//            teamList.add(new Team(0, "Ei ketään"));
            teamAmount = teamList.size();
            rounds = (teamAmount - 1) * versusGames;           
        }
    }
    
    //metodi jolla tehdään lista pelattavista peleistä
    //niin, että jokainen joukkue pelaa kotona kaikkia vastaan
    //näin saadaan koti ja vieras peli jokaiselle
    public void CreateListOfGames(){
        games = new ArrayList();
        
        //käydään joukkuelistaa läpi kahdessa loopissa
        //games listaan lisätään uusi Game olio, jos x joukkue ja t joukkue
        //niiden ollessa eri nimisiä näin vältetään päällekkäisyydet ei tule esim. tappara vastaan tappara
        teamList.forEach((x) -> {
            teamList.stream().filter((t) -> (!x.GetName().equals(t.GetName()))).forEachOrdered((t) -> {
                games.add(new Game(x,t));
            });
        });
        
        
//        games.forEach((x) -> {
//            System.out.println(x.toString());
//        });
    }
    
    public void CreateRandomRounds(){
//        serieRounds = new Team[rounds][teamAmount];
        serie = new Game[rounds][(teamAmount/2)];
        Random rand = new Random(games.size());
        int n;
        
        while(!games.isEmpty()){
            for(int row = 0; row < serie.length; row++){
                for(int col = 0; col < serie[col].length; col++){
                    n = rand.nextInt(games.size()) + 0;
                    serie[row][col] = games.get(n);
                    games.remove(n);
                }
            }
        }  
    }
    
    public void FixRounds(){
        Game g;
        int check;
        
        for(int row = 0; row < serie.length; row++){
            for(Team x : teamList){
                check = CheckRound(row, x);
                if(check > 1){
                    FindRoundWTeam(row, x);
                }
            }
        }
    }
    
    public int CheckRound(int row, Team t){
        int count = 0;
        for(int col = 0; col < serie[row].length; col++){
            if(t.GetName().equals(serie[row][col].GetHome().GetName()) || t.GetName().equals(serie[row][col].GetVisitor().GetName())){
                count++;
            }
        }
        return count;
    }
    
    public void FindRoundWTeam(int row, Team t){
        int count = 0;
        ArrayList<Game> g;
        if(row != games.size()){
            row++;
            for(int col = 0; col < serie[row].length; col++){
                if(t.GetName().equals(serie[row][col].GetHome().GetName()) || t.GetName().equals(serie[row][col].GetVisitor().GetName())){
                    count++;
                }
            }
            if(count == 0){
                g = FindMissingTeam();
            }
        }      
    }
    
//    public void CreateRandomRound(int row){
//        ArrayList<Team> tList = new ArrayList();
//        
//        teamList.forEach((x) -> { tList.add(x); });
//                
//        Random rand = new Random();
//        int n;
//        int col = 0;
//        
//        while(!tList.isEmpty()){           
//            for(Iterator<Team> iterator = tList.iterator(); iterator.hasNext();){
//                Team value = iterator.next();
//                n = rand.nextInt(teamList.size()) + 0;
//                if(value.GetName().equals(teamList.get(n).GetName())){
//                    serieRounds[row][col] = teamList.get(n);                 
//                    iterator.remove();
//                    col++;
//                }
//            }
//
//        }       
//    }    
    
    public void PrintSeriesProgram(){
        for(int i = 0; i < serie.length; i++){
            System.out.print("[Round:" + (i+1) + "]");
            for (Game item : serie[i]) {
                System.out.print(item);
            }
            System.out.println();
        }
    }
    
}
