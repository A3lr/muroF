package cl.forum.arq.util;

import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.mutiny.unchecked.Unchecked;

public class APIUtils {

	private APIUtils() {
	}

	public static <T> T castObject(Object obj, Class<T> clazz) {
		return objectMapperProcessor(
				Unchecked.function(objectMapper -> objectMapper.readValue(toJsonString(obj), clazz)));
	}

	public static <T> T castObject(String json, TypeReference<T> typeReference) {
		return objectMapperProcessor(Unchecked.function(objectMapper -> objectMapper.readValue(json, typeReference)));
	}

	public static <T> T castObject(Object obj, TypeReference<T> typeReference) {
		return objectMapperProcessor(
				Unchecked.function(objectMapper -> objectMapper.readValue(toJsonString(obj), typeReference)));
	}

	public static <T> T castObject(String json, Class<T> clazz) {
		return objectMapperProcessor(Unchecked.function(objectMapper -> objectMapper.readValue(json, clazz)));
	}

	private static <R> R objectMapperProcessor(Function<ObjectMapper, R> code) {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return code.apply(objectMapper);
	}

	public static String toJsonString(Object stringObjectMap) {
		return objectMapperProcessor(
				Unchecked.function(objectMapper -> objectMapper.writeValueAsString(stringObjectMap)));
	}

	public static String toJsonString(Map<String, Object> stringObjectMap) {
		return objectMapperProcessor(
				Unchecked.function(objectMapper -> objectMapper.writeValueAsString(stringObjectMap)));
	}
}