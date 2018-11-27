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
    }
    
    public void CreateRandomRounds(){
//        serieRounds = new Team[rounds][teamAmount];
        serie = new Game[rounds][(teamAmount/2)];
        Random rand = new Random(games.size());
        int n;
        
        while(!games.isEmpty()){
            for (Game[] x : serie) {
                for (int col = 0; col < serie[col].length; col++) {
                    n = rand.nextInt(games.size()) + 0;
                    x[col] = games.get(n);
                    games.remove(n);
                }
            }
        }  
    }
    
    public void Run(){
        boolean check = false;
        int num = 0;
        int turn = 0;
        
        CreateListOfGames();
        CreateRandomRounds();
        PrintSeriesProgram();
        System.out.println();
        
        while(check == false){
            FixRounds();
            for(int i = 0; i < serie.length; i++){
                num += RoundCheck(i);            
            }
            check = num == 0;
            turn++;
            if(turn > 50){
                break;
            }
        }
        PrintSeriesProgram();
    }
    
    public int RoundCheck(int row){
        ArrayList<Team> tList = new ArrayList();
        int num;
        teamList.forEach((x) -> {
            tList.add(x);
        });
        
        teamList.forEach((Team t) -> {
            for (Game item : serie[row]) {
                if (item.GetHome().equals(t.GetName()) || item.GetVisitor().equals(t.GetName())) {
                    tList.remove(t);
                }
            }
        });
//        System.out.println(tList.size());
        num = tList.size();
        return num;
    }
    
    public void FixRounds(){
        Game game;
        int c, r, c1;
//        int count = 0;
        
        for(int row = 0; row < serie.length; row++){
            for(Team x : teamList){
                c = CalcTeam(row, x);
                if(c > 1){
                    System.out.println(x.GetName() + "  " + row);
                    c = GetCol(row, x);
                    game = serie[row][c];                  
                    r = GetRoundWTeam(x);
                    if(r < (rounds + 1)){
//                        c1 = ColFinder(r, game);
                        
                        serie[row][c] = serie[r][c];
                        serie[r][c] = game;
//                        for(Team t : teamList){
//                            c1 = CalcTeam(r, t);
//                            if(c1 > 1){
//                                c1 = GetCol(r, t);
//                                serie[row][c] = serie[r][c1];
//                                serie[r][c1] = game;
//                            }
//                        }
                        
                    }
//                    serie[row][c] = serie[r][c];
//                    serie[r][c] = game;
                }
            }
        }
    }
    
    public int CalcTeam(int row, Team t){
        int count = 0;
        for (Game item : serie[row]) {
            if (t.GetName().equals(item.GetHome()) || t.GetName().equals(item.GetVisitor())) {              
                count++;
            }
        }
        return count;
    }   
    
    public int GetCol(int row, Team t){
        int c = 0;
        for(int col = 0; col < serie[row].length; col++){
            if(t.GetName().equals(serie[row][col].GetHome()) || t.GetName().equals(serie[row][col].GetVisitor())){               
                c = col;
                break;
            }
        }
//        System.out.println("column: " + c);
        return c;
    }
    
//    public int ColFinder(int row, Game game){
//        int c = (teamAmount/2+1);
//        for(int col = 0; col < serie[row].length; col++){
//            if(!game.GetHome().equals(serie[row][col].GetHome()) && !game.GetVisitor().equals(serie[row][col].GetVisitor()) && !game.GetVisitor().equals(serie[row][col].GetHome()) && !game.GetHome().equals(serie[row][col].GetVisitor())){
//                c = col;
////                break;
//            }
//        }
//        return c;
//    }
    
    public int GetRoundWTeam(Team t){
        int count;
        int r = (rounds + 1);
        
        for(int row = 0; row < serie.length; row++){
            count = CheckRound(row, t);
            if(count == serie[row].length){
                r = row;
                break;
            }
//            for (Game item : serie[row]) {
//                if (!t.GetName().equals(item.GetHome()) && !t.GetName().equals(item.GetVisitor())) {              
//                    count++;
//                }
//                if(count > 0){
//                    r = row;
//                    break;
//                }
//            }
//            if(count > 0){
//                r = row;
//                break;
//            }
        }
//        System.out.println("round: " + r);
        return r;
    }
    
    public int CheckRound(int row, Team t){
        int count = 0;
        
        for (Game item : serie[row]) {
            if (!t.GetName().equals(item.GetHome()) && !t.GetName().equals(item.GetVisitor())) {              
                count++;
            }
//                if(count > 0){
//                    r = row;
//                    break;
//                }
        }      
        return count;
    }
    
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
