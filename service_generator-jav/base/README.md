&&&&&&artifactId&&&&&&
====

&&&&&&artefact_description&&&&&&

- - -
	
Listado de operaciones y su descripción

1. /&&&&&&endpoint&&&&&&${info.version}/&&&&&&parametros&&&&&&			&&&&&&tipometodo&&&&&&	:	&&&&&&artefact_description&&&&&&


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

	&&&&&&endpoint&&&&&&${info.version}/&&&&&&parametros&&&&&&

## JSON entrada - Body

### Headers
	Content-Type= application/json
	"Canal": "BTINTERNO",
	"Requerimiento": "1",
	"Usuario": "BTS_DUMMY",
	"Token": "[token obtenido metodo de introspection en paso anterior]",
	"Device": "device"	

## JSON Entrada o parámetros de entrada.

	URL &&&&&&endpoint&&&&&&${info.version}/&&&&&&parametros&&&&&&

	{
	
	}

## Respuesta (respuesta del servicio)

	{
	
	}