get-operation-quotation-v3-bts
====

Obtiene los datos de Cotización dada la Instancia.

- - -
	
Listado de operaciones y su descripción

1. /localhost:9914/operation/quotation-v3/${info.version}/Instancia			Get	:	Obtiene los datos de Cotización dada la Instancia.


## Descripción archivo YML

	properties: Endpoint de conexión a Bantotal.
	

# Obtención del Token a través del servicio
	http://localhost:8124/api/token/v1/accessToken
 
## Query Params
	username=usuario_test
	password=1234
	client_id=app_introspection
	client_secret=a5a206af-4380-46f1-9018-27a9a8c908c9
	grant_type=password

## JSON Respuesta de obtención del token
	 {
		    "accesToken": "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxclF2Sjg1Y3pYUExuMmpyb093bkhUby1YSHdtSklYZGJZZXZJZFlMQnF3In0.eyJqdGkiOiI3ZWIyNzFmYi0xNDA4LTQ2ZWMtOTMxZC04OTQ4NmJlZDM0Y2UiLCJleHAiOjE1ODQ5OTU0MjksIm5iZiI6MCwiaWF0IjoxNTg0OTk1MTI5LCJpc3MiOiJodHRwOi8vMTAuMjAuMS40ODo4MDgwL2F1dGgvcmVhbG1zL0JUVF9kZXNhIiwiYXVkIjoiYXBwX2ludHJvc3BlY3Rpb24iLCJzdWIiOiI2N2ZjMTQzMS1jZmE1LTRhNWQtOTgxMC0wMDJkYTJhNGMyYzUiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJhcHBfaW50cm9zcGVjdGlvbiIsImF1dGhfdGltZSI6MCwic2Vzc2lvbl9zdGF0ZSI6IjY4NzkwOWRkLTc5ZTAtNGM2Yy1iMjIxLTc1NzBmZTYzMWQ5OSIsImFjciI6IjEiLCJjbGllbnRfc2Vzc2lvbiI6ImY0MDBlZGY0LWRiYjgtNGY5MS04OGIyLTRlNmFlZjljMTFiYyIsImFsbG93ZWQtb3JpZ2lucyI6WyIiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsInZpZXctcHJvZmlsZSJdfSwiYXBwX2ludHJvc3BlY3Rpb24iOnsicm9sZXMiOlsiaW50cm9zcGVjdGlvbiJdfX0sIm5hbWUiOiIiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJ1c3VhcmlvX3Rlc3QifQ.XpzHgGseh1KuvuhHwtzydCOv2jNhP3jgXTX0OPxIENv19Oj_Ov4YM0E5qLAa-YUrFQD2dPfShZlFgGpsGxDEy4Ybpds3WTBZWz9QgXQkfqTCIPBK5qcXpUwvfgfOGVBd0j4aKAVC5SXbSLHr34XKtHTE5XctEuK8OCk-gBxQcSm-7LnXMJWRlqvzP9UGudmfd4UyIBLa_OIWLoGiPgv-wxOiNyCN13mqR6-nyBKCKlQI8nk5TBYXQEK_P6eH3htNQfXU0UGCA5vuoz48Ryfs7cqFWaRFDvddQ6eI4bnA-BkFRmYo_OcBcMNsY8WnI82TCILaZfgcVBCQw23WlXOFZQ"
		}
## JSON's con entradas y salidas para cada operación

	localhost:9914/operation/quotation-v3/${info.version}/Instancia

## JSON entrada - Body

### Headers
	Content-Type= application/json
	"Canal": "BTINTERNO",
	"Requerimiento": "1",
	"Usuario": "BTS_DUMMY",
	"Token": "[token obtenido metodo de introspection en paso anterior]",
	"Device": "device"	

## JSON Entrada o parámetros de entrada.

	URL localhost:9914/operation/quotation-v3/${info.version}/Instancia

	{
	
	}

