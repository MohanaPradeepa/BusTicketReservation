package BusResv;
import java.util.Date;
import java.sql.*;

public class BookingDAO {
	
	public int getBookedCount(int busNo,Date date)throws SQLException
	{
		String query="select count(passenger_name) from booking where bus_no=? and travel_date=?";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(date.getTime());//convert java date to sql date
		pst.setInt(1, busNo);
		pst.setDate(2, sqldate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}
	public void addBooking(Booking booking)throws SQLException
	{
		String query="insert into booking values(?,?,?)";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		java.sql.Date sqldate=new java.sql.Date(booking.date.getTime());
		pst.setString(1,booking.passengerName);
		pst.setInt(2,booking.busNo);
		pst.setDate(3, sqldate);
		pst.executeUpdate();
		
		
		
	}
	

}
