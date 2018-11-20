package sarjaohjelma;

import java.util.ArrayList;
import java.util.Iterator;
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
            teamList.add(new Team(0, "Ei ketään"));
        }
    }
    
    public void CreateRandomRounds(){
        serieRounds = new Team[rounds][teamAmount];
             
        for(int row = 0; row < serieRounds.length; row++){
            CreateRandomRound(row);          
        }
            
    }
    
    public void CreateRandomRound(int row){
        ArrayList<Team> tList = new ArrayList();
        
        teamList.forEach((x) -> {
            tList.add(x);
        });
        
        int turn = 0;
        
        Random rand = new Random();
        int n;
        int col = 0;
        
        while(!tList.isEmpty()){           
            for(Iterator<Team> iterator = tList.iterator(); iterator.hasNext();){
                Team value = iterator.next();
                n = rand.nextInt(teamList.size()) + 0;
                if(value.GetName().equals(teamList.get(n).GetName())){
                    serieRounds[row][col] = teamList.get(n);                 
                    iterator.remove();
                    if((col + 1) % 2 == 0){
                        //joukkueen listaan merkataan ketä vastaan pelattiin vieraissa
                        serieRounds[row][col].playedV.add(serieRounds[row][col-1]);
                    }else{
                        for(int i = 0; i < col; i++){
                            if(serieRounds[row][i].GetName().equals(serieRounds[row][col].GetName())){
                                //joukkueen listaan merkataan ketä vastaan pelattiin kotona
                                serieRounds[row][col].playedH.add(serieRounds[row][col+1]);  
                            }
                        }                       
                    }    
                    col++;
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
        k = 0;
        
        for(Team x : teamList){          
            j = GetTeamCount(x);
            if(j > k){
               k = j;
               t = x; 
            }
        }
//        for(int i = 0; i < teamList.size(); i++){
//            j = GetTeamCount(teamList.get(i));
//            if(j > k){
//               k = j;
//               t = teamList.get(i); 
//            }
//        }
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
        Team t = teamList.get(0);
        
        for(int i = 0; i < teamList.size(); i++){          
            j = GetTeamCount(teamList.get(i));
            if(j < (versusGames * (teamList.size() - 1)) && !teamList.get(i).GetName().equals(team.GetName())){
                t = teamList.get(i);
                break;
            }  
        }       
        return t;
    }
    
    public int GetRoundTeamCount(Team team, int row){
        int num = 0;
        
        for(int col = 0; col < serieRounds[row].length; col++){
            if(team.GetName().equals(serieRounds[row][col].GetName())){
                num++;
            }               
        }
        
        return num;
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
