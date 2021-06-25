package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.Menu;
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
			String sql = "select cod_trabajador,nom_trabajador,ape_pat_trabajador,ape_mat_trabajador,direc_trabajador,dni_trabajador,desc_cargo,t.cod_cargo,correo "
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
				bean.setCod_cargo(rs.getInt(8));
				bean.setCorreo(rs.getString(9));
				
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
			String sql = "select cod_trabajador,nom_trabajador,ape_pat_trabajador,ape_mat_trabajador,direc_trabajador,dni_trabajador,desc_cargo,correo "
					+ "from tb_trabajador t inner join  tb_cargos c "
					+ "on t.cod_cargo = c.cod_cargo where c.cod_cargo = ?;";
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
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="insert into tb_trabajador values(null,?,?,?,?,?,?,?,?)"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getNom_trabajador());
			pstm.setString(2, bean.getApe_pat_trabajador());
			pstm.setString(3, bean.getApe_mat_trabajador());
			pstm.setString(4, bean.getDir_trabajador());
			pstm.setString(5, bean.getDni_trabajador());
			pstm.setInt(6, bean.getCod_cargo());
			pstm.setString(7, bean.getCorreo());
			pstm.setString(8, bean.getClave());
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
	public int actualizarTrabajador(Trabajador bean) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="update tb_trabajador set nom_trabajador=?,ape_pat_trabajador=?,ape_mat_trabajador=?,direc_trabajador=?,dni_trabajador=?,cod_cargo=?,correo=?,clave=? where cod_trabajador=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getNom_trabajador());
			pstm.setString(2, bean.getApe_pat_trabajador());
			pstm.setString(3, bean.getApe_mat_trabajador());
			pstm.setString(4, bean.getDir_trabajador());
			pstm.setString(5, bean.getDni_trabajador());
			pstm.setInt(6, bean.getCod_cargo());

			pstm.setString(7, bean.getCorreo());
			pstm.setString(8, bean.getClave());
			
			pstm.setInt(9, bean.getCod_trabajador());
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
	public int eliminarTrabajador(int cod_trabajador) {
		int salida=-1;
		Connection cn = null;
		PreparedStatement pstm = null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="delete from tb_trabajador where cod_trabajador=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setInt(1, cod_trabajador);
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
	public Trabajador inicarSesion(String login, String psw) {
		Trabajador bean = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConnMySQL.getConexion();
			String sql = "select cod_trabajador,nom_trabajador,ape_pat_trabajador,cod_cargo from tb_trabajador where correo = ? and clave = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, login);
			pstm.setString(2, psw);
			rs = pstm.executeQuery();
			if(rs.next()) {
				bean = new Trabajador();
				bean.setCod_trabajador(rs.getInt(1));
				bean.setNom_trabajador(rs.getString(2));
				bean.setApe_pat_trabajador(rs.getString(3));
				bean.setCod_cargo(rs.getInt(4));
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
		return bean;
	}

	@Override
	public List<Menu> getMenus(int cod_cargo) {
		List<Menu> lista = new ArrayList<Menu>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		try {
			conn = ConnMySQL.getConexion();
			String sql = "select a.cod_menu,m.des_menu,m.url_menu from tb_acceso a join tb_menu m on a.cod_menu = m.cod_menu where cod_cargo = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, cod_cargo);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Menu bean = new Menu();
				
				bean.setCod_menu(rs.getInt(1));
				bean.setDes_menu(rs.getString(2));
				bean.setUrl(rs.getString(3));

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
