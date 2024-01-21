import java.util.Random;
import java.util.Scanner;
class Rpsgame {
  Random rn=new Random();
  Scanner sc=new Scanner(System.in);
  int cs=0,us=0,cr,ur;
  public void game(){
    System.out.print("Enter your name: ");
    String name=sc.next();
    for(int i=1; cs<5 && us<5; i++){
      System.out.println("\n   Chance "+i+":");
      play(name);
      int win=check();
      if(win==0){
        System.out.println("   There is a tie");
      }
      else if(win==1){
        System.out.println("     "+name+" got a point");
        us++;
      }
      else {
        System.out.println("     Computer got a point");
        cs++;
      }
    }
    System.out.print("\n    Result: ");
    if(us==5){
      System.out.println(name+" wins the match");
    }
    else {
      System.out.println("Computer wins the match");
    }
  }
  public String printrps(int r){
    String response;
    if(r==1){
      response="Rock";
    }
    else if(r==2){
      response="Paper";
    }
    else if(r==3){
      response="Scissor";
    }
    else {
      response="*Nothing*";
    }
    return response;
  }
  public void play(String name){
    do {
      cr=rn.nextInt(3)+1;
      System.out.print("Enter response: ");
      ur=sc.nextInt();
      System.out.println("  Computer - "+printrps(cr));
      System.out.println("  "+name+" - "+printrps(ur));
      if(ur==1 || ur==2 || ur==3){
        break;
      }
      else {
        System.out.println("Your response is invalid! Enter again.");
      }
    } while(true);
  }
  public int check(){
    if(cr==ur){
      return 0;
    }
    if((cr==1 && ur==2) || (cr==2 && ur==3) || (cr==3 && ur==1)){
      return 1;
    }
    else {
      return 2;
    }
  }
}
class Main {
  public static void main(String args[]){
      System.out.println("Enter 1 for Rock");
      System.out.println("Enter 2 for Paper");
      System.out.println("Enter 3 for Scissor");
      Rpsgame rps=new Rpsgame();
      rps.game();
  }
}
