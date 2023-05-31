package cl.forum.generator.springboot.tasks;

import java.io.File;

import cl.forum.generator.springboot.utils.JsonToPojo;
import cl.forum.generator.springboot.utils.Utils;

public class CustomElements {

	public static void modifyPOMFile(String projectFolder, String groupId, String artefactId, String artefactName,
			String artefactDescription) {

		Utils.replaceTextInFile(projectFolder + File.separator + "pom.xml", "####group_id####", groupId);
		Utils.replaceTextInFile(projectFolder + File.separator + "pom.xml", "####artefact_id####", artefactId);
		Utils.replaceTextInFile(projectFolder + File.separator + "pom.xml", "####description####", artefactDescription);

	}

	public static void modifyControllerFile(String projectFolder, String mainEntity, String[][] operations) {

		StringBuilder controllerContent = new StringBuilder();

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "controller" + File.separator;

		String pathVarsMainAppend = "";
		String pathVarsBodyAppend = "";
		String custom_imports = "";

		for (String[] operation : operations) {

			String mappingPojo1 = "JsonObject data = BtsUtil.mappingPojoToBts(theBtinreq);";
			String mappingPojo2 = "JsonObject data = BtsUtil.mappingPojoToBts(theBtinreq, mainEntity, \"" + operation[7]
					+ "\");";
			String mappingPojo3 = "JsonObject data = BtsUtil.mappingPojoToBts(theBtinreq, mainEntity, list, \""
					+ operation[7] + "\");";
			String mappingPojo4 = "JsonObject data = BtsUtil.mappingPojoToBts(theBtinreq, list);";

			String upperMainEntity = "";
			if ("" != operation[7] && (operation[1].equals("Post") || operation[1].equals("Put"))) {
				upperMainEntity = (String.valueOf(operation[7].charAt(0)).toUpperCase()) + operation[7].substring(1);
				if (!custom_imports.contains("import cl.forum.bts.pojo.request." + upperMainEntity)) {
					custom_imports += "import cl.forum.bts.pojo.request." + upperMainEntity + ";\n";
				}
			}

			if (!custom_imports.contains("org.springframework.web.bind.annotation." + operation[1] + "Mapping;")) {
				custom_imports += "import org.springframework.web.bind.annotation." + operation[1] + "Mapping;\n";
			}

			pathVarsMainAppend = "";
			pathVarsBodyAppend = "";
			if (!operation[3].equals("") || (operation[9] != null && !operation[9].equals(""))) {
				pathVarsBodyAppend = "Map<String, String> map = new HashMap<>();\n";
				if (!operation[3].equals("")) {
					for (String pathvar : operation[3].split(",")) {
						pathVarsMainAppend += ",";
						String pathVarsMain = "@PathVariable(name = \"" + pathvar + "\") String " + pathvar;
						pathVarsMainAppend += pathVarsMain;

						String pathBodyMain = "		map.put(\"" + pathvar + "\", " + pathvar + ");\n";
						pathVarsBodyAppend += pathBodyMain;

					}
				}
				if (operation[9] != null) {
					for (String customInputValue : operation[9].split(",")) {
						String pathBodyMain = "		map.put(\"" + customInputValue + "\", mainEntity.get"
								+ (String.valueOf(customInputValue.charAt(0)).toUpperCase())
								+ customInputValue.substring(1) + "());\r\n";
						pathVarsBodyAppend += pathBodyMain;
					}
				}
				pathVarsBodyAppend += "		List<Map<String, String>> list = new ArrayList<>();\n"
						+ "		list.add(map);\n";
			}

			controllerContent.append("\n    ");
			switch (operation[1]) {
			case "Get":
				String getContent = Utils.readTextInFile(pathController + "ControllerGET.java");
				getContent = getContent.replaceAll("####id_operation_capital####",
						(String.valueOf(operation[0].charAt(0)).toUpperCase()) + operation[0].substring(1));
				getContent = getContent.replaceAll("####path_vars_main####", pathVarsMainAppend);
				getContent = getContent.replaceAll("####path_vars_body####", pathVarsBodyAppend);
				getContent = getContent.replaceAll("####mapping_pojo####",
						(pathVarsMainAppend != "") ? mappingPojo4 : mappingPojo1);
				getContent = getContent.replace("####endpoint_out####", operation[2]);

				getContent = getContent.replace("####method_http####", operation[0]);
				getContent = getContent.replace("####operation_description####", operation[5]);
				getContent = getContent.replace("####operation_notes####", operation[6]);

				controllerContent.append(getContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Post":
				String postContent = Utils.readTextInFile(pathController + "ControllerPOST.java");
				postContent = postContent.replaceAll("####id_operation_capital####",
						(String.valueOf(operation[0].charAt(0)).toUpperCase()) + operation[0].substring(1));
				postContent = postContent.replaceAll("####path_vars_main####", pathVarsMainAppend);
				postContent = postContent.replaceAll("####path_vars_body####", pathVarsBodyAppend);

				postContent = postContent.replaceAll("####mapping_pojo####",
						(operation[8] != null && operation[8].equals("CustomClass")) ? mappingPojo4
								: ((!pathVarsMainAppend.equals("")) ? mappingPojo3 : mappingPojo2));

				postContent = postContent.replace("####mainEntity####", upperMainEntity);

				postContent = postContent.replace("####endpoint_out####", operation[2]);

				postContent = postContent.replace("####method_http####", operation[0]);
				postContent = postContent.replace("####operation_description####", operation[5]);
				postContent = postContent.replace("####operation_notes####", operation[6]);

				controllerContent.append(postContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Put":
				String putContent = Utils.readTextInFile(pathController + "ControllerPUT.java");
				putContent = putContent.replaceAll("####id_operation_capital####",
						(String.valueOf(operation[0].charAt(0)).toUpperCase()) + operation[0].substring(1));
				putContent = putContent.replaceAll("####path_vars_main####", pathVarsMainAppend);
				putContent = putContent.replaceAll("####path_vars_body####", pathVarsBodyAppend);
				putContent = putContent.replaceAll("####mapping_pojo####",
						(operation[8] != null && operation[8].equals("CustomClass")) ? mappingPojo4
								: ((!pathVarsMainAppend.equals("")) ? mappingPojo3 : mappingPojo2));
				putContent = putContent.replace("####mainEntity####", upperMainEntity);
				putContent = putContent.replace("####endpoint_out####", operation[2]);

				putContent = putContent.replace("####method_http####", operation[0]);
				putContent = putContent.replace("####operation_description####", operation[5]);
				putContent = putContent.replace("####operation_notes####", operation[6]);

				controllerContent.append(putContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Delete":

				String deleteContent = Utils.readTextInFile(pathController + "ControllerDELETE.java");
				deleteContent = deleteContent.replaceAll("####id_operation_capital####",
						(String.valueOf(operation[0].charAt(0)).toUpperCase()) + operation[0].substring(1));
				deleteContent = deleteContent.replaceAll("####path_vars_main####", pathVarsMainAppend);
				deleteContent = deleteContent.replaceAll("####path_vars_body####", pathVarsBodyAppend);
				deleteContent = deleteContent.replaceAll("####mapping_pojo####",
						(pathVarsMainAppend != "") ? mappingPojo4 : mappingPojo1);
				deleteContent = deleteContent.replace("####endpoint_out####", operation[2]);

				deleteContent = deleteContent.replace("####method_http####", operation[0]);
				deleteContent = deleteContent.replace("####operation_description####", operation[5]);
				deleteContent = deleteContent.replace("####operation_notes####", operation[6]);

				controllerContent.append(deleteContent.replaceAll("####id_operation####", operation[0]));
				break;

			default:
				break;
			}
		}

		Utils.concatTextInFile(pathController + "Controller.java", controllerContent.toString());
		Utils.concatTextInFile(pathController + "Controller.java", "\n}");

		Utils.replaceTextInFile(pathController + "Controller.java", "####Country####", mainEntity);
		Utils.replaceTextInFile(pathController + "Controller.java", "####country####", mainEntity.toLowerCase());

		Utils.replaceTextInFile(pathController + "Controller.java", "####custom_imports####", custom_imports);

		Utils.renameFileName(pathController, "Controller.java", mainEntity + "Controller.java");

		Utils.deleteFile(pathController + "ControllerGET.java");
		Utils.deleteFile(pathController + "ControllerPOST.java");
		Utils.deleteFile(pathController + "ControllerPUT.java");
		Utils.deleteFile(pathController + "ControllerDELETE.java");

	}

	public static void modifyMainFile(String projectFolder, String mainEntity) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "main" + File.separator;
		;

		Utils.replaceTextInFile(pathController + "ServiceApplication.java", "####Country####", mainEntity);
		Utils.replaceTextInFile(pathController + "ServiceApplication.java", "####country####",
				mainEntity.toLowerCase());

		Utils.renameFileName(pathController, "ServiceApplication.java", mainEntity + "ServiceApplication.java");

	}

	public static void modifyConfigFile(String projectFolder, String mainEntity) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "config" + File.separator;

		Utils.replaceTextInFile(pathController + "RestCountryConfig.java", "####Country####", mainEntity);
		Utils.replaceTextInFile(pathController + "RestCountryConfig.java", "####country####", mainEntity.toLowerCase());

		Utils.renameFileName(pathController, "RestCountryConfig.java", "Rest" + mainEntity + "Config.java");

	}

	public static void modifySwaggerConfigFile(String projectFolder, String title, String description) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "config" + File.separator;

		Utils.replaceTextInFile(pathController + "SwaggerConfig.java", "####title####", title);
		Utils.replaceTextInFile(pathController + "SwaggerConfig.java", "####description####", description);

	}

