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
					this.connect.close();
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
			
			PreparedStatement query = this.connect.prepareStatement(sql);
			
			query.setNString(1, eb.getStoreCode());
			query.setNString(2, eb.getEmployeeCode());
			

			ResultSet queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				result = (queryResult.getInt("ISEMCODE") == 1)?true:false;
			}
			
			query.close();
			queryResult.close();
			
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
			
			PreparedStatement query = this.connect.prepareStatement(sql);
			
			query.setNString(1, eb.getStoreCode());
			query.setNString(2, eb.getEmployeeCode());
			query.setNString(3, eb.getAccessCode());
			

			ResultSet queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				result = (queryResult.getInt("ISACCESS") == 1)?true:false;
			}
			
			query.close();
			queryResult.close();
			
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
			
			PreparedStatement query = this.connect.prepareStatement(sql);
			
			query.setNString(1, eb.getStoreCode());
			query.setNString(2, eb.getEmployeeCode());
			
			ResultSet queryResult;
			
			queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				eb.setStoreCode(queryResult.getNString("STCODE"));
				eb.setStoreName(queryResult.getNString("STNAME"));
				eb.setEmployeeCode(queryResult.getNString("EMCODE"));
				eb.setEmployeeName(queryResult.getNString("EMNAME"));
				eb.setEmployeeLevel((queryResult.getNString("EMLEVEL").equals("M")? true : false));
				eb.setAccessTime(queryResult.getNString("HIACCESSDATE"));
			}
			
			query.close();
			queryResult.close();
			
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
			PreparedStatement query = this.connect.prepareStatement(sql);
			
			query.setNString(1, eb.getStoreCode());
			query.setNString(2, eb.getEmployeeCode());
			query.setInt(3, eb.getAccessState());
			
			if(query.executeUpdate() > 0) {
				result = true;
				if(eb.getAccessState() == -1) {
					this.endAutoTransAction(result);
				}
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			query.close();
			
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
			
			System.out.println("서버 접속 성공");
			this.setAutoTransAction(trans);
			
			String sql = "INSERT INTO \"EM\"(EM_STCODE, EM_CODE, EM_PWD, EM_NAME, EM_LEVEL) \n" + 
					"VALUES(?, ?, ?, ?, ?)";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, eb.getStoreCode());
			query.setNString(2, eb.getEmployeeCode());
			query.setNString(3, eb.getAccessCode());
			query.setNString(4, eb.getEmployeeName());
			query.setNString(5, eb.isEmployeeLevel()?"M":"A");
			
			trans = (query.executeUpdate()==1)?true:false;
			
			if(trans) {
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			this.endAutoTransAction(trans);
			
			query.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
		
	}

	public void setEmployeeMod(EmployeeBean eb) {
		boolean trans = false;
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			System.out.println("서버 접속 성공");
			this.setAutoTransAction(trans);
			
			String sql = "UPDATE \"EM\" SET EM_PWD = ? WHERE EM_STCODE = ? AND EM_CODE = ?";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, eb.getAccessCode());
			query.setNString(2, eb.getStoreCode());
			query.setNString(3, eb.getEmployeeCode());
			
			trans = (query.executeUpdate() == 1)?true:false;
			
			if(trans) {
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			this.endAutoTransAction(trans);
			
			query.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
	}

	public void getGoodsInfo(GoodsBean gb) {
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			System.out.println("서버 접속 성공");
			
			String sql = "SELECT GOCODE, \n" + 
					"        GONAME, \n" + 
					"        GOPRICE, \n" + 
					"        TO_CHAR(MAX(EXPIRE), 'YYYYMMDDHH24MISS') AS \"EXPIRE\" \n" + 
					"FROM (SELECT SC.SC_GOCODE AS \"GOCODE\", \n" + 
					"                GO.GO_NAME AS \"GONAME\", \n" + 
					"                GO.GO_PRICE AS \"GOPRICE\", \n" + 
					"                SUM(SC.SC_STOCKS) AS \"STOCK\", \n" + 
					"                SC.SC_EXPIRE AS \"EXPIRE\" \n" + 
					"        FROM SC INNER JOIN GO ON SC.SC_GOCODE = GO.GO_CODE \n" + 
					"        GROUP BY SC.SC_GOCODE, GO.GO_NAME, GO.GO_PRICE, SC.SC_EXPIRE) \n" + 
					"WHERE GOCODE = ? AND STOCK > 0 \n" + 
					"GROUP BY GOCODE, GONAME, GOPRICE";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getGoodsCode());
			
			ResultSet queryResult;
			queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				gb.setResult(true);
				gb.setGoodsCode(queryResult.getNString("GOCODE"));
				gb.setGoodsName(queryResult.getNString("GONAME"));
				gb.setGoodsPrice(queryResult.getInt("GOPRICE"));
				gb.setGoodsExpireDate(queryResult.getNString("EXPIRE"));
				gb.setGoodsAmount(1);
			}
			
			query.close();
			queryResult.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
	}

	public void getGoodsInfo(int fileIndex, GoodsBean gb, ArrayList<GoodsBean> goodsListBeanArrayList) {
		
	}
	
	public void setOrderInfo(GoodsBean gb) {
		boolean trans = false;
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "SON", "0000");
			
			System.out.println("서버 접속 성공");
			this.setAutoTransAction(trans);
			
			String sql = " INSERT INTO OD(OD_CODE, OD_EMSTCODE, OD_EMCODE, OD_CMCODE, OD_STATE)\n" + 
					" VALUES(DEFAULT, ?, ?, ?, ?)";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getStoreCode());
			query.setNString(2, gb.getEmployeeCode());
			query.setNString(3, gb.getCustomerCode());
			query.setNString(4, gb.getState());
			
			trans = (query.executeUpdate() == 1)?true:false;
			
			if(trans) {
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			this.endAutoTransAction(trans);
			
			query.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
	}
	
	public void getOrderCode(GoodsBean gb) {
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			System.out.println("서버 접속 성공");
			
			String sql = "SELECT TO_CHAR(MAX(OD_CODE), 'YYYYMMDDHH24MISS') AS \"ODCODE\" \n" + 
					"FROM OD \n" + 
					"WHERE OD_EMSTCODE = ? AND OD_EMCODE = ? AND OD_CMCODE = ? AND OD_STATE = ? \n";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getStoreCode());
			query.setNString(2, gb.getEmployeeCode());
			query.setNString(3, gb.getCustomerCode());
			query.setNString(4, gb.getState());
			
			ResultSet queryResult;
			
			queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				gb.setSaleDate(queryResult.getNString("ODCODE"));
			}
			
			query.close();
			queryResult.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
	}

	public void setSaleInfo(GoodsBean gb, GoodsBean goodsList) {
		boolean trans = false;
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "SON", "0000");
			
			System.out.println("서버 접속 성공");
			this.setAutoTransAction(trans);
			
			String sql = " INSERT INTO OT(OT_ODCODE, OT_GOCODE, OT_QTY, OT_STATE)\r\n" + 
					" VALUES(TO_DATE(?, 'YYYYMMDDHH24MISS'), ?, ?, ?)";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getSaleDate());
			query.setNString(2, goodsList.getGoodsCode());
			query.setInt(3, goodsList.getGoodsAmount());
			query.setNString(4, goodsList.getState());
			
			trans = (query.executeUpdate() == 1)?true:false;
			
			if(trans) {
				System.out.println("기록 성공");
			}else {
				System.out.println("기록 실패");
			}
			
			this.endAutoTransAction(trans);
			
			query.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}
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
			
			System.out.println("서버 접속 성공");
			
			String sql = "SELECT GOCODE, \n" + 
					"        GONAME, \n" + 
					"        GOPRICE, \n" + 
					"        SUM(OTQTY) AS \"OTQTY\" \n" + 
					"FROM DAILYSALES \n" + 
					"WHERE ODCODE LIKE ? || '%' AND STCODE = ?\n" + 
					"GROUP BY GOCODE, GONAME, GOPRICE";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getSaleDate());
			query.setNString(2, gb.getStoreCode());
			
			ResultSet queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				GoodsBean salesData = new GoodsBean();
				salesData.setSaleDate(gb.getSaleDate());
				salesData.setGoodsCode(queryResult.getNString("GOCODE"));
				salesData.setGoodsName(queryResult.getNString("GONAME"));
				salesData.setGoodsPrice(queryResult.getInt("GOPRICE"));
				salesData.setGoodsAmount(queryResult.getInt("OTQTY"));
				
				salesDataList.add(salesData);
			}
			
			connect.close();
			query.close();
			queryResult.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}

		return salesDataList;
	}
	
	public ArrayList<GoodsBean> getBestDailySaleInfo(GoodsBean gb) {
		ArrayList<GoodsBean> salesDataList = new ArrayList<GoodsBean>();
		
		try {
			connect = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.43:1521:xe", "FANA", "0000");
			
			System.out.println("서버 접속 성공");
			
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
					"        WHERE ODCODE LIKE ? || '%' AND STCODE = ? \n" + 
					"        GROUP BY SUBSTR(ODCODE, 1 ,6), GOCODE, GONAME, GOPRICE) \n" + 
					"WHERE (ODCODE, OTQTY) IN (SELECT ODCODE, \n" + 
					"                                    MAX(OTQTY) \n" + 
					"                            FROM (SELECT SUBSTR(ODCODE, 1 ,6) AS \"ODCODE\", \n" + 
					"                                            SUM(OTQTY) AS \"OTQTY\" \n" + 
					"                                    FROM DAILYSALES \n" + 
					"                                    WHERE ODCODE LIKE ? || '%' AND STCODE = ? \n" + 
					"                                    GROUP BY SUBSTR(ODCODE, 1 ,6), GOPRICE) \n" + 
					"                            GROUP BY ODCODE)";
			
			PreparedStatement query = connect.prepareStatement(sql);
			
			query.setNString(1, gb.getSaleDate());
			query.setNString(2, gb.getStoreCode());
			query.setNString(3, gb.getSaleDate());
			query.setNString(4, gb.getStoreCode());
			
			ResultSet queryResult = query.executeQuery();
			
			while(queryResult.next()) {
				GoodsBean salesData = new GoodsBean();
				salesData.setSaleDate(gb.getSaleDate());
				salesData.setGoodsCode(queryResult.getNString("GOCODE"));
				salesData.setGoodsName(queryResult.getNString("GONAME"));
				salesData.setGoodsPrice(queryResult.getInt("GOPRICE"));
				salesData.setGoodsAmount(queryResult.getInt("OTQTY"));
				
				salesDataList.add(salesData);
			}
			
			connect.close();
			query.close();
			queryResult.close();
		} catch (SQLException e) {
			System.out.println("서버 접속 실패");
			e.printStackTrace();
		}

		return salesDataList;
	}

}
