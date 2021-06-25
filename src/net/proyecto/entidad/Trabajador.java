package net.proyecto.entidad;

public class Trabajador {
	private int cod_trabajador;
	private int cod_cargo;
	private String nom_trabajador;
	private String ape_pat_trabajador;
	private String ape_mat_trabajador;
	private String dir_trabajador;
	private String dni_trabajador;
	private String cargo;
	private String correo;
	private String clave;
	
	public int getCod_cargo() {
		return cod_cargo;
	}
	public void setCod_cargo(int cod_cargo) {
		this.cod_cargo = cod_cargo;
	}
	public int getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(int cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public String getNom_trabajador() {
		return nom_trabajador;
	}
	public void setNom_trabajador(String nom_trabajador) {
		this.nom_trabajador = nom_trabajador;
	}
	public String getApe_pat_trabajador() {
		return ape_pat_trabajador;
	}
	public void setApe_pat_trabajador(String ape_pat_trabajador) {
		this.ape_pat_trabajador = ape_pat_trabajador;
	}
	public String getApe_mat_trabajador() {
		return ape_mat_trabajador;
	}
	public void setApe_mat_trabajador(String ape_mat_trabajador) {
		this.ape_mat_trabajador = ape_mat_trabajador;
	}
	public String getDir_trabajador() {
		return dir_trabajador;
	}
	public void setDir_trabajador(String dir_trabajador) {
		this.dir_trabajador = dir_trabajador;
	}
	public String getDni_trabajador() {
		return dni_trabajador;
	}
	public void setDni_trabajador(String dni_trabajador) {
		this.dni_trabajador = dni_trabajador;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
}
