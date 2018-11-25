/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sarjaohjelma;

import java.util.ArrayList;
import java.util.Random;

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
        
        c.CreateListOfGames();
        c.CreateRandomRounds();
        c.PrintSeriesProgram();
        
    }  
}
