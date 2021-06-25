package net.proyecto.entidad;

public class Expediente {
	private int cod_expediente, cod_trabajador;
	private String fech_expediente;
    private String desc_expediente;
    private String estado_expediente;
	public int getCod_expediente() {
		return cod_expediente;
	}
	public void setCod_expediente(int cod_expediente) {
		this.cod_expediente = cod_expediente;
	}
	public int getCod_trabajador() {
		return cod_trabajador;
	}
	public void setCod_trabajador(int cod_trabajador) {
		this.cod_trabajador = cod_trabajador;
	}
	public String getFech_expediente() {
		return fech_expediente;
	}
	public void setFech_expediente(String fech_expediente) {
		this.fech_expediente = fech_expediente;
	}
	public String getDesc_expediente() {
		return desc_expediente;
	}
	public void setDesc_expediente(String desc_expediente) {
		this.desc_expediente = desc_expediente;
	}
	public String getEstado_expediente() {
		return estado_expediente;
	}
	public void setEstado_expediente(String estado_expediente) {
		this.estado_expediente = estado_expediente;
	}
    
    
    
}
