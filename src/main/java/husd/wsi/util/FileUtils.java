package husd.wsi.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/***
 * 
 * 文件工具 类，所有的异常都抛出到外层处理
 * 
 * 
 * 
 */

public class FileUtils {

	public static void writeToFile(String content,String filePath, String fileName) throws IOException {
		Writer writer=null;
		try{
			writer = getWriter(filePath, fileName);
			writer.write(content);
		}finally{
			if(writer!=null)
			writer.close();
		}
	}
	
	public static void appendToFile(String content,String filePath, String fileName) throws IOException {
		Writer writer=null;
		try{
			writer = getWriter(filePath, fileName);
			writer.append(content);
		}finally{
			if(writer!=null)
			writer.close();
		}
	}
	
	private static Writer getWriter(String filePath,String fileName) throws IOException{
		filePath = filePath.endsWith(File.separator) ? filePath : filePath + File.separator;
		File file = new File(filePath+fileName);
		if(!file.exists()){
			file.createNewFile();
		}
		return new FileWriter(file);
	}
}
