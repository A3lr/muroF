package cl.forum.arq.service.impl;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

import cl.forum.arq.dao.EliminarSucursalUsuarioBTDao;
import cl.forum.arq.exception.ResponseException;
import cl.forum.arq.pojo.request.Sucursales;
import cl.forum.arq.service.EliminarSucursalUsuarioService;
import cl.forum.arq.util.CodigoRetornoRest;

import javax.ws.rs.core.Response;

@Singleton
public class EliminarSucursalesUsuarioServiceImpl implements EliminarSucursalUsuarioService {
	@Inject
	private EliminarSucursalUsuarioBTDao dao;

	@Override
	public Response modificarRol(Sucursales sucursal) throws ResponseException, SQLException {
		try {
			dao.deleteSucursalUsuario(sucursal);
			return CodigoRetornoRest.returnMessage(true, "200", "Respuesta exitosa");
		} catch (ResponseException sqlExc) {
			throw new ResponseException(sqlExc.getMessage());
		}

	}

}
