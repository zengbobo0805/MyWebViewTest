package com.example.mywebviewtest;
/*
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.Executor;
// 要打包的项目根目录
// 保存打包apk的根目录
// 这里的文件名必须是准确的项目名！
// 重命名的项目名称前缀(地图项目不用改)
// 需要修改manifest文件的地方(占位符)
// 先修改manifest文件:读取临时文件中的@market@修改为市场标识,然后写入manifest.xml中
// 执行打包命令
// 打完包后执行重命名加拷贝操作
// bin目录下签名的apk文件
// Set the base directory. If none is given, "." is used.

public class AntTest implements doExecuteBat {
	private static Project project = null;
	private final static String projectBasePath = "F:\\android_work_space\\TestAntPackge";// 要打包的项目根目录
	private final static String copyApkPath = "F:\\android_work_space";// 保存打包apk的根目录
	private final static String signApk = "TestAntPackge-release.apk";// 这里的文件名必须是准确的项目名！
	private final static String reNameApk = "TestAntPackge_";// 重命名的项目名称前缀(地图项目不用改)
	private final static String placeHolder = "@market@";// 需要修改manifest文件的地方(占位符)

	public static void main(String args[]) {

		long startTime = 0L;
		long endTime = 0L;
		long totalTime = 0L;
		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
		try {
			System.out.println("---------ant批量自动化打包开始----------");
			startTime = System.currentTimeMillis();
			date.setTimeInMillis(startTime);
			System.out.println("开始时间为:" + sdf.format(date.getTime()));
			BufferedReader br = new BufferedReader(new FileReader(
					"F:\\android_work_space\\myanttest\\market.txt"));
			String flag = null;
			while ((flag = br.readLine()) != null) {
				System.out.println("flag=" + flag);
				// 先修改manifest文件:读取临时文件中的@market@修改为市场标识,然后写入manifest.xml中
				String tempFilePath = projectBasePath + File.separator
						+ "AndroidManifest.xml.temp";
				String filePath = projectBasePath + File.separator
						+ "AndroidManifest.xml";
				StringUtil
						.write(filePath, StringUtil.read(tempFilePath,
								flag.trim(), placeHolder));
				// 执行打包命令
				doExecuteBat();
				// 打完包后执行重命名加拷贝操作
				File file = new File(projectBasePath + File.separator + "bin"
						+ File.separator + signApk);// bin目录下签名的apk文件
				File renameFile = new File(copyApkPath + File.separator
						+ reNameApk + flag);
				if (file.isFile()) {
					StringUtil.copy(file, renameFile);
				}
			}
			System.out.println("---------ant批量自动化打包结束----------");
			endTime = System.currentTimeMillis();
			date.setTimeInMillis(endTime);
			System.out.println("结束时间为:" + sdf.format(date.getTime()));
			totalTime = endTime - startTime;
			System.out.println("耗费时间为:" + StringUtil.getBeapartDate(totalTime));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("---------ant批量自动化打包中发生异常----------");
			endTime = System.currentTimeMillis();
			date.setTimeInMillis(endTime);
			System.out.println("发生异常时间为:" + sdf.format(date.getTime()));
			totalTime = endTime - startTime;
			System.out.println("耗费时间为:" + StringUtil.getBeapartDate(totalTime));
		}
	}

	public void init(String _buildFile, String _baseDir) throws Exception {
		project = new Project();
		project.init();
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		project.addBuildListener(consoleLogger);
		// Set the base directory. If none is given, "." is used.
		if (_baseDir == null)
			_baseDir = new String(".");
		project.setBasedir(_baseDir);
		if (_buildFile == null)
			_buildFile = new String(projectBasePath + File.separator
					+ "build.xml");
		ProjectHelper.configureProject(project, new File(_buildFile));
	}

	public static void doExecuteBat() throws Exception {
		AntTest mytest = new AntTest();
		mytest.init(projectBasePath + File.separator + "build.xml",
				projectBasePath);
		String targetArr[] = { "clean", "release" };
		Executor executor = project.getExecutor();
		executor.executeTargets(project, targetArr);
	}
}
*/

public class AntTest{
	public static String objectToString(){
		BaseModel model =new BaseModel();
		model.setDateTime(123456789);
		model.setFlag(true);
		model.setName("zengbobo");
		model.setNumber(100);
		model.setPrice(999.9);
		String buf = StringUtil.toJson(model);
		System.out.println("AntTest objectToString buf:"+buf);
		return buf;
	}
	
	public static BaseModel stringToObject(String buf){
		System.out.println("AntTest stringToObject buf:"+buf);
		BaseModel model =StringUtil.parseJson(buf, BaseModel.class);
		System.out.println("AntTest stringToObject BaseModel:"+model.getDateTime()+" "+model.getName()+"  " +model );
		return model;
	}
}