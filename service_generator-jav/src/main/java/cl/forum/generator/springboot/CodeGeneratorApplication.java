package cl.forum.generator.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.forum.generator.springboot.controller.ServiceCreation;

@SpringBootApplication
public class CodeGeneratorApplication {

	private static String groupId = "cl.forum.bts"; //No Modificar
	
	private static String path = "D:\\Workspace\\Generados\\";  //Carpeta donde quedan los servicios generados
	private static String pathBaseClasses = "D:\\Workspace\\Generador\\service_generator-jav\\base"; //Aqui van los Request
	
	private static String artefactId = "get-operation-instance-data-for-printing-v2-bts"; //Nombre del servicio Integracion
	private static String agrupacion = "operation/instance-data-for-printing-v2"; //Dominio/Servicio
	
	private static String metodoExposicion = "Get"; // Get - Post - Put - Delete
	
	private static String title = "Obtener Datos de Instancia para Impresion"; //Titulo para mostrar en Swagger
	private static String artefactDescription = "Devuelve todos los datos asociadas a una instancia, Este servicio será consumido para poder realizar la impresión de documentos desde otra aplicación.";  //Descripción del servicio
	
	private static String mainEntity = "ObtenerDatos"; //Nombre Metodo en Bantotal
	private static String idOperacion = "obtenerDatos"; //Minuscula
	
	private static String parametrosUri = "Instancia"; //Parametros separados por coma, si no lleva parametros dejar en ""
	private static String endPointParams = "{Instancia}"; // Parametros deben ir de la forma "{parametro}/" Si no lleva parametros dejar en ""
	//private static String bodyParams = "ABMAgendaTareasRequest"; // En caso de uso, descomentar esta linea y operations[0][7]

	private static String servicePort = "9857"; //Puerto asignado al servicio
	private static String servicioBT = "odwsbt_BTImpresosYDocumentos_v1.aspx"; //Servicio bantotal
	private static String urlbtsfijo = "{ENVIRONMENT_URLBTSRT:http://desacoreapp01.forum.local/btdesarrollo/}"; //URL Ambiente
	

	//DESDE AQUI SOLO COMENTAR O DESCOMENTAR LINEA operations[0][7] CUANDO CORRESPONDA
	private static String artefactName = artefactId;
	private static String[][] operations;
	static {
	     operations = new String[1][10];
		 operations[0][0] = idOperacion; // ID operacion
		 operations[0][1] = metodoExposicion; // método de exposición
		 operations[0][2] = "/"+ agrupacion + "/${info.version}/" + endPointParams; // endpoint de exposición
		 operations[0][3] = parametrosUri; // parámetros pasados por URI
		 operations[0][4] = urlbtsfijo + servicioBT + "?" + mainEntity;// endpoint BTS
		 operations[0][5] = title; // operation description (Swagger)
		 operations[0][6] = artefactDescription; // operation notes (Swagger)
		 //operations[0][7] = bodyParams; // Campos del Body
	}
		
	public static void main(String[] args) {
		SpringApplication.run(CodeGeneratorApplication.class, args);

		ServiceCreation.initServiceCreation(path, groupId, artefactId, pathBaseClasses, artefactName,
				artefactDescription, mainEntity, operations, title, servicePort, agrupacion);

	}

}