	public static void modifyServicesFile(String projectFolder, String mainEntity, String[][] operations) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "service" + File.separator;
		String pathImplService = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "service" + File.separator + "impl" + File.separator;

		Utils.replaceTextInFile(pathController + "Service.java", "####Country####", mainEntity);
		Utils.replaceTextInFile(pathController + "Service.java", "####country####", mainEntity.toLowerCase());

		String getContent = Utils.readTextInFile(pathImplService + "ServiceImplGET.java");
		String postContent = Utils.readTextInFile(pathImplService + "ServiceImplPOST.java");
		String putContent = Utils.readTextInFile(pathImplService + "ServiceImplPUT.java");
		String deleteContent = Utils.readTextInFile(pathImplService + "ServiceImplDELETE.java");

		StringBuilder serviceInterfaceDeclaration = new StringBuilder();
		StringBuilder serviceImplDeclaration = new StringBuilder();
		for (String[] operation : operations) {
			switch (operation[1]) {
			case "Get":
				serviceInterfaceDeclaration.append("\n\tMap<String, Object> " + operation[0]
						+ "(JsonObject data) throws IOException, ResponseException, ParseException;\n");
				serviceImplDeclaration.append(getContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Post":
				serviceInterfaceDeclaration.append("\n\tMap<String, Object> " + operation[0]
						+ "(JsonObject data) throws IOException, ResponseException, ParseException;\n");
				serviceImplDeclaration.append(postContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Put":
				serviceInterfaceDeclaration.append("\n\tMap<String, Object> " + operation[0]
						+ "(JsonObject data) throws IOException, ResponseException, ParseException;\n");
				serviceImplDeclaration.append(putContent.replaceAll("####id_operation####", operation[0]));
				break;
			case "Delete":
				serviceInterfaceDeclaration.append("\n\tMap<String, Object> " + operation[0]
						+ "(JsonObject data) throws IOException, ResponseException, ParseException;\n");
				serviceImplDeclaration.append(deleteContent.replaceAll("####id_operation####", operation[0]));
				break;

			default:
				break;
			}
		}

		Utils.concatTextInFile(pathController + "Service.java", serviceInterfaceDeclaration.toString());
		Utils.concatTextInFile(pathController + "Service.java", "\n}");

		Utils.renameFileName(pathController, "Service.java", mainEntity + "Service.java");

		Utils.concatTextInFile(pathImplService + "ServiceImpl.java", serviceImplDeclaration.toString());
		Utils.concatTextInFile(pathImplService + "ServiceImpl.java", "\n}");
		Utils.replaceTextInFile(pathImplService + "ServiceImpl.java", "####Country####", mainEntity);
		Utils.replaceTextInFile(pathImplService + "ServiceImpl.java", "####country####", mainEntity.toLowerCase());

		Utils.renameFileName(pathImplService, "ServiceImpl.java", mainEntity + "ServiceImpl.java");

		Utils.deleteFile(pathImplService + "ServiceImplGET.java");
		Utils.deleteFile(pathImplService + "ServiceImplPOST.java");
		Utils.deleteFile(pathImplService + "ServiceImplPUT.java");
		Utils.deleteFile(pathImplService + "ServiceImplDELETE.java");
	}

	public static void setAplicationYML(String projectFolder, String[][] operations, String servicePort,
			String artefactName) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator;

		StringBuilder newproperties = new StringBuilder();

		for (String[] operation : operations) {
			newproperties.append("\n    ");
			newproperties.append(operation[0] + "URI").append(": \uff04").append(operation[4]);
		}

		Utils.replaceTextInFile(pathController + "application.yml", "####endpoints_bts####", newproperties.toString());

		Utils.replaceTextInFile(pathController + "application.yml", "####service_port####", servicePort);
		Utils.replaceTextInFile(pathController + "application.yml", "####service_name####", artefactName);

	}

