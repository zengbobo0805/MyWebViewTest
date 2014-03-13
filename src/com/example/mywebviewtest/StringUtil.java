package com.example.mywebviewtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class StringUtil {
	public static void copy(File file, File renameFile) throws IOException {
		if (!renameFile.exists()) {
			renameFile.mkdir();
		}
		if (!renameFile.exists()) {// 路径判断，是路径还是单个的文件
			File[] cf = file.listFiles();
			for (File fn : cf) {
				if (fn.isFile()) {
					FileInputStream fis = new FileInputStream(fn);
					FileOutputStream fos = new FileOutputStream(renameFile
							+ "\\" + fn.getName());
					byte[] b = new byte[1024];
					int i = fis.read(b);
					while (i != -1) {
						fos.write(b, 0, i);
						i = fis.read(b);
					}
					fis.close();
					fos.close();
				} else {
					File fb = new File(renameFile + "\\" + fn.getName());
					fb.mkdir();
					if (fn.listFiles() != null) {// 如果有子目录递归复制子目录！
						copy(fn, fb);
					}
				}
			}
		} else {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(renameFile + "\\"
					+ file.getName());
			byte[] b = new byte[1024];
			int i = fis.read(b);
			while (i != -1) {
				fos.write(b, 0, i);
				i = fis.read(b);
			}
			fis.close();
			fos.close();
		}
	}

	/**
	 * 根据所秒数,计算相差的时间并以**时**分**秒返回
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static String getBeapartDate(long m) {
		m = m / 1000;
		String beapartdate = "";
		int nDay = (int) m / (24 * 60 * 60);
		int nHour = (int) (m - nDay * 24 * 60 * 60) / (60 * 60);
		int nMinute = (int) (m - nDay * 24 * 60 * 60 - nHour * 60 * 60) / 60;
		int nSecond = (int) m - nDay * 24 * 60 * 60 - nHour * 60 * 60 - nMinute
				* 60;
		beapartdate = nDay + "天" + nHour + "小时" + nMinute + "分" + nSecond + "秒";
		return beapartdate;
	}

	/**
	 * 将内容回写到文件中
	 * 
	 * @param filePath
	 * @param content
	 */
	public static void write(String filePath, String content) {
		BufferedWriter bw = null;
		try {
			// 根据文件路径创建缓冲输出流
			bw = new BufferedWriter(new FileWriter(filePath));
			// 将内容写入文件中
			bw.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					bw = null;
				}
			}
		}
	}

	public static String read(String filePath, String replaceStr,
			String placeHolder) {
		BufferedReader br = null;
		String line = null;
		StringBuffer buf = new StringBuffer();
		try {
			// 根据文件路径创建缓冲输入流
			br = new BufferedReader(new FileReader(filePath));
			// 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中
			while ((line = br.readLine()) != null) {
				// 此处根据实际需要修改某些行的内容
				if (line.contains(placeHolder)) {
					line = line.replace(placeHolder, replaceStr);
					buf.append(line);
				} else {
					buf.append(line);
				}
				buf.append(System.getProperty("line.separator"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 关闭流
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
				}
			}
		}
		return buf.toString();
	}
	

	private static Gson gson = new Gson();

	@SuppressWarnings("hiding")
	public static <T> T parseJson(String response, Class<T> clazz) {
		try {
			return gson.fromJson(response, clazz);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toJson(Object object) {
		try {
			return gson.toJson(object);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}