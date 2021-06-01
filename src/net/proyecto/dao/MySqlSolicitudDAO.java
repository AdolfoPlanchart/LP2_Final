package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.Solicitud;
import net.proyecto.interfaz.SolicitudDao;
import net.proyecto.utils.ConnMySQL;

public class MySqlSolicitudDAO  implements SolicitudDao{

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
			String sql="insert into tb_solicitud values(null,?,?,?)"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getFecha());
			pstm.setString(2, bean.getDescripcion());
			pstm.setInt(3, bean.getCodigoTrabajador());
			
		
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
			String sql="update tb_solicitud set ? where cod_solicitud=?"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setString(1, bean.getFecha());
			pstm.setString(2, bean.getDescripcion());
			pstm.setInt(3, bean.getCodigoTrabajador());
			pstm.setInt(4, bean.getCodigo());
			
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
			String sql="delete from tb_solicitud where cod_solcitud=?"; 
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
			String sql="select *from tb_docente"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 (NO HAYYYYYY)
			
			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				//7 crear objeto de la clase Docente
				Solicitud bean=new Solicitud();
				//8
				pstm.setInt(1, bean.getCodigo());
				pstm.setString(2, bean.getFecha());
				pstm.setString(3, bean.getDescripcion());
				pstm.setInt(4, bean.getCodigoTrabajador());
				
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
	}


