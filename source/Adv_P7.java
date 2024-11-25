//DBClient.java for Practical 6 (Using Only one .accdb file)(same for mysql odbc connection)

import java.rmi.*;
import java.io.*;

public class DBClient_P7 {
  public static void main(String[] args) {
    String sql = "", ch = "", ch1 = "", res = "", db="";
    try 
    {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

      while (true) 
      {
        System.out.println("\n*** Select an Option ***");
        System.out.println("1:Retrieve College Information");
        System.out.println("2:Retrieve MTNL Billing Information");
        System.out.println("3:Exit");
        System.out.print("\nEnter your Choice:> ");
        ch = br.readLine();
        if(ch.equals("1") || ch.equals("2"))
        {
          if (ch.equals("1"))
          {
             db="CollegeDB";  // Same as DNS Connection Name
             System.out.println("\n*** Select an Option ***");
             System.out.println("a:Retrieve Student Information");
             System.out.println("b:Retrieve  Books  Informatin");
             System.out.print("\nEnter your Choice (In CollegeDB):> ");
             ch1 = br.readLine();
             if (ch1.equals("a"))
              sql = "select * from Student";
            else
              sql = "select * from Book";
          }
          else if(ch.equals("2"))
          {
              db = "MtnlDB";   // Same as DNS Connection Name
              sql = "select * from Bill";
          }
    
          String url = "rmi://127.0.0.1/DBServer";
          IDb id = (IDb) Naming.lookup(url);
          res = id.getData(sql, db);
          System.out.println("\n-----------------------------------------------------------");
          System.out.print(res);
          System.out.println("-----------------------------------------------------------");
        }
        else if (ch.equals("3"))
          System.exit(0); 
        else
          System.out.println("\nInvalid Option!");
  
      } // end of while loop
    } // end of try
    catch (Exception e) 
    {
      e.printStackTrace();
    }
  }
}