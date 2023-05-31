package cl.forum.generator.springboot.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

public class Utils {

	public static void replaceTextInFile(String sourceFilePath, String oldString, String newString) {

		File fileToBeModified = new File(sourceFilePath);

		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));

			// Reading all the lines of input text file into oldContent

			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent

			String newContent = oldContent.replaceAll(oldString, newString);

			// Rewriting the input text file with newContent

			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources

				reader.close();

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static String readTextInFile(String sourceFilePath) {

		String content = "";
		try {
			content = new String(Files.readAllBytes(Paths.get(sourceFilePath)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return content;

	}

	public static void concatTextInFile(String sourceFilePath, String newString) {

		File fileToBeModified = new File(sourceFilePath);

		String oldContent = "";

		BufferedReader reader = null;

		FileWriter writer = null;

		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));

			// Reading all the lines of input text file into oldContent

			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();

				line = reader.readLine();
			}

			// Replacing oldString with newString in the oldContent

			String newContent = oldContent.concat(newString);

			// Rewriting the input text file with newContent

			writer = new FileWriter(fileToBeModified);

			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Closing the resources

				reader.close();

				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void renameFileName(String pathController, String oldName, String newName) {

		File oldFile = new File(pathController + oldName);
		File newFile = new File(pathController + newName);
		oldFile.renameTo(newFile);

	}

	public static void deleteFile(String pathFile) {

		File file = new File(pathFile);
		file.delete();

	}
	
	public static void deleteFilesFromFolder(String pathFolder) {

		File directory = new File(pathFolder);
	    Collection<File> archivosJson = FileUtils.listFiles(directory, new WildcardFileFilter("*.json"), null);
	    archivosJson.forEach(File::delete);
	    
//	    
//		final File downloadDirectory = new File(pathFolder);
////		final File[] files = downloadDirectory.listFiles().
//	    final File[] files = downloadDirectory.listFiles( (dir,name) -> name.matches("*.json?" ));
//	    Arrays.asList(files).stream().forEach(File::delete);
//		
//		Path pathDirectory = Paths.get(pathFolder); 
//	    try {
//			Files.list(pathDirectory).filter(p -> p.toString().contains(".json")).forEach((p) -> {
//		        try {
//					Files.deleteIfExists(p);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			});
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	    
	}

	public static void pojoGenerate(String pathJsonFile, String packageName, String pojoDirectory) {
		File inputJson = new File(pathJsonFile);
		File outputPojoDirectory = new File(pojoDirectory);
		outputPojoDirectory.mkdirs();
		try {
			new JsonToPojo().convert2JSON(inputJson.toURI().toURL(), outputPojoDirectory, packageName,
					inputJson.getName().replace(".json", ""));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Encountered issue while converting to pojo: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
