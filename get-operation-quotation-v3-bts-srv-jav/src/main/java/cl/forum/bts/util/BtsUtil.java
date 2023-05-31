package cl.forum.bts.util;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import java.io.IOException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cl.forum.bts.exception.ResponseException;
import cl.forum.bts.exception.StatusResponseEnum;

public class BtsUtil {

	public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");
	private static final String ERROBTS = "Error en la ejecucion BTS{}";

	private static final int EXCEPCION_PLATAFORMA = 10001;
	private static final int EXCEPCION_PROGRAMA = 10002;

	private static final int CANAL_NO_DECLARADO = 10021;
	private static final int CANAL_DESHABILITADO = 10022;
	private static final int SERVICIO_NO_HABILITADO_EN_CANAL = 10023;
	private static final int SERVICIO_NO_DECLARADO_EN_CANAL = 10024;
	private static final int SERVICIO_NO_EXISTE = 10025;
	private static final int USUARIO_BTS_NO_VALIDO = 10026;
	private static final int USUARIO_EXTERNO_SIN_ASIGNACION_USUARIO_BTS = 10027;
	private static final int USUARIO_DESHABILITADO_PARA_SERVICIO = 10028;
	private static final int USUARIO_EXTERNO_DESHABILITADO = 10029;
	private static final int USUARIO_EXTERNO_NO_ASOCIADO_AL_SERVICIO_EN_CANAL = 10030;
	private static final int SERVICIO_MAL_CONFIGURADO = 10031;
	private static final int TOKEN_NO_VALIDO = 30003;
	private static final String ERRORESNEGOCIO = "Erroresnegocio";
	private static final String ERRORESNEGOCIOS = "BTErrorNegocio";

	private BtsUtil() {
	}

// GET SIN PARAETRO
	public static <T> JsonObject mappingPojoToBts(T a) throws JsonProcessingException {

		JsonObject json = new JsonObject();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(a);
		JsonObject j = new JsonParser().parse(jsonString).getAsJsonObject();

		json.add(a.getClass().getSimpleName(), j);

		return json;
	}

// GET CON PARAETRO Y DELETE
	public static <T> JsonObject mappingPojoToBts(T a, List<Map<String, String>> id) throws JsonProcessingException {

		JsonObject json = new JsonObject();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(a);
		JsonObject j = new JsonParser().parse(jsonString).getAsJsonObject();

		json.add(a.getClass().getSimpleName(), j);

		for (Map<String, String> s : id) {
			for (Entry<String, String> entry : s.entrySet()) {
				JsonElement l = new JsonParser().parse(entry.getValue());
				json.add(entry.getKey(), l);
			}
		}
		return json;
	}

	public static <T> JsonObject postPutUtil(T a, T b, String jsonName) throws JsonProcessingException {

		JsonObject json = new JsonObject();

		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(a);
		JsonObject j = new JsonParser().parse(jsonString).getAsJsonObject();

		ObjectMapper mapper2 = new ObjectMapper();
		String jsonString2 = mapper2.writeValueAsString(b);
		JsonObject k = new JsonParser().parse(jsonString2).getAsJsonObject();

		json.add(a.getClass().getSimpleName(), j);

		if (b.getClass().getName().contains("Request")) {
			ArrayList<String[]> nodeNames = getNodeName(k);

			for (String[] elemento : nodeNames) {
				json.add(elemento[0], new JsonParser().parse(elemento[1]));
			}
		} else {
			json.add(jsonName, k);
		}
		return json;
	}

// PUT
	public static <T> JsonObject mappingPojoToBts(T a, T b, List<Map<String, String>> id, String jsonName)
			throws JsonProcessingException {

		JsonObject json = postPutUtil(a, b, jsonName);

		for (Map<String, String> s : id) {
			for (Entry<String, String> entry : s.entrySet()) {
				JsonElement l = new JsonParser().parse(entry.getValue());
				json.add(entry.getKey(), l);
			}
		}
		return json;
	}

// POST
	public static <T> JsonObject mappingPojoToBts(T a, T b, String jsonName) throws JsonProcessingException {
		return postPutUtil(a, b, jsonName);
	}

	public static Map<String, Object> readBtsMessage(String body, boolean pretty) throws ResponseException {

		int codigo = 0;
		String descripcion = "";
		JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();

		JsonArray errorNegocio = jsonObject.getAsJsonObject(ERRORESNEGOCIO).getAsJsonArray(ERRORESNEGOCIOS);

		for (int i = 0; i < errorNegocio.size(); i++) {
			codigo = errorNegocio.get(i).getAsJsonObject().get("Codigo").getAsInt();
			descripcion = errorNegocio.get(i).getAsJsonObject().get("Descripcion").getAsString();
		}

		if (codigo != 0) {
			if (codigo == EXCEPCION_PLATAFORMA || codigo == EXCEPCION_PROGRAMA) {
				LOG_ERROR.error(ERROBTS, descripcion);
				throw new ResponseException(descripcion, StatusResponseEnum.INTERNAL_SERVER_ERROR, true);
			}
			if (codigo == CANAL_NO_DECLARADO || codigo == SERVICIO_NO_HABILITADO_EN_CANAL
					|| codigo == SERVICIO_MAL_CONFIGURADO || codigo == CANAL_DESHABILITADO
					|| codigo == SERVICIO_NO_EXISTE || codigo == SERVICIO_NO_DECLARADO_EN_CANAL
					|| codigo == TOKEN_NO_VALIDO) {
				LOG_ERROR.error(ERROBTS, descripcion);
				throw new ResponseException(descripcion, StatusResponseEnum.UNAUTHORIZED, true);
			}
			if (codigo == USUARIO_EXTERNO_SIN_ASIGNACION_USUARIO_BTS || codigo == USUARIO_BTS_NO_VALIDO
					|| codigo == USUARIO_EXTERNO_NO_ASOCIADO_AL_SERVICIO_EN_CANAL
					|| codigo == USUARIO_EXTERNO_DESHABILITADO || codigo == USUARIO_DESHABILITADO_PARA_SERVICIO) {
				LOG_ERROR.error(ERROBTS, descripcion);
				throw new ResponseException(descripcion, StatusResponseEnum.FORBIDDEN, true);
			}
			if (pretty) {
				Map<String, Object> map = generateMap(jsonObject.getAsJsonObject(ERRORESNEGOCIO).toString());
				jsonObject.remove(ERRORESNEGOCIO);
				jsonObject.remove("Btoutreq");
				jsonObject.remove("Btinreq");

				return map;
			}
		}

		if (pretty) {
			jsonObject.remove(ERRORESNEGOCIO);
			jsonObject.remove("Btoutreq");
			jsonObject.remove("Btinreq");
		}

		Map<String, Object> yourHashMap = null;
		generateMap(jsonObject.toString());

		yourHashMap = generateMap(jsonObject.toString());
		return yourHashMap;

	}

	private static ArrayList<String[]> getNodeName(JsonObject k) {

		Set<Map.Entry<String, JsonElement>> entrySet = k.entrySet();
		ArrayList<String[]> list = new ArrayList<>();
		for (Map.Entry<String, JsonElement> entry : entrySet) {
			String[] nodeName = new String[2];
			nodeName[0] = entry.getKey();
			nodeName[1] = entry.getValue().toString();
			list.add(nodeName);
		}
		return list;
	}

	private static Map<String, Object> generateMap(String json) throws ResponseException {
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS,
				true);
		Map<String, Object> map = null;
		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			throw new ResponseException("Problemas al mapear la respuesta", StatusResponseEnum.INTERNAL_SERVER_ERROR,
					true);
		}
		return map;
	}
}