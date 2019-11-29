import java.util.Scanner;

public class HangmanFigure{
public static void main(String args[]){
/*ignore just for trial
Scanner input = new Scanner(System.in);
System.out.println(" enter number from 1 to 6 ");
int incorrect_guesses= input.nextInt(); */


switch(incorrect_guesses){
case 0:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|");
System.out.println("|");
System.out.println("|");
System.out.println("|___	");
break;

case 1:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|");
System.out.println("|");
System.out.println("|___	");
break;

case 2:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|       |");
System.out.println("|       ");
System.out.println("|___	");
break;

case 3:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|       |");
System.out.println("|      / ");
System.out.println("|___	");
break;

case 4:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|       |");
System.out.println("|      / \\");
System.out.println("|___	");
break;

case 5:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|      /|");
System.out.println("|      / \\");
System.out.println("|___	");
break;

case 6:
System.out.println("--------");
System.out.println("|	|");
System.out.println("|       O");
System.out.println("|      /|\\");
System.out.println("|      / \\");
System.out.println("|___	");
break;


		}
	}
}