	public static void setEndpointsController(String projectFolder, String[][] operations, String artefactDescription,
			String mainEntity) {

		String pathController = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "java" + File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator
				+ "controller" + File.separator;

		StringBuilder newproperties = new StringBuilder();

		for (String[] operation : operations) {
			newproperties.append("\n    ");
			newproperties.append(operation[0]).append(": ").append(operation[4]);
		}

		Utils.replaceTextInFile(pathController + mainEntity + "Controller.java", "####endpoints_bts####",
				newproperties.toString());
		Utils.replaceTextInFile(pathController + mainEntity + "Controller.java", "####artefact_description####",
				artefactDescription);

	}

	public static void createPojos(String projectFolder, String[][] operations, String artefactDescription,
			String mainEntity) {

		for (String[] operation : operations) {
			if (operation[1].equals("Put") || operation[1].equals("Post")) {

				String pathJsonFile = projectFolder + File.separator + "src" + File.separator + "main" + File.separator
						+ "resources" + File.separator + operation[0] + "Request.json";
				JsonToPojo.convert(pathJsonFile, "cl.forum.bts.pojo.request", projectFolder + File.separator + "src"
						+ File.separator + "main" + File.separator + "java" + File.separator);
			}

		}

		Utils.deleteFile(projectFolder + File.separator + "src" + File.separator + "main" + File.separator + "java"
				+ File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator + "pojo"
				+ File.separator + "request" + File.separator + "Btinreq.java");

		Utils.deleteFilesFromFolder(projectFolder + File.separator + "src" + File.separator + "main" + File.separator
				+ "resources" + File.separator);

		Utils.renameFileName(projectFolder + File.separator + "src" + File.separator + "main" + File.separator + "java"
				+ File.separator + "cl" + File.separator + "forum" + File.separator + "bts" + File.separator + "pojo"
				+ File.separator + "request" + File.separator, "Btinreq_source.java", "Btinreq.java");

	}

	public static void setReadme(String projectFolder, String[][] operations, String artefactDescription,
			String mainEntity, String artefactId, String servicePort, String agrupacion) {

		String pathController = projectFolder + File.separator;

		Utils.replaceTextInFile(pathController + "README.md", "&&&&&&artifactId&&&&&&", artefactId.toString());
		Utils.replaceTextInFile(pathController + "README.md", "&&&&&&agrupacion&&&&&&", agrupacion.toString());
		Utils.replaceTextInFile(pathController + "README.md", "&&&&&&artefact_description&&&&&&",
				artefactDescription.toString());

		for (String[] operation : operations) {
			if (operation[1] != null) {
				Utils.replaceTextInFile(pathController + "README.md", "&&&&&&tipometodo&&&&&&",
						operation[1].toString());
			}

			if (operation[2] != null) {
				Utils.replaceTextInFile(pathController + "README.md", "&&&&&&endpoint&&&&&&",
						"localhost:" + servicePort + "/" + agrupacion + "/");
			}

			if (operation[3] != null) {
				Utils.replaceTextInFile(pathController + "README.md", "&&&&&&parametros&&&&&&",
						operation[3].replace(",", "/").toString());
			}
		}
	}

}