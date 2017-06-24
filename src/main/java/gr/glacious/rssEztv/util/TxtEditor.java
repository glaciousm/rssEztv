package gr.glacious.rssEztv.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TxtEditor {

	File file = new File("C:\\Users\\Glacious\\Documents\\favorites.txt");

	public List<String> readFromFile() {
		List<String> lines = Collections.emptyList();

		try {
			lines = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			System.out.println("Lines = " + lines);

		} catch (IOException e) {
			e.printStackTrace();

		}
		return lines;
	}

	public List<String> writeToFile() throws IOException {
		List<String> list = new ArrayList<>();

		FileOutputStream fos = new FileOutputStream(file);

		FileWriter fw = new FileWriter(file, true);
		
		fw.write("test");
		fw.flush();
		fw.close();
				

		return readFromFile();
	}

}
