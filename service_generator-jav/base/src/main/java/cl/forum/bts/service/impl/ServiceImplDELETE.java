
	
	public Map<String, Object> ####id_operation####(JsonObject data) throws IOException, ResponseException, ParseException {

		Map<String, Object> respuesta = null;

		respuesta = BtsUtil.readBtsMessage(callServicePost(data, properties.getProperties().get("####id_operation####URI"), HttpMethod.POST).getBody(), true);

		return respuesta;
	}