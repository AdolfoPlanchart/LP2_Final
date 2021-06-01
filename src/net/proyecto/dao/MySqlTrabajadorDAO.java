package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.Trabajador;
import net.proyecto.interfaz.TrabajadorDAO;
import net.proyecto.utils.ConnMySQL;

public class MySqlTrabajadorDAO implements TrabajadorDAO {

	@Override
	public List<Trabajador> listarTrabajadores() {
		List<Trabajador> lista = new ArrayList<Trabajador>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConnMySQL.getConexion();
			String sql = "select cod_trabajador,nom_trabajador,ape_pat_trabajador,ape_mat_trabajador,direc_trabajador,dni_trabajador,desc_cargo "
					+ "from tb_trabajador t inner join  tb_cargos c "
					+ "on t.cod_cargo = c.cod_cargo;";
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Trabajador bean = new Trabajador();
				
				bean.setCod_trabajador(rs.getInt(1));
				bean.setNom_trabajador(rs.getString(2));
				bean.setApe_pat_trabajador(rs.getString(3));
				bean.setApe_mat_trabajador(rs.getString(4));
				bean.setDir_trabajador(rs.getString(5));
				bean.setDni_trabajador(rs.getString(6));
				bean.setCargo(rs.getString(7));
				
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
	public List<Trabajador> listarTrabajadores(int cod_cargo) {
		List<Trabajador> lista = new ArrayList<Trabajador>();
		Connection cn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			cn = ConnMySQL.getConexion();
			String sql = "select cod_trabajador,nom_trabajador,ape_pat_trabajador,ape_mat_trabajador,direc_trabajador,dni_trabajador,desc_cargo "
					+ "from tb_trabajador t inner join  tb_cargos "
					+ "on t.cod_cargo = c.cod_cargo where cod_cargo = ?;";
			pstm = cn.prepareStatement(sql);
			pstm.setInt(1,cod_cargo);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Trabajador bean = new Trabajador();
				
				bean.setCod_trabajador(rs.getInt(1));
				bean.setNom_trabajador(rs.getString(2));
				bean.setApe_pat_trabajador(rs.getString(3));
				bean.setApe_mat_trabajador(rs.getString(4));
				bean.setDir_trabajador(rs.getString(5));
				bean.setDni_trabajador(rs.getString(6));
				bean.setCargo(rs.getString(7));
				
				lista.add(bean);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public int registrarTrabajador(Trabajador bean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int actualizarTrabajador(int cod_trabajador) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int eliminarTrabajador(int cod_trabajador) {
		// TODO Auto-generated method stub
		return 0;
	}

}
