package cl.forum.bts.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import cl.forum.bts.exception.ResponseException;
import cl.forum.bts.pojo.request.Btinreq;
import cl.forum.bts.pojo.response.DetailResponse;
import cl.forum.bts.pojo.response.GenericResponse;
import cl.forum.bts.pojo.response.GetResponse;
import cl.forum.bts.service.ObtenerDatosCotizacionV3Service;
import cl.forum.bts.util.BtsUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = { "http://prodconfluence01.forum.local:8090", "*.openshiftapps.com" }, methods = {
		RequestMethod.GET, RequestMethod.POST })
@Api(value = "obtenerdatoscotizacionv3", tags = { "Obtiene los datos de Cotización dada la Instancia." })
public class ObtenerDatosCotizacionV3Controller {

	public static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");
	public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");
	private static final String OK_REQUEST = "Respuesta exitosa";

	@Autowired
	private ObtenerDatosCotizacionV3Service obtenerdatoscotizacionv3Service;

	@ControllerAdvice
	public class ErrorHandler {
		@ExceptionHandler(ResponseException.class)
		public ResponseEntity<GenericResponse> methodCatchResponseException(HttpServletRequest request,
				ResponseException e) {
			LOG_ERROR.error("Error info ", e);
			GenericResponse errorInfo = new GenericResponse(e);
			return new ResponseEntity<>(errorInfo,
					HttpStatus.valueOf(Integer.parseInt(e.getStatusResponseEnum().getStatusCode())));
		}

		@ExceptionHandler(Exception.class)
		public ResponseEntity<GenericResponse> methodCatchException(HttpServletRequest request, Exception e) {
			GenericResponse errorInfo = new GenericResponse(e);
			LOG_ERROR.error("BAD REQUEST : ", e);
			return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Obtener Datos Cotización v3", notes = "Obtiene los datos de Cotización dada la Instancia.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Btinreq.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = String.class) })
	@GetMapping(value = "/operation/quotation-v3/${info.version}/{Instancia}", produces = "application/json")
	public ResponseEntity<Object> obtenerDatosCotizacionV3(@RequestHeader(name = "Canal", required = true) String canal,
			@RequestHeader(name = "Requerimiento", required = true) String requerimiento,
			@RequestHeader(name = "Usuario", required = true) String usuario,
			@RequestHeader(name = "Token", required = true) String token,
			@RequestHeader(name = "Device", required = true) String device, HttpServletRequest request,
			@PathVariable(name = "Instancia") String instancia) throws ResponseException, ParseException, IOException {

		Btinreq theBtinreq = new Btinreq();
		theBtinreq.setCanal(canal);
		theBtinreq.setRequerimiento(requerimiento);
		theBtinreq.setDevice(device);
		theBtinreq.setToken(token);
		theBtinreq.setUsuario(usuario);

		Map<String, String> map = new HashMap<>();
		map.put("Instancia", instancia);
		List<Map<String, String>> list = new ArrayList<>();
		list.add(map);

		JsonObject data = BtsUtil.mappingPojoToBts(theBtinreq, list);

		Map<String, Object> responseEntity = obtenerdatoscotizacionv3Service.obtenerDatosCotizacionV3(data);

		GetResponse<Object> respuesta = new GetResponse<>();
		DetailResponse detailResponse = new DetailResponse(HttpStatus.OK.toString(), OK_REQUEST);

		respuesta.setBody(responseEntity);
		respuesta.setDetailResponse(detailResponse);
		respuesta.setSuccess(true);

		return new ResponseEntity<>(respuesta, HttpStatus.OK);

	}

}
