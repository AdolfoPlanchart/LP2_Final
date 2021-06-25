package net.proyecto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import net.proyecto.entidad.Detalle;
import net.proyecto.entidad.GenerarProyecto;
import net.proyecto.interfaz.GenerarProyectoDAO;
import net.proyecto.utils.ConnMySQL;

public class MySqlGenerarProyectoDAO implements GenerarProyectoDAO {

	@Override
	public int registrarProyecto(GenerarProyecto bean, List<Detalle> lista) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstmDetalle=null;
		try {
			//1
			try {
				cn=ConnMySQL.getConexion();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			//2 anular el commit del metodo exectupdate
			cn.setAutoCommit(false);
			
			//3
			String sql="insert into tb_Proyecto_Resolu values(null,?,?,?,?,?)"; 
			//3
			pstm=cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			//4 
			pstm.setInt(1, bean.getCod_resolu());
			pstm.setString(2, bean.getDes_resolu());
			pstm.setString(3, bean.getFech_resolu());
			pstm.setString(4, bean.getDocumentos_Resolu());
			pstm.setInt(5, bean.getCod_trabajador());
			pstm.setString(6,bean.getCargo_resolu());
			//5
			salida=pstm.executeUpdate();
			//6 obtener el codigo de proyecto generado
			ResultSet rs=pstm.getGeneratedKeys();
			//mover el cursor
			rs.next();
			int cod;
			cod=rs.getInt(1);
			//detalle
			//1. sentencia sql para detalle
			String sqlDet="insert into tb_detalle values(?,?)";
			//2.- recorrido para realizar sobre lista
			for(Detalle det:lista) {
				//3 crear objeto pstmDetlle
				pstmDetalle=cn.prepareStatement(sqlDet);
				//4. parametros
				pstmDetalle.setInt(1, det.getCod_resolu());
				pstmDetalle.setString(2,det.getDesc_expediente());
				//5ejecutar
				salida+=pstmDetalle.executeUpdate();
			}
			
			
			//confirmar transaccion "los insert"
			cn.commit();
			
		} catch (SQLException e) {
			//revertir todos los inserts
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
