package cl.forum.arq.dao;

import cl.forum.arq.exception.ResponseException;
import cl.forum.arq.pojo.request.Sucursales;

import java.sql.SQLException;

public interface EliminarSucursalUsuarioBTDao {

	public void deleteSucursalUsuario(Sucursales sucursales) throws ResponseException, SQLException;

}
