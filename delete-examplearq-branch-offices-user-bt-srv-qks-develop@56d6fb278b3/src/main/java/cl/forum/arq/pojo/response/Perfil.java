package cl.forum.arq.pojo.response;

public class Perfil {
	private String cPerfilBT;

	public Perfil() {
		super();
	}

	public Perfil(String perfil) {
		this.cPerfilBT = perfil;
	}

	public void setCPerfil(String perfil) {
		this.cPerfilBT = perfil;
	}

	public String getCPerfil() {
		return this.cPerfilBT;
	}

}
