package sarjaohjelma;

import java.util.ArrayList;
import java.util.List;
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
        
        n = rand.nextInt(teamList.size()) + 0;
        //tehdään tarkistus objekti looppia varten ja
        //taulukon ensimmäiseen soluun pistetään se
        Team t = teamList.get(n);
        serieRounds[0][0] = t;
        
        for(int row = 0; row < rounds; row ++){
            for(int column = 0; column < teamAmount; column ++){              
                n = rand.nextInt(teamList.size()) + 0;
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
    
    public int GetTeamCount(Team team){
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
    
    public Team GetBiggestTeam(){
        int j,k;
        Team t = teamList.get(0);
        k = GetTeamCount(teamList.get(0));
        
        for(int i = 0; i < teamList.size(); i++){
            j = GetTeamCount(teamList.get(i));
            if(j > k){
               k = j;
               t = teamList.get(i); 
            }
        }
        return t;
    }
    
    public Team GetSmallestTeam(){
        int j,k;
        Team t = teamList.get(0);
        k = GetTeamCount(teamList.get(0));
        
        for(int i = 0; i < teamList.size(); i++){          
            j = GetTeamCount(teamList.get(i));
            if(j < k){
               k = j;
               t = teamList.get(i); 
            }            
        }      
        return t;
    }
    
    public Team GetSmallestTeam(Team team){
        int j;
        Team t = new Team();
        
        for(int i = 0; i < teamList.size(); i++){          
            j = GetTeamCount(teamList.get(i));
            if(j < (versusGames * (teamList.size() - 1)) && !teamList.get(i).GetName().equals(team.GetName())){
                t = teamList.get(i);
                break;
            }  
        }       
        return t;
    }
    
    public void FixSeriesProgram(){
        //pistetään apu olioon eniten esiintyä joukkue
        Team t = GetBiggestTeam();      
        
        //käydään taulukkoa läpi,
        for(int row = 0; row < serieRounds.length; row++){
            for(int col = 0; col < serieRounds[row].length; col++){
                //jos sarakkeen joukkuuen nimi on sama, kuin joukkueella, jolla eniten pelejä
                if(serieRounds[row][col].GetName().equals(t.GetName())){
                    //pistetään t apu olioon vähiten esiintyvä joukkue
                    t = GetSmallestTeam();
                    //jos sarake + 1 on 2 jaollinen(vierasjoukkueen sarake) ja sarake ei ole rivin viimeinen niin
                    if(col + 1 % 2 == 0&& col != teamList.size()){
                        //tarkistetaan onko edellinen eli kotijoukkue sama kuin t
                        //ettei tule t vastaan t, jos näin on niin haetaan uusi joukkue
                        if(serieRounds[row][col-1].GetName().equals(t.GetName())){
                            //antamalla metodille parametriksi joukkue, jota emme halua
                            //näin se hakee joukkueen, jolla liian vähän pelejä ja ei ole samaa nimeä
                            serieRounds[row][col] = GetSmallestTeam(t);
                        }else{
                            serieRounds[row][col] = t;
                        }
                    }else{
                        serieRounds[row][col] = t;
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
