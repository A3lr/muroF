package cl.forum.generator.springboot.controller;

import cl.forum.generator.springboot.tasks.BasicElements;
import cl.forum.generator.springboot.tasks.CustomElements;

public class ServiceCreation {

	public static void initServiceCreation(String path, String groupId, String artefactId, String pathBaseClasses, String artefactName, String artefactDescription, String mainEntity, String[][] operations, String title, String servicePort, String agrupacion) {

		String projectFolder = path + artefactId;
		
		BasicElements.createBasicStructure(projectFolder);

		BasicElements.createBasicClasses(projectFolder, pathBaseClasses);
		
		CustomElements.modifyPOMFile(projectFolder, groupId, artefactId, artefactName, artefactDescription);
		
		CustomElements.modifyControllerFile(projectFolder, mainEntity, operations);
		
		CustomElements.modifyMainFile(projectFolder, mainEntity);
		
		CustomElements.modifyConfigFile(projectFolder, mainEntity);
		
		CustomElements.modifySwaggerConfigFile(projectFolder, title, artefactDescription);
		
		CustomElements.modifyServicesFile(projectFolder, mainEntity, operations);
		
		CustomElements.setAplicationYML(projectFolder, operations, servicePort, artefactName);
		
		CustomElements.setEndpointsController(projectFolder, operations, artefactDescription, mainEntity);
		
		CustomElements.createPojos(projectFolder, operations, artefactDescription, mainEntity);
		
		CustomElements.setReadme(projectFolder, operations, artefactDescription, mainEntity, artefactId,servicePort, agrupacion);

	}

}
