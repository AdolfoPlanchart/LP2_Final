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

}
