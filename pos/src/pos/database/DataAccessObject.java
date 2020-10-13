package pos.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class DataAccessObject {
	private String[] filePath = {"D:\\ICIA\\pos\\src\\pos\\database\\employeeData.txt",
			"D:\\ICIA\\pos\\src\\pos\\database\\employeeHistory.txt",
			"D:\\ICIA\\pos\\src\\pos\\database\\goodsInfo.txt",
			"D:\\ICIA\\pos\\src\\pos\\database\\goodsList.txt",
	"D:\\ICIA\\pos\\src\\pos\\database\\saleInfo.txt"};
	private FileReader fr;
	private BufferedReader br;
	private FileWriter fw;
	private BufferedWriter bw;
	private File file;

	public DataAccessObject() {

	}

	public boolean isEmployeeCode(int fileIndex, EmployeeBean eb) {
		boolean result = false;
		file = new File(filePath[fileIndex]);

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String record = null;

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				/*
				if(eb.getEmployeeCode().equals(record.substring(0, record.indexOf(",")))) {
					result = true;
					break;
				}
				 */

				String[] recordArr = record.split(",");
				if(eb.getEmployeeCode().equals(recordArr[0])) {
					result = true;
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean isAccessCode(int fileIndex, EmployeeBean eb) {
		boolean result = false;
		file = new File(filePath[fileIndex]);

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String record = null;

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				/*
				if(eb.getEmployeeCode().equals(record.substring(0, record.indexOf(",")))) {
					String temp = record.substring(record.indexOf(",")+1);
					if(eb.getAccessCode().equals(temp.substring(0, temp.indexOf(",")))) {
						result = true;
						break;
					}
				}
				 */

				String[] recordArr = record.split(",");
				if(eb.getEmployeeCode().equals(recordArr[0])) {
					if(eb.getAccessCode().equals(recordArr[1])) {
						result = true;
						break;
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public void getEmployeeData(int fileIndex, EmployeeBean eb) {
		file = new File(filePath[fileIndex]);

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String record = null;

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				/*
				if(eb.getEmployeeCode().equals(record.substring(0, record.indexOf(",")))) {
					String temp = record.substring(record.indexOf(",")+1);
					if(eb.getAccessCode().equals(temp.substring(0, temp.indexOf(",")))) {

						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeeName(temp.substring(0, temp.indexOf(",")));

						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeephone(temp.substring(0, temp.indexOf(",")));

						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeeLevel((temp.equals("Manager"))?true:false);
					}
				}
				 */

				String[] recordArr = record.split(",");
				if(eb.getEmployeeCode().equals(recordArr[0])) {
					if(eb.getAccessCode().equals(recordArr[1])) {
						eb.setEmployeeName(recordArr[2]);
						eb.setEmployeephone(recordArr[3]);
						eb.setEmployeeLevel(recordArr[4].equals("Manager")?true:false);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void setEmployeeHistory(int fileIndex, EmployeeBean eb) {
		file = new File(filePath[fileIndex]);

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			sb.append(eb.getEmployeeCode());
			sb.append(",");
			sb.append(eb.getEmployeeName());
			sb.append(",");
			sb.append(eb.getEmployeephone());
			sb.append(",");
			sb.append(eb.isEmployeeLevel()?"Manager":"Mate");
			sb.append(",");
			sb.append(eb.getAccessTime());
			sb.append(",");
			sb.append(eb.getRequest().equals("A1")?"1":"-1");

			bw.write(sb.toString() + "\n");
			bw.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean setEmployeeReg(int fileIndex, EmployeeBean eb) {
		boolean result = false;
		file = new File(filePath[fileIndex]);

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			StringBuilder sb = new StringBuilder();
			sb.append(eb.getEmployeeCode());
			sb.append(",");
			sb.append(eb.getAccessCode());
			sb.append(",");
			sb.append(eb.getEmployeeName());
			sb.append(",");
			sb.append(eb.getEmployeephone());
			sb.append(",");
			sb.append(eb.isEmployeeLevel()?"Manager":"Mate");

			bw.write(sb.toString() + "\n");
			bw.flush();

			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public ArrayList<EmployeeBean> getEmployeesData(int fileIndex) {
		file = new File(filePath[fileIndex]);
		ArrayList<EmployeeBean> employeeList = new ArrayList<EmployeeBean>();
		EmployeeBean eb;
		String record;
		String[] recordArr;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				recordArr = record.split(",");

				eb = new EmployeeBean();

				eb.setEmployeeCode(recordArr[0]);
				eb.setAccessCode(recordArr[1]);
				eb.setEmployeeName(recordArr[2]);
				eb.setEmployeephone(recordArr[3]);
				eb.setEmployeeLevel(recordArr[4].equals("Manager")?true:false);

				employeeList.add(eb);

			}

		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return employeeList;
	}

	public boolean setEmployeeMod(int fileIndex, ArrayList<EmployeeBean> employeeList) {
		boolean result = false;
		file = new File(filePath[fileIndex]);
		String record;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for(EmployeeBean eb : employeeList) {
				record = eb.getEmployeeCode() + "," + eb.getAccessCode() + "," + eb.getEmployeeName() + "," +
						eb.getEmployeephone() + "," + (eb.isEmployeeLevel()?"Manager":"Mate");
				bw.write(record + "\n");
				bw.flush();
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fw != null) {
					fw.close();
				}
				if(bw != null) {
					bw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void getGoodsInfo(int fileIndex, GoodsBean gb) {
		file = new File(filePath[fileIndex]);
		String record = null;
		String[] recordArr;

		gb.setResult(false);

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				recordArr = record.split(",");

				if(gb.getGoodsCode().equals(recordArr[0])) {
					gb.setGoodsName(recordArr[1]);
					gb.setGoodsPrice(Integer.parseInt(recordArr[2]));
					gb.setGoodsExpireDate(recordArr[3]);
					gb.setGoodsAmount(1);
					gb.setResult(true);

					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void getGoodsInfo(int fileIndex, GoodsBean gb, ArrayList<GoodsBean> goodsListBeanArrayList) {
		file = new File(filePath[fileIndex]);
		String record = null;
		String[] recordArr;

		gb.setResult(false);

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				recordArr = record.split(",");

				if(gb.getGoodsCode().equals(recordArr[0])) {
					gb.setGoodsName(recordArr[1]);
					gb.setGoodsPrice(Integer.parseInt(recordArr[2]));
					gb.setGoodsExpireDate(recordArr[3]);
					for(int i=0;i<goodsListBeanArrayList.size();i++) {
						if(gb.getGoodsCode().equals(goodsListBeanArrayList.get(i).getGoodsCode())) {
							gb.setGoodsAmount(goodsListBeanArrayList.get(i).getGoodsAmount()+1);
							break;
						}else {
							gb.setGoodsAmount(1);
						}
					}
					gb.setResult(true);
					break;
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setSaleInfo(int fileIndex, GoodsBean gb, ArrayList<GoodsBean> goodsListBeanArrayList) {
		file = new File(filePath[fileIndex]);
		String record = null;
		gb.setResult(false);

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);

			for(GoodsBean goodsListBean : goodsListBeanArrayList) {
				
				record = goodsListBean.getSaleDate() + "," + goodsListBean.getGoodsCode() + "," + goodsListBean.getGoodsName() + "," + goodsListBean.getGoodsAmount() + "," + goodsListBean.getGoodsPrice() + "," + 
						goodsListBean.getGoodsExpireDate();
				
				bw.write(record + "\n");
				bw.flush();
			}
			gb.setResult(true);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(bw != null) {
					bw.close();
				}
				if(fw != null) {
					fw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<GoodsBean> getSaleInfo(int fileIndex) {
		file = new File(filePath[fileIndex]);
		ArrayList<GoodsBean> saleInfo = new ArrayList<GoodsBean>();
		GoodsBean gb;
		String record;
		String[] recordArr;

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				gb = new GoodsBean();

				recordArr = record.split(",");
				gb.setSaleDate(recordArr[0]);
				gb.setGoodsCode(recordArr[1]);
				gb.setGoodsName(recordArr[2]);
				gb.setGoodsAmount(Integer.parseInt(recordArr[3]));
				gb.setGoodsPrice(Integer.parseInt(recordArr[4]));
				gb.setGoodsExpireDate(recordArr[5]);

				saleInfo.add(gb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return saleInfo;
	}

	public ArrayList<GoodsBean> getRefundList(int fileIndex, GoodsBean gb) {
		file = new File(filePath[fileIndex]);
		ArrayList<GoodsBean> refundList = new ArrayList<GoodsBean>();
		GoodsBean recordBean;
		String[] recordArr;
		String record;
		
		gb.setResult(false);

		try {
			fr = new FileReader(file);
			br =  new BufferedReader(fr);
			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				recordArr = record.split(",");
				if(recordArr[0].equals(gb.getSaleDate())) {
					recordBean = new GoodsBean();
					gb.setResult(true);
					recordBean.setSaleDate(recordArr[0]);
					recordBean.setGoodsCode(recordArr[1]);
					recordBean.setGoodsName(recordArr[2]);
					recordBean.setGoodsAmount(Integer.parseInt(recordArr[3]));
					recordBean.setGoodsPrice(Integer.parseInt(recordArr[4]));
					recordBean.setGoodsExpireDate(recordArr[5]);
					refundList.add(recordBean);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return refundList;
	}

	public void setRefundList(int fileIndex, ArrayList<GoodsBean> saleInfo) {
		file = new File(filePath[fileIndex]);
		String record;

		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);

			for(GoodsBean gb : saleInfo) {
				record = gb.getSaleDate() + "," + gb.getGoodsCode() + "," + gb.getGoodsName() + "," + gb.getGoodsAmount() + "," +
						gb.getGoodsPrice() + "," + gb.getGoodsExpireDate();
				bw.write(record + "\n");
				bw.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fw != null) {
					fw.close();
				}
				if(bw != null) {
					bw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void setGoodsInfo(int fileIndex, GoodsBean gb) {
		file = new File(filePath[fileIndex]);
		StringBuilder record;

		try {
			fw = new FileWriter(file, true);
			bw = new BufferedWriter(fw);
			record = new StringBuilder();

			record.append(gb.getGoodsCode());
			record.append(",");
			record.append(gb.getGoodsName());
			record.append(",");
			record.append(gb.getGoodsPrice());
			record.append(",");
			record.append(gb.getGoodsExpireDate());
			record.append(",");
			record.append(gb.getGoodsStock());
			record.append(",");
			record.append(gb.getGoodsSafetyStock());

			bw.write(record.toString() + "\n");
			bw.flush();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fw != null) {
					fw.close();
				}
				if(bw != null) {
					bw.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<GoodsBean> getDailySaleInfo(int fileIndex, GoodsBean gb) {
		file = new File(filePath[fileIndex]);
		String record;
		String[] recordArr;
		GoodsBean recordBean;
		ArrayList<GoodsBean> recordList = new ArrayList<GoodsBean>();

		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				recordArr = record.split(",");

				if(gb.getSaleDate().equals(recordArr[0].substring(0, gb.getSaleDate().length()))) {
					recordBean = new GoodsBean();

					recordBean.setSaleDate(recordArr[0]);
					recordBean.setGoodsCode(recordArr[1]);
					recordBean.setGoodsName(recordArr[2]);
					recordBean.setGoodsAmount(Integer.parseInt(recordArr[3]));
					recordBean.setGoodsPrice(Integer.parseInt(recordArr[4]));
					recordBean.setGoodsExpireDate(recordArr[5]);

					recordList.add(recordBean);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fr != null) {
					fr.close();
				}
				if(br != null) {
					br.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return recordList;
	}

}
