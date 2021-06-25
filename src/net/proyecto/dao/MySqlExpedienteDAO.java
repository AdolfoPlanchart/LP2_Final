package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.proyecto.entidad.ExpedienteGastos;
import net.proyecto.entidad.Solicitud;
import net.proyecto.entidad.SolicitudxTrabajador;
import net.proyecto.interfaz.ExpedienteDAO;
import net.proyecto.utils.ConnMySQL;

public class MySqlExpedienteDAO implements ExpedienteDAO {

	@Override
	public int save(ExpedienteGastos bean) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="insert into tb_expediente_gastos values(null,?,?,?,?)"; 
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setInt(1, bean.getCodigoSolicitud());
			pstm.setString(2, bean.getFecha());
			pstm.setString(3, bean.getDescripcion());
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
	public int update(ExpedienteGastos bean) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="update tb_expediente_gastos set cod_solicitud=?,fech_expediente=?,desc_expediente=?,estado=? where cod_expediente=?";
			//3
			pstm=cn.prepareStatement(sql);
			//4 
			pstm.setInt(1, bean.getCodigoSolicitud());
			pstm.setString(2, bean.getFecha());
			pstm.setString(3, bean.getDescripcion());
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
	public int delete(int cod) {
		// TODO Auto-generated method stub
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="delete from tb_expediente_gastos where cod_expediente=?"; 
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
		return null;
	}

	@Override
	public List<ExpedienteGastos> listAll() {
		// TODO Auto-generated method stub
		List<ExpedienteGastos> lista=new ArrayList<ExpedienteGastos>();
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			//1
			cn=ConnMySQL.getConexion();
			//2
			String sql="select * from tb_expediente_gastos"; 
			pstm=cn.prepareStatement(sql);
			//4 (NO HAYYYYYY)

			//5
			rs=pstm.executeQuery();
			//6 while
			while(rs.next()) {
				//7 crear objeto de la clase Docente
				ExpedienteGastos bean=new ExpedienteGastos();
				//8
				System.out.println(rs.getInt(2));
				
				bean.setCodigo(rs.getInt(1));
				bean.setCodigoSolicitud(rs.getInt(2));
				bean.setFecha(rs.getString(3));
				bean.setDescripcion(rs.getString(4));
				bean.setEstado(rs.getString(5));
				
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
