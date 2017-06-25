package gr.glacious.rssEztv.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class TxtEditor {

	public List<String> readFromFile(File file) {
		List<String> lines = Collections.emptyList();

		try {
			lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);

		} catch (IOException e) {
			e.printStackTrace();

		}
		return lines;
	}

	public String writeToFile(String title,File file) throws IOException {
		List<String> txtList = readFromFile(file);
		if (txtList.contains(title)) {
			System.out.println("Already Exists");
			return "Already Exists";
		}

		FileWriter fw = new FileWriter(file, true);
		BufferedWriter out = new BufferedWriter(fw);
		
		out.write(title);
		out.newLine();
		out.flush();
		out.close();
		fw.close();
				

		return "OK";
	}
	
	public void deleteFromFile(String title,File file) throws FileNotFoundException, IOException{
		
			 
	        try {
	            if (!file.isFile()) {
	                System.out.println("Parameter is not an existing file");
	                return;
	            }
	            //Construct the new file that will later be renamed to the original filename. 
	            File tempFile = new File(file.getAbsolutePath() + ".tmp");
	            BufferedReader br = new BufferedReader(new FileReader(file));
	            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	            String line ;
	            //Read from the original file and write to the new 
	            //unless content matches data to be removed.
	            while ((line = br.readLine()) != null) {
	                if (!line.trim().equals(title)) {
	                    pw.println(line);
	                    pw.flush();
	                }
	            }
	            pw.close();
	            br.close();
	 
	            //Delete the original file
	            if (!file.delete()) {
	                System.out.println("Could not delete file");
	                return;
	            }
	            //Rename the new file to the filename the original file had.
	            if (!tempFile.renameTo(file))
	                System.out.println("Could not rename file");
	 
	        } catch (FileNotFoundException ex) {
	            ex.printStackTrace();
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
		
		
	}

}
