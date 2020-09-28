package pos.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataAccessObject {
	private String[] filePath = {"D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\employeeData.txt",
			"D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\employeeHistory.txt",
			"D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\goodsInfo.txt",
			"D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\goodsList.txt",
			"D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\saleInfo.txt"};
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
			Date date;
			SimpleDateFormat dateFormat;

			while(true) {
				record = br.readLine();
				if(record == null) {
					break;
				}
				/*
				if(eb.getEmployeeCode().equals(record.substring(0, record.indexOf(",")))) {
					String temp = record.substring(record.indexOf(",")+1);
					if(eb.getAccessCode().equals(temp.substring(0, temp.indexOf(",")))) {
						date = new Date(System.currentTimeMillis());
						dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
						
						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeeName(temp.substring(0, temp.indexOf(",")));
						
						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeephone(temp.substring(0, temp.indexOf(",")));
						
						temp = temp.substring(temp.indexOf(",")+1);
						eb.setEmployeeLevel((temp.equals("Manager"))?true:false);
						
						eb.setAccessTime(dateFormat.format(date));
					}
				}
				*/
				
				String[] recordArr = record.split(",");
				if(eb.getEmployeeCode().equals(recordArr[0])) {
					if(eb.getAccessCode().equals(recordArr[1])) {
						date = new Date(System.currentTimeMillis());
						dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
						eb.setEmployeeName(recordArr[2]);
						eb.setEmployeephone(recordArr[3]);
						eb.setEmployeeLevel(recordArr[4].equals("Manager")?true:false);
						eb.setAccessTime(dateFormat.format(date));
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
			
			bw.newLine();
			bw.write(sb.toString());
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

}
