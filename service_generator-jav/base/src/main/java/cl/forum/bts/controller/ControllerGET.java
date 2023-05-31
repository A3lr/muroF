@ApiOperation(value = "####operation_description####", notes = "####operation_notes####")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Btinreq.class),
			@ApiResponse(code = 404, message = "Not Found", response = String.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 500, message = "Internal Server Error", response = String.class) })
	@GetMapping(value = "####endpoint_out####", produces = "application/json")
	public ResponseEntity<Object> ####method_http####(@RequestHeader(name = "Canal", required = true) String canal,
			@RequestHeader(name = "Requerimiento", required = true) String requerimiento,
			@RequestHeader(name = "Usuario", required = true) String usuario,
			@RequestHeader(name = "Token", required = true) String token,
			@RequestHeader(name = "Device", required = true) String device,
			HttpServletRequest request
			####path_vars_main####)
			throws ResponseException, ParseException, IOException {

     
		Btinreq theBtinreq = new Btinreq();
		theBtinreq.setCanal(canal);
		theBtinreq.setRequerimiento(requerimiento);
		theBtinreq.setDevice(device);
		theBtinreq.setToken(token);
		theBtinreq.setUsuario(usuario);
		
		####path_vars_body####
		
		####mapping_pojo####

		Map<String, Object> responseEntity = ####country####Service.####id_operation####(data);

		GetResponse<Object> respuesta = new GetResponse<>();
		DetailResponse detailResponse = new DetailResponse(HttpStatus.OK.toString(), OK_REQUEST);

		respuesta.setBody(responseEntity);
		respuesta.setDetailResponse(detailResponse);
		respuesta.setSuccess(true);

		return new ResponseEntity<>(respuesta, HttpStatus.OK);

	}
