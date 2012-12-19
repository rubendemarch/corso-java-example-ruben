package businessObject;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class ContenitorePasswords {

	private String path = "C:/TESTth/";
	private String zipPath = "C:/TESTth/Provaci.zip";
	private String threadNome = "";
	private ZipFile zip = null;

		
	public ContenitorePasswords(String threadNome) {
		
		try {
			zip = new ZipFile(zipPath);
		} catch (ZipException e) {
			e.printStackTrace();
			return;
		}
		this.threadNome = threadNome;
		new File(path+threadNome).mkdir();
	}
	
	public boolean isEncrypted()
	{
		try {
			return zip.isEncrypted();
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean isCorrectPass(String password)
	{
		try {
			zip.setPassword(password);
		} catch (ZipException e) {
			return false;
		}catch (NullPointerException npe){
			return false;
		}
		try {
			zip.extractAll(path + threadNome);
		} catch (ZipException e) {
			return false;
		}
		return true;
	}
	
	
	
	
	
	
}
