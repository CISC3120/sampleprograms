package cisc3120.game.tester;

import cisc3120.game.SimpleDotCom;

public class SimpleDotComTester {
    public static void main(String[] args)
    {
        SimpleDotCom dot = new SimpleDotCom();
        int[] locations = {2, 3, 4};
        dot.setLocationCells(locations);
        String userGuess = "2";
        String result = dot.checkYourself(userGuess);
        if (result.equals("hit")) {
        	System.out.println("The result is \"" + result + "\" as expected.");
        } else {
        	System.out.println("The result is \"" + result + "\" *NOT* as expected.");
        }
    }
}
