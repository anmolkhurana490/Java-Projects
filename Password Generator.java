import java.util.Scanner;
import java.util.Random;

class Password {
  RandomChar rc=new RandomChar();
  Random rn=new Random();
  void generatePassword(int passLen,char low,char upp,char spl,char num){
    char pass[]=new char[passLen+1];
    for(int i=0;i<passLen;i++){
    int rand;
      char ch[]={0,0,0,0};
      char ch1,ch2,ch3,ch4;
      if(low=='y'){
        ch[0]=rc.randomChar('a','z');
      }
      if(upp=='y'){
        ch[1]=rc.randomChar('A','Z');
      }
      if(num=='y'){
        ch[2]=rc.randomChar('0','9');
      }
      if(spl=='y'){
        ch1=rc.randomChar('!','/');
        ch2=rc.randomChar(':','@');
        ch3=rc.randomChar('[','`');
        ch4=rc.randomChar('{','~');
        rand=rn.nextInt(4);
        if(rand==0){
          ch[3]=ch1;
        }
        else if(rand==1){
          ch[3]=ch2;
        }
        else if(rand==2){
          ch[3]=ch3;
        }
        else {
          ch[3]=ch4;
        }
      }
      do {
        rand=rn.nextInt(4);
      } while(ch[rand]==0);
      if(rand==0){
        pass[i]=(char)ch[0];
      }
      else if(rand==1){
        pass[i]=(char)ch[1];
      }
      else if(rand==2){
        pass[i]=(char)ch[2];
      }
      else {
        pass[i]=(char)ch[3];
      }
    }
    pass[passLen]='\0';
    System.out.println("Generated Password: "+pass);
  }
}
class RandomChar {
  Random rn=new Random();
  char randomChar(char chS,char chE){
    int d=chE-chS+1;
    return (char)(rn.nextInt(d)+chS);
  }
}
class Main {
  public static void main(String[] args){
    int passLength;
    char lowCase,uppCase,speChar,num,ch;
    Scanner sc=new Scanner(System.in);
    Password pg=new Password();
    System.out.println("Enter Response in y/n or number accordingly");
    System.out.print("Length of Password: ");
    passLength=sc.nextInt();
    System.out.println("LowerCase: ");
    lowCase=sc.next().charAt(0);
    System.out.print("UpperCase: ");
    uppCase=sc.next().charAt(0);
    System.out.print("Special Character: ");
    speChar=sc.next().charAt(0);
    System.out.print("Number: ");
    num=sc.next().charAt(0);
    do {
      pg.generatePassword(passLength,lowCase,uppCase,speChar,num);
      System.out.print("Want to generate Again (y/n): ");
      ch=sc.next().charAt(0);
    } while(ch=='Y'||ch=='y');
  }
}



