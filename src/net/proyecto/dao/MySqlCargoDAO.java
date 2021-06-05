package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.Cargo;
import net.proyecto.entidad.Trabajador;
import net.proyecto.interfaz.CargoDAO;
import net.proyecto.utils.ConnMySQL;

public class MySqlCargoDAO implements CargoDAO {

	@Override
	public List<Cargo> listarCargos() {
		List<Cargo> lista = new ArrayList<Cargo>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConnMySQL.getConexion();
			String sql = "select * from tb_cargos;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Cargo bean = new Cargo();
				
				bean.setCod_cargo(rs.getInt(1));
				bean.setDesc_cargo(rs.getString(2));
				
				lista.add(bean);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(conn!=null) conn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public int registrarCargo(Cargo bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="insert into tb_cargos values(null,?)"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getDesc_cargo());
			//5
			salida=pstm.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}		
		return salida;
	}

	@Override
	public int actualizarCargo(Cargo bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="update tb_cargos set desc_cargo=? where cod_cargo=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getDesc_cargo());
			pstm.setInt(2, bean.getCod_cargo());
			//5
			salida=pstm.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}		
		return salida;
	}

	@Override
	public int eliminarCargo(int cod_cargo) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="delete from tb_cargos where cod_cargo=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setInt(1, cod_cargo);
			//5
			salida=pstm.executeUpdate();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}		
		return salida;
	}

}
