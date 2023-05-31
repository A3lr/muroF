package cl.forum.arq.pojo.request;

public class Sucursales {

	private String usuario;
	private String[] sucursales;

	public Sucursales() {
		super();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String[] getSucursales() {
		return sucursales;
	}

	public void setSucursales(String[] sucursales) {
		this.sucursales = sucursales;
	}
}