package pos.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataAccessObject {
	
	Connection connect;

	public DataAccessObject() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("드라이버 접속 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 접속 실패");
			e.printStackTrace();
		}
	}
	
	public void setAutoTransAction(boolean trans) {
		try {
			if(!this.connect.isClosed()) {
				this.connect.setAutoCommit(trans);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void endAutoTransAction(boolean trans) {
		try {
			if(!this.connect.isClosed()) {
				if(trans) {
					this.connect.commit();
				}else {
					this.connect.rollback();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(!this.connect.isClosed()) {
					this.connect.isClosed();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isEmployeeCode(EmployeeBean eb) {
		boolean result = false;
		
		try {
			this.connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			System.out.println("서버 접속 성공");
			
			String sql = "SELECT COUNT(*) AS \"ISEMCODE\" \n" + 
						"FROM \"EM\" \n" + 
						"WHERE EM_STCODE = ? AND EM_CODE = ?";
			
			PreparedStatement qeury = this.connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getStoreCode());
			qeury.setNString(2, eb.getEmployeeCode());
			

			ResultSet qeuryResult = qeury.executeQuery();
			
			while(qeuryResult.next()) {
				result = (qeuryResult.getInt("ISEMCODE") == 1)?true:false;
			}
			
			qeury.isClosed();
			qeuryResult.isClosed();
			
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}

		return result;
	}

	public boolean isAccessCode(EmployeeBean eb) {
		boolean result = false;
		
		try {
			String sql = "SELECT COUNT(*) AS \"ISACCESS\" \n" + 
						"FROM \"EM\" \n" + 
						"WHERE EM_STCODE = ? AND EM_CODE = ? AND EM_PWD = ?";
			
			PreparedStatement qeury = this.connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getStoreCode());
			qeury.setNString(2, eb.getEmployeeCode());
			qeury.setNString(3, eb.getAccessCode());
			

			ResultSet qeuryResult = qeury.executeQuery();
			
			while(qeuryResult.next()) {
				result = (qeuryResult.getInt("ISACCESS") == 1)?true:false;
			}
			
			qeury.isClosed();
			qeuryResult.isClosed();
			
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
		
		return result;
	}

	public void getEmployeeData(EmployeeBean eb) {
		
		try {
			String sql = "SELECT ST.ST_NAME AS \"STNAME\", \n" + 
					"        \"EM\".EM_NAME AS \"EMNAME\", \n" + 
					"        \"EM\".EM_LEVEL AS \"EMLEVEL\", \n" + 
					"        TO_CHAR(MAX(HI.HI_ACCESSDATE), 'YYYYMMDDHH24MISS') AS \"HIACCESSDATE\", \n" + 
					"        HI.HI_EMSTCODE AS \"STCODE\", \n" + 
					"        HI.HI_EMCODE AS \"EMCODE\" \n" + 
					"FROM HI INNER JOIN \"EM\" ON HI.HI_EMCODE = \"EM\".EM_CODE AND HI.HI_EMSTCODE = \"EM\".EM_STCODE \n" + 
					"        INNER JOIN ST ON HI.HI_EMSTCODE = ST.ST_CODE \n" + 
					"WHERE HI.HI_STATE = 1 \n" + 
					"        AND HI.HI_EMSTCODE = ? \n" + 
					"        AND HI.HI_EMCODE = ? \n" + 
					"GROUP BY ST.ST_NAME, \"EM\".EM_NAME, \"EM\".EM_LEVEL, HI.HI_EMSTCODE, HI.HI_EMCODE";
			
			PreparedStatement qeury = this.connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getStoreCode());
			qeury.setNString(2, eb.getEmployeeCode());
			
			ResultSet qeuryResult;
			
			qeuryResult = qeury.executeQuery();
			
			while(qeuryResult.next()) {
				eb.setStoreCode(qeuryResult.getNString("STCODE"));
				eb.setStoreName(qeuryResult.getNString("STNAME"));
				eb.setEmployeeCode(qeuryResult.getNString("EMCODE"));
				eb.setEmployeeName(qeuryResult.getNString("EMNAME"));
				eb.setEmployeeLevel((qeuryResult.getNString("EMLEVEL").equals("M")? true : false));
				eb.setAccessTime(qeuryResult.getNString("HIACCESSDATE"));
			}
			
			qeury.isClosed();
			qeuryResult.isClosed();
			
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}

	}

	public boolean setEmployeeHistory(EmployeeBean eb) {
		boolean result = false;
		try {
			String sql = "INSERT INTO HI(HI_EMSTCODE, HI_EMCODE, HI_ACCESSDATE, HI_STATE) \n" + 
					"VALUES(?, ?, DEFAULT, ?)";
			if(eb.getAccessState() == -1) {
				connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
				this.setAutoTransAction(result);
			}
			PreparedStatement qeury = this.connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getStoreCode());
			qeury.setNString(2, eb.getEmployeeCode());
			qeury.setInt(3, eb.getAccessState());
			
			if(qeury.executeUpdate() > 0) {
				result = true;
				if(eb.getAccessState() == -1) {
					this.endAutoTransAction(result);
				}
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			qeury.isClosed();
			
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}

		return result;
	}

	public void setEmployeeReg(EmployeeBean eb) {
		boolean trans = false;
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			this.setAutoTransAction(trans);
			
			String sql = "INSERT INTO \"EM\"(EM_STCODE, EM_CODE, EM_PWD, EM_NAME, EM_LEVEL) \n" + 
					"VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement qeury = connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getStoreCode());
			qeury.setNString(2, eb.getEmployeeCode());
			qeury.setNString(3, eb.getAccessCode());
			qeury.setNString(4, eb.getEmployeeName());
			qeury.setNString(5, eb.isEmployeeLevel()?"M":"A");
			
			trans = (qeury.executeUpdate()==1)?true:false;
			
			this.endAutoTransAction(trans);
			
			qeury.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void setEmployeeMod(EmployeeBean eb) {
		boolean trans = false;
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			this.setAutoTransAction(trans);
			
			String sql = "UPDATE \"EM\" SET EM_PWD = ? WHERE EM_STCODE = ? AND EM_CODE = ?";
			
			PreparedStatement qeury = connect.prepareStatement(sql);
			
			qeury.setNString(1, eb.getAccessCode());
			qeury.setNString(2, eb.getStoreCode());
			qeury.setNString(3, eb.getEmployeeCode());
			
			trans = (qeury.executeUpdate() == 1)?true:false;
			
			this.endAutoTransAction(trans);
			
			qeury.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getGoodsInfo(int fileIndex, GoodsBean gb) {
		
	}

	public void getGoodsInfo(int fileIndex, GoodsBean gb, ArrayList<GoodsBean> goodsListBeanArrayList) {
		
	}

	public void setSaleInfo(int fileIndex, GoodsBean gb, ArrayList<GoodsBean> goodsListBeanArrayList) {
		
	}

	public ArrayList<GoodsBean> getSaleInfo(int fileIndex) {
		ArrayList<GoodsBean> saleInfo = new ArrayList<GoodsBean>();
		
		return saleInfo;
	}

	public ArrayList<GoodsBean> getRefundList(int fileIndex, GoodsBean gb) {
		ArrayList<GoodsBean> refundList = new ArrayList<GoodsBean>();
		
		return refundList;
	}

	public void setRefundList(int fileIndex, ArrayList<GoodsBean> saleInfo) {
		
	}

	public void setGoodsInfo(int fileIndex, GoodsBean gb) {
		
	}

	public ArrayList<GoodsBean> getDailySaleInfo(GoodsBean gb) {
		ArrayList<GoodsBean> salesDataList = new ArrayList<GoodsBean>();
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			String sql = "SELECT GOCODE,\r\n" + 
					"        GONAME,\r\n" + 
					"        GOPRICE,\r\n" + 
					"        SUM(OTQTY) AS \"OTQTY\"\r\n" + 
					"FROM DAILYSALES\r\n" + 
					"WHERE ODCODE LIKE ? || '%'\r\n" + 
					"GROUP BY GOCODE, GONAME, GOPRICE";
			
			PreparedStatement qeury = connect.prepareStatement(sql);
			
			qeury.setNString(1, gb.getSaleDate());
			
			ResultSet qeuryResult = qeury.executeQuery();
			
			while(qeuryResult.next()) {
				GoodsBean salesData = new GoodsBean();
				salesData.setSaleDate(gb.getSaleDate());
				salesData.setGoodsCode(qeuryResult.getNString("GOCODE"));
				salesData.setGoodsName(qeuryResult.getNString("GONAME"));
				salesData.setGoodsPrice(qeuryResult.getInt("GOPRICE"));
				salesData.setGoodsAmount(qeuryResult.getInt("OTQTY"));
				
				salesDataList.add(salesData);
			}
			
			connect.isClosed();
			qeury.isClosed();
			qeuryResult.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salesDataList;
	}
	
	public ArrayList<GoodsBean> getBestDailySaleInfo(GoodsBean gb) {
		ArrayList<GoodsBean> salesDataList = new ArrayList<GoodsBean>();
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			String sql = "SELECT GOCODE, \n" + 
					"        GONAME, \n" + 
					"        GOPRICE, \n" + 
					"        OTQTY \n" + 
					"FROM (SELECT SUBSTR(ODCODE, 1 ,6) AS \"ODCODE\", \n" + 
					"            GOCODE AS \"GOCODE\", \n" + 
					"            GONAME AS \"GONAME\", \n" + 
					"            GOPRICE AS \"GOPRICE\", \n" + 
					"            SUM(OTQTY) AS \"OTQTY\" \n" + 
					"        FROM DAILYSALES  \n" + 
					"        WHERE ODCODE LIKE ? || '%' \n" + 
					"        GROUP BY SUBSTR(ODCODE, 1 ,6), GOCODE, GONAME, GOPRICE) \n" + 
					"WHERE (ODCODE, OTQTY) IN (SELECT ODCODE, \n" + 
					"                                    MAX(OTQTY) \n" + 
					"                            FROM (SELECT SUBSTR(ODCODE, 1 ,6) AS \"ODCODE\", \n" + 
					"                                            SUM(OTQTY) AS \"OTQTY\" \n" + 
					"                                    FROM DAILYSALES \n" + 
					"                                    WHERE ODCODE LIKE ? || '%' \n" + 
					"                                    GROUP BY SUBSTR(ODCODE, 1 ,6), GOPRICE) \n" + 
					"                            GROUP BY ODCODE)";
			
			PreparedStatement qeury = connect.prepareStatement(sql);
			
			qeury.setNString(1, gb.getSaleDate());
			qeury.setNString(2, gb.getSaleDate());
			
			ResultSet qeuryResult = qeury.executeQuery();
			
			while(qeuryResult.next()) {
				GoodsBean salesData = new GoodsBean();
				salesData.setSaleDate(gb.getSaleDate());
				salesData.setGoodsCode(qeuryResult.getNString("GOCODE"));
				salesData.setGoodsName(qeuryResult.getNString("GONAME"));
				salesData.setGoodsPrice(qeuryResult.getInt("GOPRICE"));
				salesData.setGoodsAmount(qeuryResult.getInt("OTQTY"));
				
				salesDataList.add(salesData);
			}
			
			connect.isClosed();
			qeury.isClosed();
			qeuryResult.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return salesDataList;
	}

}
