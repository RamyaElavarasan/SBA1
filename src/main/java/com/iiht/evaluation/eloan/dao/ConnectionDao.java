package com.iiht.evaluation.eloan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.iiht.evaluation.eloan.dto.LoanDto;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

public class ConnectionDao {
	private static final long serialVersionUID = 1L;
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;

	public ConnectionDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

	public Connection connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				System.out.println("Driver loaded");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
		return jdbcConnection;
	}

	public void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	// put the relevant DAO methods here..
	
	//User Query
	
	public static String ADD_USER_QRY=
			"INSERT INTO Users VALUES(?,?)";
	public static String VAL_USER_QRY=
			"SELECT * FROM Users WHERE username=? and password=?";
	//Loan Info Query
	public static String APP_LOAN_REGISTER_QRY=
			"INSERT INTO loaninfo VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	public static String LOAN_DISPLAY_QRY=
			"SELECT applno,status FROM loaninfo WHERE applno=?";
	public static String GET_LOAN_TRACK_STATUS="SELECT * FROM loaninfo WHERE applno=? and status='New'";
	public static String GET_ALL_LOAN_QUERY = "SELECT * FROM loaninfo";
	public static String APP_LOAN_EDIT_QRY=
			"UPDATE contacts SET amtrequest=?,purpose=?,doa=?,bstructure=?,bindicator=?,tindicator=?,baddress=?,email=?,mobile=?,status=? WHERE applno=?";
	//Approved loan Query
	public static String APP_APPROVED_ADD_QRY=
			"INSERT INTO Approvedloan  (applno,amotsanctioned,loanterm,psd,lcd,emi) VALUES(?,?,?,?,?,?)";
	public static final String UPDATE_LOAN_STATUS_QRY = 
			"UPDATE loaninfo SET status=? WHERE applno=?";
	
	public User addUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		if (user != null) {
		try(Connection con = connect();
				PreparedStatement pst = con.prepareStatement(ADD_USER_QRY)){
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.executeUpdate();
		 }
		
		catch (SQLException e) {
			
			System.out.println(e.getStackTrace());
		}
		}
		return user;

	}
	
	public User getUser(User user) throws SQLException {
		// TODO Auto-generated method stub
		if(user!=null){
		try(Connection con = connect();
				PreparedStatement pst = con.prepareStatement(VAL_USER_QRY)){
			pst.setString(1, user.getUsername());
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) {
				user = new User();
				user.setUsername(rs.getString(1));
				user.setPassword(rs.getString(2));
			}
		} catch (SQLException e) {
			
			System.out.println(e.getStackTrace());
		}
		}
		return user;
	}
	
	/*public  boolean validate(String username,String password) throws SQLException {
		// TODO Auto-generated method stub
		boolean status=false;
		try(Connection con = connect();
				PreparedStatement pst = con.prepareStatement(VAL_USER_QRY)){
			pst.setString(1, username);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			
			status=rs.next();
		} catch (SQLException e) {
			
			System.out.println(e.getStackTrace());
		}
		
		return status;
	}*/
	
	public LoanInfo registerUser(LoanInfo loan) throws SQLException {
		// TODO Auto-generated method stub
		if(loan!=null) {
		try(Connection con = connect();
				PreparedStatement pst = con.prepareStatement(APP_LOAN_REGISTER_QRY)){
			
			pst.setString(1, loan.getAlphaNumericString(16));
			pst.setString(2,loan.getPurpose());
			pst.setInt(3, loan.getAmtrequest());
			pst.setString(4, loan.getDoa());
			pst.setString(5, loan.getBstructure());
			pst.setString(6, loan.getBindicator());
			pst.setString(7, loan.getTindicator());
			pst.setString(8, loan.getAddress());
			pst.setString(9,loan.getEmail());
			pst.setString(10, loan.getMobile());
			pst.setString(11, loan.getStatus());
			pst.executeUpdate();
			
		} catch (SQLException e) {
			
			System.out.println(e.getStackTrace());
		}
	    }
		return loan;
		}
		
	
	
	public LoanInfo getDisplayloanStatus(String applno) throws SQLException {
		LoanInfo status=null;
		try (Connection con = connect(); 
				PreparedStatement ps = con.prepareStatement(LOAN_DISPLAY_QRY)) {

			ps.setString(1, applno);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = new LoanInfo();
				status.setApplno(rs.getString(1));
				status.setStatus(rs.getString(2));
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}		
		return status;
	}
	
	public LoanInfo getUserApploanStatus(String applno) throws SQLException {
		LoanInfo status=null;
		try (Connection con = connect(); 
				PreparedStatement ps = con.prepareStatement(LOAN_DISPLAY_QRY)) {

			ps.setString(1, applno);

			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				status = new LoanInfo();
				status.setApplno(rs.getString(1));
				status.setPurpose(rs.getString(2));
				status.setAmtrequest(Integer.parseInt(rs.getString(3)));
				status.setDoa(rs.getString(4));
				status.setBstructure(rs.getString(5));
				status.setBindicator(rs.getString(6));
				status.setTindicator(rs.getString(7));
				status.setAddress(rs.getString(8));
				status.setEmail(rs.getString(9));
				status.setMobile(rs.getString(10));
				status.setStatus(rs.getString(11));
			}
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}		
		return status;
	}
	
	public List<LoanInfo> getAllLoan() throws SQLException{
		List<LoanInfo> loanInfos =new ArrayList<LoanInfo>();
		try (Connection con = connect();
			PreparedStatement pst = con.prepareStatement(GET_ALL_LOAN_QUERY)){
			
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				LoanInfo loanInfo = new LoanInfo();
				
				loanInfo.setApplno(rs.getString(1));
				loanInfo.setPurpose(rs.getString(2));
				loanInfo.setAmtrequest(Integer.parseInt(rs.getString(3)));
				loanInfo.setDoa(rs.getString(4));
				loanInfo.setBstructure(rs.getString(5));
				loanInfo.setBindicator(rs.getString(6));
				loanInfo.setTindicator(rs.getString(7));
				loanInfo.setAddress(rs.getString(8));
				loanInfo.setEmail(rs.getString(9));
				loanInfo.setMobile(rs.getString(10));
				loanInfo.setStatus(rs.getString(11));				
				loanInfos.add(loanInfo);
				
			}
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}	
		return loanInfos;
	}
	
	public LoanInfo save(LoanInfo loan) throws SQLException {
		if (loan != null) {
			try (Connection con = connect(); PreparedStatement pst = con.prepareStatement(APP_LOAN_EDIT_QRY)) {

				pst.setInt(1, loan.getAmtrequest());
				pst.setString(2,loan.getPurpose());
				pst.setString(3, loan.getBstructure());
				pst.setString(4, loan.getBindicator());
				pst.setString(5, loan.getTindicator());
				pst.setString(6, loan.getAddress());
				pst.setString(7,loan.getEmail());
				pst.setString(8, loan.getMobile());
				pst.setString(9, loan.getStatus());
				pst.setString(10, loan.getApplno());
				pst.executeUpdate();
				
			} catch (SQLException e) {
				System.out.println(e.getStackTrace());
		}
		}
		return loan;
	 }
	
     public ApprovedLoan insertApprovedLoan(ApprovedLoan approvedLoan) throws SQLException{
    	 if (approvedLoan != null) {
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(APP_APPROVED_ADD_QRY);
		
			pst.setString(1, approvedLoan.getApplno());
			pst.setInt(2, approvedLoan.getAmotsanctioned());
			pst.setInt(3, approvedLoan.getLoanterm());
			pst.setString(4, approvedLoan.getPsd());
			pst.setString(5, approvedLoan.getLcd());
			pst.setInt(6, approvedLoan.getEmi());
			
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}
    	 }
    	 
		return approvedLoan;
	}
	
	   public LoanInfo  updateLoanStatus(LoanInfo loanInfo) throws SQLException{
		   if (loanInfo != null) {
		try {
			Connection con = connect();
			PreparedStatement pst = con.prepareStatement(UPDATE_LOAN_STATUS_QRY);
		
			pst.setString(1, loanInfo.getStatus());
			pst.setString(2, loanInfo.getApplno());
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getStackTrace());
		}	
		   }
		   return loanInfo;
		
	}
		
	
}




