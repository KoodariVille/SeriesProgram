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
        serieRounds = new Team[rounds][teamAmount / 2];
        Random rand = new Random();
        
        for(int row = 0; row < rounds; row ++){
            for(int column = 0; column < teamAmount / 2; column ++){
                int n = rand.nextInt(5) + 0;
                int m = rand.nextInt(5) + 0;
                while(m == n){
                    m = rand.nextInt(5) + 0;
                }
                serieRounds[row][column] = new Team(teamList.get(n), teamList.get(m));
            }
        }  
    }
    
    public void PrintSeriesProgram(){
        for(int i = 0; i < serieRounds.length; i++){
            for(int j = 0; j < serieRounds[i].length; j++){
                System.out.print(serieRounds[i][j] + " ");
            }
            System.out.println();
        }
    }
    
}