## Respuesta (respuesta del servicio)

	{
	    "success": true,
		"return": {
			"code": "200",
			"message": "Respuesta exitosa"
		},
		"body": {
			"IntanciaDetalle": {
				"analistaPOLODescripcion": "",
				"operacion": 119855.0,
				"analistaPOLO": "Analista Automático",
				"valorCuota": 696256.0,
				"empleadoForum": "N",
				"montoAdicionales": 0.0,
				"camada": "",
				"fechaConstitucion": "0000-00-00",
				"auxiliarCaracter1": "",
				"sucursalDescripcion": "AUTOMOTORA VALP",
				"usuarioDesbloqueoDescripcion": "",
				"marcaRelacionada": "",
				"modeloID": 31.0,
				"auxiliarCaracter3": "",
				"telefono": "56912345678",
				"auxiliarCaracter2": "",
				"auxiliarCaracter5": "",
				"auxiliarCaracter4": "",
				"tipoDocumentoClienteDescripcion": "R.U.T.",
				"estadoVehiculo": 1.0,
				"numeroContrato": "",
				"mensualidad": 0.0,
				"paisDocumentoClienteDescripcion": "CHILE",
				"fechaVencimientoCredito": "0000-00-00",
				"regionDescripcion": "",
				"tipoDocumentoCliente": 2.0,
				"vendedorSuplenteDescripcion": "",
				"precioVenta": 2.0E7,
				"correoElectronico": "m@correo.com",
				"empresaCreditoDescripcion": "FORUM SERVICIOS FINANCIEROS",
				"modulo": 101.0,
				"tipoClienteDescripcion": "Persona",
				"paisDocumentoCliente": 152.0,
				"vendedorSuplente": "",
				"apellidoPaterno": "MUÑOZ",
				"fechaNacimiento": "1969-06-16",
				"cantidadVehiculo": 1.0,
				"califiDescripcion": "APROBADA",
				"sucursalDistribuidorDescripcion": "AUTOMOTORA VALP",
				"cumpleCompraInst": "NO",
				"sucursalDistribuidor": 707.0,
				"estadoVehiculoDescripcion": "Nuevo",
				"subOperacion": 0.0,
				"usuarioBloqueoDescripcion": "",
				"porcentajePreEvaluacion": 0.0,
				"auxiliarVarchar5": "       524 - En Curse",
				"auxiliarVarchar4": "S",
				"auxiliarVarchar3": "N",
				"auxiliarVarchar2": "N",
				"auxiliarFecha1": "0000-00-00",
				"valorUltimaCuotaVMG": 1.0E7,
				"auxiliarNumerico5": 0.0,
				"marcaID": 8.0,
				"montoCreditoTotal": 1.9421038E7,
				"empresaCliente": 1.0,
				"fechaCurse": "2021-06-30",
				"moneda": 0.0,
				"joinVenture": "N",
				"canal": "FINEX",
				"auxiliarVarchar1": "KIA MOTORS",
				"auxiliarFecha2": "0000-00-00",
				"auxiliarFecha3": "0000-00-00",
				"auxiliarFecha4": "0000-00-00",
				"auxiliarFecha5": "0000-00-00",
				"tipoValorCuotaDescripcion": "CAPITAL/INTERES",
				"calificacionID": "A",
				"ejecutivo": "H513515",
				"auxiliarNumerico1": 16.0,
				"auxiliarNumerico2": 1.0,
				"auxiliarNumerico3": 0.0,
				"segmentoUso": 1.0,
				"auxiliarNumerico4": 0.0,
				"saldo": 1.9E7,
				"fechaDesbloqueo": "0000-00-00",
				"impuestoPagare": 155368.0,
				"distribuidor": 21.0,
				"usuarioDesbloqueo": "",
				"metodoPagoDescripcion": "PAC",
				"sucursal": 707.0,
				"ejecutivoSuplente": "",
				"montoPie": 1000000.0,
				"ejecutivoDescripcion": "MARCO LOBOS ARANEDA",
				"valorRTCpesos": 0.0,
				"numeroPagare": "1635901",
				"valorRTCuf": 0.0,
				"vendedorDescripcion": "Sebastian Zamorano Castro",
				"numeroMotor": "",
				"fechaPagare": "2021-06-30",
				"fechaUltimoMovimiento": "0000-00-00",
				"metodoPago": 1.0,
				"empresaClienteDescripcion": "FORUM SERVICIOS FINANCIEROS",
				"fechaEntrega": "0000-00-00",
				"usuarioConfirmacion": "",
				"fechaPrimerVencimiento": "2021-08-04",
				"observacion": "",
				"valorUF": 29709.83,
				"tipoValorCuota": "M",
				"empresaCredito": 1.0,
				"usuarioConfirmacionDescripcion": "",
				"sociedadConyugal": "",
				"tipoOperacionDescripcion": "CI PASAJERO",
				"fechaIngreso": "2021-06-30",
				"instanciaID": 16028.0,
				"numeroCuotas": 24.0,
				"region": 0.0,
				"gastosOperacionales": 265670.0,
				"marcaAltoRiesgo": "",
				"comisionDistribuidor": 0.0,
				"estadoOperacion": "PRECURSADA",
				"valorTasacion": 0.0,
				"versionDescripcion": "EX 1.2L 5MT ABS AC",
				"modeloDescripcion": "MORNING",
				"apellidoMaterno": "CÁRDENAS",
				"distribuidorDescripcion": "AUTOMOTORA VALP",
				"anioVehiculo": 2022.0,
				"versionID": 20.0,
				"usuarioBloqueo": "",
				"admiteCompraInst": "NO",
				"cuenta": 8561329.0,
				"numeroDocumentoCliente": "85613294",
				"moduloDescripcion": "COMPRA INTELIGENTE",
				"papel": 0.0,
				"nombreRazonSocial": "MARISOL DEL CARMEN",
				"segmentoUsoDescripcion": "Particular",
				"vendedor": "XH32470",
				"tipoOperacion": 1.0,
				"fechaPreCurse": "2021-06-30",
				"cuentaCliente": 0.0,
				"montoPreEvaluacion": 1880262.0,
				"tipoCliente": 1.0,
				"auxiliarImporte5": 0.0,
				"fechaBloqueo": "0000-00-00",
				"auxiliarImporte3": 0.0,
				"auxiliarImporte4": 1.19855E10,
				"tasaInteres": 1.68,
				"porcentajePie": 5.0,
				"auxiliarImporte1": 2.0,
				"auxiliarImporte2": 0.0
			}
		}
	}
