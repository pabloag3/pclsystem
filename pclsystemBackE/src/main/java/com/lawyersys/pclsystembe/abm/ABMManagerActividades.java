package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Actividades;
import com.lawyersys.pclsystembacke.entities.EstadosActividades;
import com.lawyersys.pclsystembacke.entities.TiposActividades;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tatoa
 */
@Stateless(name="ABMManagerActividades")
public class ABMManagerActividades {
    
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos

    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    } 
   
    public List<Object> traerPendienteCaducadoDelEmpleado(String idEmpleado) {

        Query q = em.createNativeQuery("SELECT *" +
                " FROM actividades a" +
                " WHERE (a.cod_estado = 4" +
                " 	OR  ( (a.cod_estado = 1) AND (DATE(now()) - DATE(a.fecha)) <= a.dia_notificable))" +
                " 	AND a.cedula_responsable = (?1);", Actividades.class);
        q.setParameter(1, idEmpleado);
        return q.getResultList();
    }
    
    public List<Object> traerPendientesCaducados() {

        Query q = em.createNativeQuery("SELECT *" +
                " FROM actividades a" +
                " WHERE (a.cod_estado = 4" +
                " 	OR  ( (a.cod_estado = 1) AND (DATE(now()) - DATE(a.fecha)) <= a.dia_notificable))" +
                " 	;", Actividades.class);
        return q.getResultList();
    }
    
    public List<Object> find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "TiposActividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodTipoActividad")
                    .setParameter("codTipoActividad", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "EstadosActividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodEstado")
                    .setParameter("codEstado", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Actividades") {
            Query q = em.createNamedQuery(entidad + ".findByCodActividad")
                    .setParameter("codActividad", Integer.parseInt(id));
            return q.getResultList();
        }
        return elem;
    }

    public <S> void create(Class<S> clazz, S elem) throws SQLException, Exception {
        if (clazz == TiposActividades.class) {
            TiposActividades ta = (TiposActividades) elem;
            em.persist(ta);
        } else if (clazz == EstadosActividades.class) {
            EstadosActividades ta = (EstadosActividades) elem;
            em.persist(ta);
        } else if (clazz == Actividades.class) {
            Actividades ta = (Actividades) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) throws SQLException, Exception {
        if (clazz == TiposActividades.class) {
            TiposActividades ta = (TiposActividades) elem;
            em.merge(ta);
        } else if (clazz == EstadosActividades.class) {
            EstadosActividades ta = (EstadosActividades) elem;
            em.merge(ta);
        } else if (clazz == Actividades.class) {
            Actividades ta = (Actividades) elem;
            em.merge(ta);
        }
    }
    
}
