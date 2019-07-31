package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;

public class IO {
	
	private File file;
	
	public IO(){
		file = new File("res/score/score.txt");
	}
	
	public void loadFromTextFile(Map<Integer, String> scoreTable) throws IOException, FileNotFoundException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String s;
		while((s = reader.readLine()) != null) { 
			addString(s, scoreTable);
			}
		reader.close();
		}
	
	
	public void saveToTextFile(Map<Integer, String> scoreTable) throws IOException, FileNotFoundException {
		StringBuilder str;
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		
		for (Integer key : scoreTable.keySet()) {
			str = new StringBuilder().append(scoreTable.get(key)).append(";").append(key);
			writer.write(str.toString());
			writer.newLine();
		}
		writer.close();
		}
	
	public void addString(String str, Map<Integer, String> scoreTable) {
		String[] pair = str.trim().split(";");
		if(!str.isEmpty())
			scoreTable.put(Integer.parseInt(pair[1]),pair[0]);
		else
			return;
		}
	
	
}
