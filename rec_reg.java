import java.util.*;
import java.sql.*;
public class rec_reg {
    String rec_name,rec_email,rec_pass,rec_firm,waste_type,address;
    int contact_number;
    Connection cn;
    Statement st;
    PreparedStatement ps;
    Scanner s=new Scanner(System.in);
    void recycler_reg(){
        
    }
    
    void connect()
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost:3302/e-waste_management","root","");
            st=cn.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    void insert()
    {
        try{
            connect();
            String rec_log_name,rec_log_mail,rec_log_pass;
            System.out.println("Enter your name = ");
            rec_log_name=s.next();
            System.out.println("Enter your email = ");
            rec_log_mail=s.next();
            System.out.println("Enter your password = ");
            rec_log_pass=s.next();
            System.out.println("Enter your name = ");
            rec_name=s.next();
            System.out.println("Enter your email = ");
            rec_email=s.next();
            System.out.println("Enter your password = ");
            rec_pass=s.next();
            System.out.println("Enter your firm name = ");
            rec_firm=s.next();
            System.out.println("Enter the type of waste you handle = ");
            waste_type=s.next();
            System.out.println("Enter your address = ");
            address=s.next();
            System.out.println("Enter your contact number = ");
            contact_number=s.nextInt();
            if(rec_name.equals(rec_log_name)&&rec_email.equals(rec_log_mail)&&rec_pass.equals(rec_log_pass)){
                ps=cn.prepareStatement("insert into recycler values(?,?,?,?,?,?,?)");
                ps.setString(1,rec_name);
                ps.setString(2,rec_email);
                ps.setString(3,rec_pass);
                ps.setString(4,rec_firm);
                ps.setString(5,waste_type);
                ps.setString(6,address);
                ps.setInt(7,contact_number);
                int i=ps.executeUpdate();
            System.out.println(i+" records inserted");
            }
            else
            {
                insert();
            }
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}