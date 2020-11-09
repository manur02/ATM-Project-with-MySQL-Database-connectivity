/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;
import java.util.List;
/**
 *
 * @author HP
 */
public class App {
    
    public static float balance;
    public static int a, choice;
    public static String AccNum, Anum;
    public static String AccPwd, Apwd;
    public static List<String> names = new ArrayList<String>();
    public static List<String> acc = new ArrayList<String>();
    public static List<String> pass = new ArrayList<String>();
    public static List<String> bal = new ArrayList<String>();
    public static void main(String[] args){ 
        startMenu();
       
        
    }
   
    public static void startMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----Welcome to Ren Banks----");
        System.out.println("Enter Account no.");
        String AccNo = sc.nextLine();
        System.out.println("\nEnter Password:\n");
        String Pwd = sc.nextLine();
        
         try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/myatm", "root", "root" );
            Statement s = c.createStatement();
            String counter = s.executeQuery("select count(*) from Custdetails").toString();
            ResultSet rs = s.executeQuery("select * from CustDetails");
           
            
            while(rs.next())
            {
                
                names.add(rs.getString("Cname"));
                //System.out.println(names);
                acc.add(rs.getString("CAccNo"));
                //AccNum = acc;
                bal.add(rs.getString("AccBalance"));
                pass.add(rs.getString("password"));
                //AccPwd = pass;
              
                
            }
                 //converting hashsets into list
//                    List<String> list1 = new ArrayList<String>(acc);
//                    List<String> list2 = new ArrayList<String>(pass);
//                    List<String> list3 = new ArrayList<String>(bal);
                    
//                    System.out.println(acc);
//                     System.out.println(bal);
//                      System.out.println(pass);
                      
                    for(int i = 0; i< acc.size(); i++){
                        
                       if(acc.get(i).equals(AccNo) && pass.get(i).equals(Pwd))
                            {
                                Anum = acc.get(i);
                                Apwd = pass.get(i);
                                balance = Float.parseFloat(bal.get(i));
                                mainMenu();
                            }
                       else
                        {   
                   
                            a=1;
                            a++;
                           
                            //startMenu();
                        } 
                        
                    }
                    
                    if(choice < 1){
                        System.out.println("Invalid Input");
                        startMenu();
                    } 
                    if(a==acc.size()){
                         System.out.println("Invalid Input");
                        startMenu();
                    }
                    
                    
//                    System.out.println(list);
//                    balance = Float.parseFloat(bal); 
//                    System.out.println("Welcome "+name);
                
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         
    }
    public static void mainMenu(){
        float tamount, withdraw, deposit, n, i, amount;
      

        Scanner sc = new Scanner(System.in);
        System.out.println("1. Account Details\n2. Check Balance\n3. Deposit\n4. Withdraw\n5. Exit ");
        System.out.println("Enter your choice:");
        choice = sc.nextInt();
        
        
        switch(choice){
            case 1: 
                System.out.println("Account Details");
                mainMenu();
                break;
                
            case 2: 
                System.out.println("Check Balance");
                System.out.println("Balance"+balance);
                mainMenu();
                break;
                
            case 3: 
                System.out.println("Deposit\nEnter Amount:");
                deposit = sc.nextFloat();
                balance = balance + deposit;
                System.out.println("After Deposit Balance is: "+balance);
                updateBalance();
                mainMenu();
                break;
                
            case 4:
                 System.out.println("Withdraw\nEnter Amount:");
                 withdraw = sc.nextFloat();
                 balance = balance - withdraw;
                 System.out.println("After Withdraw Balance is: "+balance);
                 updateBalance();
                 mainMenu();
                 break;
                 
            case 5:
                System.out.println("Thank you!");
                break;
                 
                
                
        }
 
    }
    
    public static void updateBalance(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/myatm", "root", "root" );
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("select * from CustDetails");
            
            s = c.createStatement();
            String sql = "UPDATE CustDetails"+
                   " SET AccBalance ="+balance+" WHERE CAccNo ='"+Anum+"' AND password= '"+Apwd+"'";
           // System.out.println(sql);
            s.executeUpdate(sql);

          
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

