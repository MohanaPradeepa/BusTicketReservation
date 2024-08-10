import java.sql.*;
public class JDBCDemo {

	public static void main(String[] args)throws Exception {
		
		sp3();
		

	}
	public static void readRecords()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		String query="select * from employee";
		
		Connection con=DriverManager.getConnection(url,username,password);
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);//returns a table so resultset
		while(rs.next())
		{
		System.out.println("Id is "+rs.getInt(1));
		System.out.println("Name is "+rs.getString(2));
		System.out.println("Salary is "+rs.getInt(3));
		}
		
		con.close();
	}
	//insert rec using normal statement
	public static void insertRecord()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		String query="insert into employee value(4,'raj',25000)";
		
		Connection con=DriverManager.getConnection(url,username,password);
		Statement st=con.createStatement();
		int rows=st.executeUpdate(query);//it is just going to insert or make changes in the table so executeUpdate and it will returns an integerlike how many rows or cols are added
		System.out.println("number of rows affected "+rows);
		
		con.close();
		
	}
	//insert input into table using prepared statement
	public static void insertUsingPst()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		int id=5;
		String name="kavi";
		int salary=50000;
		String query="insert into employee value(?,?,?);";
		
		
		Connection con=DriverManager.getConnection(url,username,password);
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, id);
		pst.setString(2, name);
		pst.setInt(3, salary);
		int rows=pst.executeUpdate();
		System.out.println("number of rows affected "+rows);
		
		con.close();
		
	}
	//calling sp using callable statement
	public static void sp()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		
		
		
		Connection con=DriverManager.getConnection(url,username,password);
		CallableStatement cst=con.prepareCall("{call GetEmp()}");
		ResultSet rs=cst.executeQuery();
		while(rs.next())
		{
		System.out.println("Id is "+rs.getInt(1));
		System.out.println("Name is "+rs.getString(2));
		System.out.println("Salary is "+rs.getInt(3));
		}
		con.close();
		
	}
	//calling sp with input parameter
	public static void sp2()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		int id=3;
		
		
		
		Connection con=DriverManager.getConnection(url,username,password);
		CallableStatement cst=con.prepareCall("{call GetEmpById(?)}");
		cst.setInt(1, id);
		ResultSet rs=cst.executeQuery();
		while(rs.next())
		{
		System.out.println("Id is "+rs.getInt(1));
		System.out.println("Name is "+rs.getString(2));
		System.out.println("Salary is "+rs.getInt(3));
		}
		con.close();
		
	}
	public static void sp3()throws Exception{
		String url="jdbc:mysql://localhost:3306/jdbcdemo";
		String username="root";
		String password="Darshini@318";
		int id=1;
		
		
		
		Connection con=DriverManager.getConnection(url,username,password);
		CallableStatement cst=con.prepareCall("{call GetNameByIdExam(?,?)}");
		cst.setInt(1, id);// to set the input parameter 
		cst.registerOutParameter(2,Types.VARCHAR);//to call out parameters in stored function
	   
		cst.executeUpdate();
	    System.out.println(cst.getString(2));
		
		con.close();
		
	}

}
