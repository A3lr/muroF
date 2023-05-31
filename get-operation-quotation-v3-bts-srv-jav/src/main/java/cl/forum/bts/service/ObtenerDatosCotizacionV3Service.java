package cl.forum.bts.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import com.google.gson.JsonObject;

import cl.forum.bts.exception.ResponseException;

public interface ObtenerDatosCotizacionV3Service {

	Map<String, Object> obtenerDatosCotizacionV3(JsonObject data) throws IOException, ResponseException, ParseException;

}