/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarjaohjelma;

import java.util.ArrayList;

/**
 *
 * @author Omistaja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Team> teamList = new ArrayList();
        
        teamList.add(new Team(1, "Tappara"));
        teamList.add(new Team(2, "TPS"));
        teamList.add(new Team(3, "Kärpät"));
        teamList.add(new Team(4, "Jokerit"));
        teamList.add(new Team(5, "HIFK"));
        teamList.add(new Team(6, "Ässät"));
//        teamList.add(new Team(7, "HPK"));
//        teamList.add(new Team(8, "Jukurit"));
//        teamList.add(new Team(9, "JYP"));
//        teamList.add(new Team(10, "KalPa"));
//        teamList.add(new Team(11, "Lukko"));
//        teamList.add(new Team(12, "Pelicans"));
//        teamList.add(new Team(13, "KooKoo"));
//        teamList.add(new Team(14, "Saipa"));
//        teamList.add(new Team(15, "Sport"));
        
        SeriesProgram c = new SeriesProgram(2, teamList);
        
        c.CreateRandomRounds();
        c.PrintSeriesProgram();
//        c.SetPlayedGames();
        
        for(int k = 0; k < teamList.get(0).playedH.size(); k++){
            System.out.println(teamList.get(0).playedH.get(k));
        }
        
//        for(int k = 0; k < teamList.get(0).playedV.size(); k++){
//            System.out.println(teamList.get(0).playedV.get(k));
//        }
        
//        for(int j = 0; j < teamList.size(); j++){
//            System.out.println(c.GetTeamCount(teamList.get(j)));   
//        }
        
//        System.out.println(c.GetBiggestTeam().GetName());
//        System.out.println(c.GetSmallestTeam().GetName());
        
//        for(int i = 0; i < teamList.size(); i++){
//            int turn = 0;
//            while(c.GetTeamCount(teamList.get(i)) < (2 * (teamList.size() - 1))){
//                c.FixSeriesProgram();
//                turn ++;
//                if(turn == 100){
//                    break;
//                }
//            }
//        }
//        c.FixSeriesProgram();
//        System.out.println();
//        c.PrintSeriesProgram();
        
//        System.out.println(c.GetBiggestTeam().GetName());
//        System.out.println(c.GetSmallestTeam().GetName());
        
//        for(int j = 0; j < teamList.size(); j++){
//            System.out.println(c.GetTeamCount(teamList.get(j)));
//        }
    }  
}
