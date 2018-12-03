package sarjaohjelma;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Ville Niemi
 */
public class SeriesProgram {
    private final int rounds;
    private final int teamAmount;
    private final int versusGames;
    private final ArrayList<Team> teamList;
    private ArrayList<Game> games;
    private Game[][] serie;
    
    public SeriesProgram(int m, ArrayList<Team> teamList){
        this.teamList = teamList;       
        versusGames = m;
        if(teamList.size() % 2 == 0){
            teamAmount = teamList.size();
            rounds = (teamAmount - 1) * versusGames;           
        }else{
            teamList.add(new Team(0, "Ei ketään"));
            teamAmount = teamList.size();
            rounds = (teamAmount - 1) * versusGames;           
        }
    }
    
    //metodi jolla tehdään lista pelattavista peleistä
    //niin, että jokainen joukkue pelaa kotona kaikkia vastaan
    //näin saadaan koti ja vieras peli jokaiselle
    public void CreateListOfGames(){
        games = new ArrayList();
        ArrayList<Team> tList = new ArrayList();
        
        if(versusGames == 1){
            teamList.forEach((x) -> {
                tList.add(x);
            });
            while(tList.size() > 1){
                for(Team x : teamList){
                    for(int i = 0; i < tList.size(); i++){
                        if(!x.equals(tList.get(i))){
                            games.add(new Game(x,tList.get(i)));
                        }
                    }
                    tList.remove(x);
                }
            }
        }
        else if((versusGames % 2) == 0){
            for(int i = 0; i < (versusGames/2); i++){
                //käydään joukkuelistaa läpi kahdessa loopissa
                //games listaan lisätään uusi Game olio, jos x joukkue ja t joukkue
                //niiden ollessa eri nimisiä näin vältetään päällekkäisyydet ei tule esim. tappara vastaan tappara
                teamList.forEach((x) -> {
                    teamList.stream().filter((t) -> (!x.GetName().equals(t.GetName()))).forEachOrdered((t) -> {
                        games.add(new Game(x,t));
                    });
                });
            }
        }else{
            for(int i = 0; i < ((versusGames-1)/2); i++){
                teamList.forEach((x) -> {
                    teamList.stream().filter((t) -> (!x.GetName().equals(t.GetName()))).forEachOrdered((t) -> {
                        games.add(new Game(x,t));
                    });
                });
            }
            teamList.forEach((x) -> {
                tList.add(x);
            });
            while(tList.size() > 1){
                for(Team x : teamList){
                    for(int i = 0; i < tList.size(); i++){
                        if(!x.equals(tList.get(i))){
                            games.add(new Game(x,tList.get(i)));
                        }
                    }
                    tList.remove(x);
                }
            }
        }
        
    }
    
    //luodaan pelilistasta random kierroksia kunnes lista on tyhjä
    public void CreateRandomRounds(){
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
        int num;
        int turn = 0;
        
        CreateListOfGames();
        CreateRandomRounds();
        PrintSeriesProgram();
        System.out.println();
        
        while(check == false){
            num = 0;
            
            //pyöritetään looppia kunnes joka kierros palauttaa nollan
            for(int i = 0; i < serie.length; i++){
                num += RoundCheck(i);
                if(num != 0){
                    FixRound(i);
                }
            }
            check = num == 0;
            turn++;
//            if(turn > 50){
//                break;
//            }
        }
        PrintSeriesProgram();
    }
    
    public int RoundCheck(int row){
        ArrayList<Team> tList = new ArrayList();
        int num;
        
        //tehdään uusi lista joukkeista
        teamList.forEach((x) -> {
            tList.add(x);
        });
        
        //käydään joukkuelistaa läpi ja katsotaan onko kierroksella joukkuetta
        //sen ollessa poistetaan listalta
        teamList.forEach((Team t) -> {
            for (Game item : serie[row]) {
                if (item.GetHome().equals(t.GetName()) || item.GetVisitor().equals(t.GetName())) {
                    tList.remove(t);
                }
            }
        });
        
        //palautetaan koko, jos se ei ole nolla kierroksessa ei ole kaikkia joukkeita 
        num = tList.size();
        return num;
    }
    
    //tulostaa sarjaohjelman
    private void PrintSeriesProgram(){
        for(int i = 0; i < serie.length; i++){
            System.out.print("[Round:" + (i+1) + "]");
            for (Game item : serie[i]) {
                System.out.print(item);
            }
            System.out.println();
        }
    }
    
    private void FixRound(int row){
        int count = 0;
        int column;
        int roundWTeam;
        
        //katsotaan löytyy kierrokselta joukkuetta ja jos se löytyy enemmän kuin kerran
        //haetaan sen jokin sen esiintyminen kolumni 
        for(Team x : teamList){
            for(int col = 0; col < serie[row].length; col++){
                if(serie[row][col].GetHome().equals(x.GetName())||serie[row][col].GetVisitor().equals(x.GetName())){
                    count++;
                }
            }
            if(count > 1){
                column = GetCol(x, row);
                //etsitään kierros missä tarkastelun joukkuetta ei ole
                roundWTeam = GetRoundWTeam(x);
                //tämän saatua vaihdetaan pelien paikkaa
                ChangeGame(serie[row][column], roundWTeam);             
            }
            count = 0;
        }
    }
    
    //haetaan riviltä kolumnit missä annettu joukue esiintyy 
    //arvotaan niiden väliltä mistä kohdasta joukkue vaihdetaan
    private int GetCol(Team t, int row){
        ArrayList<Integer> list = new ArrayList();
        Random rand = new Random();
        int n;

        for(int col = 0; col < serie[row].length; col++){
            if(serie[row][col].GetHome().equals(t.GetName())||serie[row][col].GetVisitor().equals(t.GetName())){
                list.add(col);
            }
        }
        n = list.get(rand.nextInt(list.size()) + 0);
        
        return n;
    }
    
    //etsitään kierrokset ilman parametrin tiimiä
    //tehdään kierros niistä ja arvotaan palautettava rivi niistä
    private int GetRoundWTeam(Team t){
        ArrayList<Integer> list = new ArrayList();
        Random rand = new Random();
        int count = 0;
        int n;
        
        for(int row = 0; row < serie.length; row++){
            for(int col = 0; col < serie[row].length; col++){
                //tarkistetaan onko solussa joukuetta, jos ei ole counteri nousee
                if(!serie[row][col].GetHome().equals(t.GetName())&&!serie[row][col].GetVisitor().equals(t.GetName())){
                    count++;
                }
            //sen ollessa saman verran kuin kierroksen pituus lisätään kierros listaan    
            }if(count == serie[row].length){
                list.add(row);
            }
            count = 0;
        }
        
        n = list.get(rand.nextInt(list.size()) + 0);
        return n;
    }
    
    //pelin vaihto metodi
    private void ChangeGame(Game source, int r2){
        int r1 = Integer.MAX_VALUE;
        int c1 = Integer.MAX_VALUE;
        int c2 = Integer.MAX_VALUE;
        Random rand = new Random();
        Game sourceGame = new Game();
        Game target;
        int n;
        
        for(int row = 0; row < serie.length; row++){
            for(int col = 0; col < serie[row].length; col++){
                if(serie[row][col].equals(source)){
                    r1 = row;
                    c1 = col;
                    sourceGame = source;
                }
            }
        }
        //arvotaan kohde kierroksen kolumni
        n = rand.nextInt(serie[r2].length);
        target = serie[r2][n];
        //pistetään vaihdettavaan peliin kohde
        serie[r1][c1] = target;
        //ja kohteeseen vaihdettava peli
        serie[r2][n] = sourceGame;      
    }  
}
