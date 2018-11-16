package sarjaohjelma;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Omistaja
 */
public class SeriesProgram {
    private final int rounds;
    private final int teamAmount;
    private final int versusGames;
    private final ArrayList<Team> teamList;
    private Team[][] serieRounds;
    
    public SeriesProgram(int m, ArrayList<Team> teamList){
        this.teamList = teamList;
        versusGames = m;
        if(teamList.size() % 2 == 0){
            teamAmount = teamList.size();
            rounds = (teamAmount - 1) * versusGames;           
        }else{
            teamAmount = teamList.size() + 1;
            rounds = (teamAmount - 1) * versusGames;
        }
    }
    
    public void CreateRandomRounds(){
        //luodaan satunnaisesti täytetty taulukko
        //niin ettei joukkue mene ns. itseään vastaan
        serieRounds = new Team[rounds][teamAmount];
        Random rand = new Random();
        int n;
        
        n = rand.nextInt(6) + 0;
        //tehdään tarkistus objekti looppia varten ja
        //taulukon ensimmäiseen soluun pistetään se
        Team t = teamList.get(n);
        serieRounds[0][0] = t;
        
        for(int row = 0; row < rounds; row ++){
            for(int column = 0; column < teamAmount; column ++){              
                n = rand.nextInt(6) + 0;
                //katsotaan onko tarkistus olion nimi sama, kuin arvottu
                if(t.GetName().equals(teamList.get(n).GetName())){
                    //olion ollessa sama niin katsotaan onko arvottu numero 0, 
                    //jos on niin lisätään yks, jos ei niin miinustetaan yks
                    if(n == 0){
                        n++;
                        serieRounds[row][column] = teamList.get(n);
                        //pistetään myös tarkistus olioon arvottu olio,
                        //jotta seuraavalla kierroksella ei arvota samaa
                        t = teamList.get(n);
                    }else{
                        n--;
                        serieRounds[row][column] = teamList.get(n);
                        t = teamList.get(n);
                    }
                }else{
                    serieRounds[row][column] = teamList.get(n);
                    t = teamList.get(n);
                }
            }
        }
    }
    
    public int CheckSeriesProgram(Team team){
        int count = 0;
        for(int row = 0; row < serieRounds.length; row++){
            for(int col = 0; col < serieRounds[row].length; col++){
                if(serieRounds[row][col].GetName().equals(team.GetName())){
                    count++;
                }
            }
        }
        return count;
    }
    
    public Team CheckSeriesProgram(){
        int j;
        Team t = new Team();
        for(int i = 0; i < teamList.size(); i++){
            j = CheckSeriesProgram(teamList.get(i));
            if(j < 10){
                t = teamList.get(i);
                break;
            }  
        }
        return t;
    }
    
    public void FixSeriesProgram(){
        for(int i = 0; i < teamList.size(); i++){
            if(CheckSeriesProgram(teamList.get(i)) > 10){
                for(int row = 0; row < serieRounds.length; row++){
                    for(int col = 0; col < serieRounds[row].length; col++){
                        if(serieRounds[row][col].GetName().equals(teamList.get(i).GetName())){
                            serieRounds[row][col] = CheckSeriesProgram();
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void PrintSeriesProgram(){
        for(int i = 0; i < serieRounds.length; i++){
            System.out.print("[Round:" + (i+1) + "]");
            for(int j = 0; j < serieRounds[i].length; j++){
                if((j + 1) % 2 == 0){
                    System.out.print(serieRounds[i][j] + "]");
                }
                else{
                    System.out.print("[" + serieRounds[i][j] + " vs ");
                }               
            }
            System.out.println();
        }
    }
    
}
