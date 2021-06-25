package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.Solicitud;
import net.proyecto.entidad.SolicitudxTrabajador;
import net.proyecto.interfaz.SolicitudDAO;
import net.proyecto.utils.ConnMySQL;

public class MySqlSolicitudDAO  implements SolicitudDAO{

	@Override
	public int save(Solicitud bean) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="insert into tb_solicitud values(null,?,?,?,?)"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getFecha());
			pstm.setString(2, bean.getDescripcion());
			pstm.setInt(3, bean.getCodigoTrabajador());
			pstm.setString(4, bean.getEstado());
			
		
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
	public int update(Solicitud bean) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="update tb_solicitud set fech_solicitud=?,desc_solicitud=?,cod_trabajador=?,estado=? where cod_solicitud=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getFecha());
			pstm.setString(2, bean.getDescripcion());
			pstm.setInt(3, bean.getCodigoTrabajador());
			pstm.setString(4, bean.getEstado());
			pstm.setInt(5, bean.getCodigo());
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
	public int delete(int cod) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="delete from tb_solicitud where cod_solicitud=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setInt(1, cod);
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
	public List<SolicitudxTrabajador> listAll(int numPisos) {
		// TODO Auto-generated method stub
		List<SolicitudxTrabajador> lista=new ArrayList<SolicitudxTrabajador>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="select cod_solicitud,fech_solicitud,desc_solicitud as'Descripcion',estado,nom_trabajador as'Trabajador' "
					   + "from tb_solicitud c inner join tb_trabajador t "
					   + "on c.cod_trabajador=t.cod_trabajador where cod_solicitud>=? "; 
			pstm=cn.prepareStatement(sql);
			//4 (NO HAYYYYYY)
			pstm.setInt(1, numPisos);
			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				//7 crear objeto de la clase Docente
				SolicitudxTrabajador bean=new SolicitudxTrabajador();
				//8
				bean.setCodigo(rs.getInt(1));
				bean.setFecha(rs.getString(2));
				bean.setDescripcion(rs.getString(3));
				bean.setEstado(rs.getString(4));
				bean.setTrabajador(rs.getString(5));
				
				//9
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
	public List<Solicitud> listAll() {
		// TODO Auto-generated method stub
		
		List<Solicitud> lista=new ArrayList<Solicitud>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="select * from tb_solicitud"; 
			pstm=cn.prepareStatement(sql);
			//4 (NO HAYYYYYY)

			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				Solicitud bean=new Solicitud();
				
				bean.setCodigo(rs.getInt(1));
				bean.setFecha(rs.getString(2));
				bean.setDescripcion(rs.getString(3));
				bean.setCodigoTrabajador(rs.getInt(4));
				bean.setEstado(rs.getString(5));
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

	}


