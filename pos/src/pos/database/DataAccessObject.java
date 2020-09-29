package pos.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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

			bw.write("\n" + sb.toString());
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

	public void setEmployeeReg(int fileIndex, EmployeeBean eb) {
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

			bw.write("\n" + sb.toString());
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

	public void getSaleInfo(int fileIndex, GoodsBean gb) {
		file = new File(filePath[fileIndex]);
		String record = null;
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
				if(gb.getGoodsCode().equals(recordArr[0])) {
					gb.setGoodsName(recordArr[1]);
					gb.setGoodsPrice(recordArr[2]);
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

}
