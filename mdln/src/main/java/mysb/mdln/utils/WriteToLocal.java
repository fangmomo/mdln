package mysb.mdln.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import mysb.mdln.domain.Chapter;


public class WriteToLocal {
	
	public static final String separator = File.separator;
	
	public static void save(String username,String bookname,Chapter chapter) {
		FileWriter fw = null; 
		String dir = separator+"home"+separator+"books"+separator+username;
		File file = new File(dir);
		if(!file.exists()&&!file.isDirectory())      
		{      
			file.mkdir();   
		}
		dir = separator+"home"+separator+"books"+separator+username+separator+bookname;
		File file1 =new File(dir);
		if(!file1.exists()&&!file1.isDirectory())      
		{      
			 file1.mkdir();
		      
		}
		String savename = dir+separator+String.valueOf(chapter.getCount())+".txt";
		try {
			fw = new FileWriter(savename);
			fw.write(chapter.getTitle()+"\r\n");
			fw.write(chapter.getContent());
			fw.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public static String read(String author,String bookname,int chaptercount) {
		FileReader fr = null;
		BufferedReader br = null;
		String novelname = separator+"home"+separator+"books"+separator+author+separator+bookname+separator+String.valueOf(chaptercount)+".txt";
		File file = new File(novelname);
		String content = "";
		String line = "";
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			line = br.readLine();
			while(line!=null) {
				content =content + line+"\r\n";
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch ( IOException e) {
			e.printStackTrace();
			return null;
		}
		return content;
	}
	
}
