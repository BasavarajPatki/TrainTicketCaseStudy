package caseStudy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TrainDao {
	String DRIVER_NAME="com.mysql.cj.jdbc.Driver";
	String DB_URL="jdbc:mysql://localhost:3306/casestudy?autoReconnect=true&useSSL=false";
	String USERNAME="root";
	String PASSWORD="root";
	
	Train findTrain(int Train_no) {
		int id=Train_no;
		Train train = null;
		try {
			Class.forName(DRIVER_NAME);
			
			
			Connection con=DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			
			
			PreparedStatement ps=con.prepareStatement("select * from trains where TRAIN_NO= ?");
			ps.setInt(1, id);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				train = new Train(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDouble(5));
				
			}
			
			con.close();
			
		}catch (Exception e) {
			
		}
		return train;
		
	}
}
