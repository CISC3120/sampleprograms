package edu.cuny.brooklyn.cisc3120.gui;

public class ComputerScienceQuoteDataModel {
    private static final String[] QUOTES = {
            "In computing, the mean time to failure keeps getting shorter.",
            "Everything should be built top-down, except the first time.",
            "Computers are good at following instructions, but not at reading your mind.",
            "Computer Science is no more about computers than astronomy is about telescopes.",
            "Besides a mathematical inclination, an exceptionally good mastery of one's native tongue is the most vital asset of a competent programmer." 
    };
    private static final String[] AUTHORS = {
            "Alan Perlis",
            "Alan Perlis",
            "Donald Knuth",
            "Edsger Dijkstra",
            "Edsger Dijkstra"
    };
    private static final String[] PORTRAITS = {
            "alan_perlis.jpg",
            "alan_perlis.jpg",
            "don_knuth.jpg",
            "edsger_dijkstra.jpg",
            "edsger_dijkstra.jpg"            
    };
    
    public static String getQuote(int index) {
        return QUOTES[index];
    }
    
    public static String getAuthor(int index) {
        return AUTHORS[index];
    }
    
    public static String getPortrait(int index) {
        return PORTRAITS[index];
    }
    
    public static int getNumOfQuotes() {
        return QUOTES.length;
    }
}
