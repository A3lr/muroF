package cl.forum.bts.controller;

import java.io.IOException;
import java.text.ParseException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;

import cl.forum.bts.exception.ResponseException;
import cl.forum.bts.pojo.request.Btinreq;
import cl.forum.bts.pojo.response.DetailResponse;
import cl.forum.bts.pojo.response.GenericResponse;
import cl.forum.bts.pojo.response.GetResponse;
import cl.forum.bts.service.####Country####Service;
import cl.forum.bts.util.BtsUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

####custom_imports####

@RestController
@CrossOrigin(origins = { "http://prodconfluence01.forum.local:8090", "*.openshiftapps.com" }, methods= {RequestMethod.GET,RequestMethod.POST})
@Api(value = "####country####", tags = { "####artefact_description####" })
public class ####Country####Controller {

	public static final Logger LOG_SERVICE = LoggerFactory.getLogger("restservice");
	public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");
	private static final String OK_REQUEST = "Respuesta exitosa";

	@Autowired
	private ####Country####Service ####country####Service;

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
