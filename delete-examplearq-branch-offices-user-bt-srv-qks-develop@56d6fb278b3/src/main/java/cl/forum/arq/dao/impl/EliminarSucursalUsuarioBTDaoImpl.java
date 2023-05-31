package cl.forum.arq.dao.impl;

import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import org.jboss.logging.Logger;
import io.agroal.api.AgroalDataSource;

import javax.inject.Inject;
import javax.inject.Singleton;

import cl.forum.arq.dao.EliminarSucursalUsuarioBTDao;
import cl.forum.arq.exception.ResponseException;
import cl.forum.arq.pojo.request.Sucursales;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.microsoft.sqlserver.jdbc.SQLServerError;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.util.Arrays;

@Singleton
public class EliminarSucursalUsuarioBTDaoImpl implements EliminarSucursalUsuarioBTDao {

	@Inject
	Logger log;

	@Inject
	AgroalDataSource defaultDS;

	@ConfigProperty(name = "SP_ELIMINAR_SUCURSAL_USUARIO_BT")
	private String spEliminarSucursal;

	@Override
	public void deleteSucursalUsuario(Sucursales sucursales) throws ResponseException, SQLException {
		Connection con = null;
		CallableStatement cstm = null;

		try {
			String parameterCall = "{" + spEliminarSucursal + "}";
			log.info("STORE PROCEDURE " + spEliminarSucursal);
			con = defaultDS.getConnection();
			cstm = con.prepareCall(parameterCall, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			cstm.setString(1, sucursales.getUsuario());
			cstm.setString(2, getString(sucursales.getSucursales()));
			cstm.execute();

		} catch (SQLServerException sqlExc) {
			log.error(sqlExc.getMessage());

			if (cstm != null) {
				cstm.close();
			}
			if (con != null) {
				con.close();
			}

			SQLServerError sqlError = sqlExc.getSQLServerError();
			String msgSqlErr = null;

			if (sqlError != null) {
				msgSqlErr = "Error en el MotorSQL -> Numero: " + sqlError.getErrorNumber() + ", Estado: "
						+ sqlError.getErrorState() + ", Severidad: " + sqlError.getErrorSeverity() + ", Procedimiento: "
						+ sqlError.getProcedureName() + ", Linea: " + sqlError.getLineNumber() + ", Mensaje: "
						+ sqlError.getErrorMessage();
			}
			throw new ResponseException(msgSqlErr == null ? sqlExc.getMessage() : msgSqlErr);

		} finally {
			if (cstm != null) {
				cstm.close();
			}
			if (con != null) {
				con.close();
			}
		}

	}

	public String getString(String[] arr) {
		String ret = Arrays.toString(arr);
		ret = ret.replace("[", "");
		ret = ret.replace("]", "");
		log.info(ret);
		return ret;
	}

}
