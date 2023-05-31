package cl.forum.arq.errornegocio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorNegocioDetailResponse {

	@JsonProperty("Codigo")
	public String codigo;

	@JsonProperty("Severidad")
	public String severidad;

	@JsonProperty("Descripcion")
	public String descripcion;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSeveridad() {
		return severidad;
	}

	public void setSeveridad(String severidad) {
		this.severidad = severidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}