package cl.forum.arq.util;

import javax.ws.rs.core.Response;

import cl.forum.arq.pojo.response.DetailResponse;
import cl.forum.arq.pojo.response.GetResponse;

public final class CodigoRetornoRest {

	public static final String TIPOERROR = "E";

	private CodigoRetornoRest() {
	}

	public static Response returnMessage(boolean success, String code, String message) {

		GetResponse<Object> respuesta = new GetResponse<>();

		respuesta.setSuccess(success);
		respuesta.setDetailResponse(new DetailResponse(code, message));
		respuesta.setBody(null);

		return Response.status(Response.Status.OK).entity(respuesta).build();
	}

	public static Response returnMessage(boolean success, String code, String message, Object object) {

		GetResponse<Object> respuesta = new GetResponse<>();

		respuesta.setSuccess(success);
		respuesta.setDetailResponse(new DetailResponse(code, message));
		respuesta.setBody(object);

		return Response.status(Response.Status.OK).entity(respuesta).build();
	}
}