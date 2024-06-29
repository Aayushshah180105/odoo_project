import java.util.Scanner;
import java.sql.*;
class Register extends Recycler{
    String Name="";
    String Email="";
    String Password="";
    String Cpassword="";
    Scanner input=new Scanner(System.in);
    Recycler re=new Recycler();
    Connection cn=null;
    Statement st;
    PreparedStatement ps;
    void register()
    {
        System.out.println("Enter your Name = ");
        Name=input.next();
        System.out.println("Enter your Email");
        Email=input.next();
        System.out.println("Enter your Password");
        Password=input.next();
        System.out.println("Confirm your Password");
        Cpassword=input.next();
        if(Password.equals(Cpassword)){
            System.out.println("Registration Successful");
        }
        else{
            register();
        }
    }
    void dbconnection()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3302/e-waste_management","root","");
            st=cn.createStatement();
            System.out.println("Connected to database successfully");

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    void login()
    {
        try{
        dbconnection();
        String log_name,log_mail,log_pass;
        System.out.println("Enter your name");
        log_name=input.next();
        System.out.println("Enter your email = ");
        log_mail=input.next();
        System.out.println("Enter your Password");
        log_pass=input.next();
        if(log_name.equals(Name)&&log_mail.equals(Email)&&log_pass.equals(Password)){
            System.out.println("Login Successful");
            ps=cn.prepareStatement("INSERT INTO User VALUES(?,?,?)");
            ps.setString(1,log_name);
            ps.setString(2,log_mail);
            ps.setString(3,log_pass);
            int i=ps.executeUpdate();
            System.out.println(i+" records inserted");
        }
        else{
            login();
        }
    }catch(Exception e){
        System.out.println(e.getMessage());
    }
    }
    
    
    public static void main(String[] args) 
    {
        Register r=new Register();
        int user_choice=1;
        int choice=1;
        r.dbconnection();
        do{
            System.out.println("1. User Register\n2. Recycler Register\n3. Admin Register\n4. Exit");
            choice=r.input.nextInt();
            switch(choice){
                case 1:
                    r.register();
                    r.login();
                    do                  
                    {
                        System.out.println("Welcome User");
                        System.out.println("1.Name of all recyclers");
                        System.out.println("2. Enter the type of your waste");
                        System.out.println("3. Book a request for Waste");
                        System.out.println("4. View your profile");
                        System.out.println("5. Track your waste collection status");
                        System.out.println("6. Delete your Request");
                        System.out.println("7. Update your Request");
                        System.out.println("8. Update your profile");
                        System.out.println("9. Logout");
                        System.out.println("Enter your choice");
                        user_choice=r.input.nextInt();
                    }while(user_choice<=9);
                    break;
                case 2:
                   
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }while(choice<=3);
    }
}
