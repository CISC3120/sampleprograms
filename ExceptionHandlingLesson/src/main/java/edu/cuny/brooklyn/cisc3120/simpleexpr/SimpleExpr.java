package edu.cuny.brooklyn.cisc3120.simpleexpr;

public class SimpleExpr {
    public static void main(String[] args) {
      int d1 = Integer.parseInt(args[0]);
      int d2 = Integer.parseInt(args[2]);
      int result;
      switch(args[1]) {
      case "+":
          result = d1 + d2;
          System.out.println(String.join(" ",  args) + " = " + result);
          break;
      case "-":
          result = d1 - d2;
          System.out.println(String.join(" ",  args) + " = " + result);          
      case "*":
          result = d1 * d2;
          System.out.println(String.join(" ",  args) + " = " + result);          
      case "/":
          result = d1 / d2;
          System.out.println(String.join(" ",  args) + " = " + result);          
      }
    }
}

