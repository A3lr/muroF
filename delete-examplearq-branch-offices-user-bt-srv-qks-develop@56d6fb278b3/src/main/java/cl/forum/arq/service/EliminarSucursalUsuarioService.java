package cl.forum.arq.service;

import java.sql.SQLException;

import cl.forum.arq.exception.ResponseException;
import cl.forum.arq.pojo.request.Sucursales;

import javax.ws.rs.core.Response;

public interface EliminarSucursalUsuarioService {

	public Response modificarRol(Sucursales sucursal) throws ResponseException, SQLException;

}
