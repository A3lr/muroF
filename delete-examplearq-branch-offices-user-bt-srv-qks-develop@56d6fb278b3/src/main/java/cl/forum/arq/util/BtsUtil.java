package cl.forum.arq.util;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import org.jboss.logging.Logger;

import cl.forum.arq.exception.ResponseExceptionA;
import cl.forum.arq.exception.StatusResponseEnum;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BtsUtil {
	@Inject
	static Logger log;

	private BtsUtil() {
	}

	public static Map<String, Object> generateMap(String json) throws ResponseExceptionA {

		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS,
				true);
		Map<String, Object> map = null;

		try {
			map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (IOException e) {
			throw new ResponseExceptionA("Problemas al mapear la respuesta", StatusResponseEnum.INTERNAL_SERVER_ERROR,
					true);
		}

		return map;
	}
}