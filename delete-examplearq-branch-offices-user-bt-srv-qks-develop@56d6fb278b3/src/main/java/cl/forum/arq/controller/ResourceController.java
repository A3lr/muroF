package cl.forum.arq.controller;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import cl.forum.arq.exception.ResponseException;
import cl.forum.arq.pojo.request.Sucursales;
import cl.forum.arq.service.EliminarSucursalUsuarioService;
import cl.forum.arq.util.CodigoRetornoRest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;


import java.sql.SQLException;

/**
* The ResourceController Class contains  samples of rest service that
* simply displays credit line detail,channel bifurcation and "Hello RESTEasy!" to the standard output.
*
* @author  Arquitectura
* @version 1.0
* @since   2022-06-15
*/
@Path("/")
public class ResourceController {

	@Inject
	Logger log;
	@Inject
	private EliminarSucursalUsuarioService service;
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("sucursal-usuario")
	public Response deleteSucursal(@RequestBody Sucursales sucursal) throws SQLException {
		
		try {
	
			log.info(" ################################################# ");
			log.info("          SERVICIO ELIMINAR SUCURSALES BT ");
			
			return service.modificarRol(sucursal);

		} catch(ResponseException e) {
			return CodigoRetornoRest.returnMessage(false, "500", e.getMessage(), null);
		}	
	}	
}