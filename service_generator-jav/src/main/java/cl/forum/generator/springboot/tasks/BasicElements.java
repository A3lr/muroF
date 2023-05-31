package cl.forum.generator.springboot.tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

public class BasicElements {

	public static void createBasicStructure(String projectFolder) {

		try {
			Files.createDirectories(Paths.get(projectFolder));
			System.out.println("! New Directory Successfully Created !");
		} catch (IOException ioExceptionObj) {
			System.out
					.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
		}

	}

	public static void createBasicClasses(String projectFolder, String pathBaseClasses) {

		File srcDir = new File(pathBaseClasses);
		File destDir = new File(projectFolder);
		try {
			FileUtils.copyDirectory(srcDir, destDir);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

}
