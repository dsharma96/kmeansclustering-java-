import java.sql.*;
class Connectiondb
{
    public static void main(String args[]) throws Exception
    {
        
		//connecting to database
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String userName = "sa";
        String password = "a";
        String url = "jdbc:sqlserver://localhost:1433"+";databaseName=mjcetDB";
        Connection con = DriverManager.getConnection(url, userName, password);
        //executing sql query
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("Select * from docs");
        while(rs.next())
        {
			//getting contents of database(myfileid and prescreption
        	System.out.println(rs.getInt(1)+ " "+rs.getString(2));
        }
		//closing database connection 
        con.close();
    } 
}

