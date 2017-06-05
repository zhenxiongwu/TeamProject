package proguard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;

public class Proguard {
	
	public static boolean EncryptOrDecrypt(String sourceFileName, String targetFileName,String password){
		File inputFile = new File(sourceFileName);
		if(!inputFile.exists()||password==null||password.equals("")){
			return false;
		}
		
/*
		String inputFileName=inputFile.getName();
		Logger logger = Logger.getLogger("zhenxiongwu");
		logger.info(inputFileName);
		String outputFileName = inputFileName.substring(0,inputFileName.indexOf("."))+".mf";*/
		File outputFile = new File(targetFileName);
		
		byte keys[] = password.getBytes();
		byte key = keys[0];
		for(int i =1;i<keys.length;i++){
				key=(byte) (key^keys[i]);
		}
		
		try {
			FileInputStream fileInputStream = new FileInputStream(inputFile);
			FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
			byte buffer[]= new byte[1024];
			int count;
			while((count = fileInputStream.read(buffer))!=-1){
				for(int i=0;i<count;i++){
					buffer[i] = (byte) (buffer[i]^key);
				}
				fileOutputStream.write(buffer,0,count);
			}
			fileInputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*public static boolean Decrypt(String sourceFileName,String targetFileName,String key){
		
	}*/

}
