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
        
        SeriesProgram c = new SeriesProgram(2, teamList);
        
        c.CreateRandomRounds();
        c.PrintSeriesProgram();
    }
    
}
