package cl.forum.bts.service.impl;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonObject;

import cl.forum.bts.exception.StatusResponseEnum;
import cl.forum.bts.config.Properties;
import cl.forum.bts.exception.ResponseException;
import cl.forum.bts.service.####Country####Service;
import cl.forum.bts.util.BtsUtil;

@Service
public class ####Country####ServiceImpl implements ####Country####Service {

	@Autowired
	private Properties properties;
	@Autowired
	private RestTemplate restTemplate;

	public static final Logger LOG_ERROR = LoggerFactory.getLogger("error");
	public static final String ERRORINFO = "Error info {}";
	public static final String ERRORNOTNULL = "response cannot be null";

	public ResponseEntity<String> callServicePost(JsonObject data, String url, HttpMethod method)
			throws ResponseException {
		ResponseEntity<String> response = null;
		try {
			HttpEntity<String> request = new HttpEntity<>(data.toString());
			response = restTemplate.exchange(url, method, request, String.class);

		} catch (HttpClientErrorException | HttpServerErrorException httpClientErrorException) {

			throw new ResponseException(httpClientErrorException.getMessage(), StatusResponseEnum.INTERNAL_SERVER_ERROR,
					true);

		}

		return response;
	}
