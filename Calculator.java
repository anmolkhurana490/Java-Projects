import java.util.*;

class InvalidInputException extends Exception {
    public String getMessage(){
        return "Invalid input!";
    }
}

class MaxInputException extends Exception {
    public String getMessage(){
        return "Operands should be less than 100000!";
    }
}

class MaxMultiplierReachedException extends Exception {
    public String getMessage(){
        return "Product should be less than 700000! Enter Operators accordingly";
    }
}

class Calculator {
    void calculate(float a,char op,float b) {
        try {
            if(a>100000 || b>100000){
                throw new MaxInputException();
            }
            if(op=='+'){
                System.out.println("Result = "+(a+b));
            }
            else if(op=='-'){
                System.out.println("Result= "+(a-b));
            }
            else if(op=='*'){
                if(a*b>700000){
                    throw new MaxMultiplierReachedException();
                }
                System.out.println("Result = "+(a*b));
            }
            else if(op=='/'){
                if(b==0){
                    throw new ArithmeticException("Can't Divide by 0");
                }
                System.out.println("Result = "+(a/b));
            }
            else if(op=='%'){
                if(b==0){
                    throw new ArithmeticException("Can't Divide by 0");
                }
                System.out.println("Result = "+(a%b));
            }
            else {
              throw new InvalidInputException();
            }
        }
        catch(InvalidInputException e){
            System.out.println(e.getMessage());
        }
        catch(ArithmeticException e){
            System.out.println(e.getMessage());
        }
        catch(MaxInputException e){
            System.out.println(e.getMessage());
        }
        catch(MaxMultiplierReachedException e){
            System.out.println(e.getMessage());
        }
    }
}

public class Main {
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Calculator cal=new Calculator();
        System.out.println("Welcome to Calculator");
        System.out.println("Input Operands and Operator with Spaces between them\nCan do only one Operation at a time");
        System.out.println("Enter Yes to exit the Calculator and No to Continue");
        float num1,num2;
        char oper;
        while(true){
            System.out.print("Want to Exit: ");
            String str=sc.next();
            if(str.equalsIgnoreCase("yes")){
                break;
            }
            System.out.print("Enter Operation: ");
            try {
                num1=sc.nextFloat();
                oper=sc.next().charAt(0);
                num2=sc.nextFloat();
                cal.calculate(num1,oper,num2);
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Input!");
            }
            catch(Exception e){
                System.out.println(e);
            }                              
            finally {
                sc.nextLine();
            }
        }
    }
}
