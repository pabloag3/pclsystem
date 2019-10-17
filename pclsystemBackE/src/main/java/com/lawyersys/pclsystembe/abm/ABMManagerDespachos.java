/*
 */
package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Actuarios;
import com.lawyersys.pclsystembacke.entities.Ciudades;
import com.lawyersys.pclsystembacke.entities.Departamentos;
import com.lawyersys.pclsystembacke.entities.Despachos;
import com.lawyersys.pclsystembacke.entities.Fueros;
import com.lawyersys.pclsystembacke.entities.Jueces;
import com.lawyersys.pclsystembacke.entities.Ujieres;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Pablo Aguilar
 */
@Stateless(name="ABMManagerDespachos")
public class ABMManagerDespachos {
    
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos
    
    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    }
    
    public List<Object> find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "Departamentos") {
            Query q = em.createNamedQuery(entidad + ".findByCodDepartamento")
                    .setParameter("codDepartamento", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Ciudades") {
            Query q = em.createNamedQuery(entidad + ".findByCodCiudad")
                    .setParameter("codCiudad", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Actuarios") {
            Query q = em.createNamedQuery(entidad + ".findByCodActuario")
                    .setParameter("codActuario", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Fueros") {
            Query q = em.createNamedQuery(entidad + ".findByCodFuero")
                    .setParameter("codFuero", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Jueces") {
            Query q = em.createNamedQuery(entidad + ".findByCodJuez")
                    .setParameter("codJuez", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Ujieres") {
            Query q = em.createNamedQuery(entidad + ".findByCodUjier")
                    .setParameter("codUjier", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Despachos") {
            Query q = em.createNamedQuery(entidad + ".findByCodDespacho")
                    .setParameter("codDespacho", Integer.parseInt(id));
            return q.getResultList();
        }
        return elem;
    }
    
    public List<Object> buscarDespachoPorDepartamentoFuero(String codDepartamento, String codFuero) {
        Query q = em.createNamedQuery("Despachos.findByDepartamentoFuero")
                .setParameter("codDepartamento", Integer.parseInt(codDepartamento))
                .setParameter("codFuero", Integer.parseInt(codFuero));
        return q.getResultList();
    }

    public <S> void create(Class<S> clazz, S elem) throws SQLException, Exception {
        if (clazz == Departamentos.class) {
            Departamentos ta = (Departamentos) elem;
            em.persist(ta);
        } else if (clazz == Ciudades.class) {
            Ciudades ta = (Ciudades) elem;
            em.persist(ta);
        } else if (clazz == Actuarios.class) {
            Actuarios ta = (Actuarios) elem;
            em.persist(ta);
        } else if (clazz == Fueros.class) {
            Fueros ta = (Fueros) elem;
            em.persist(ta);
        } else if (clazz == Jueces.class) {
            Jueces ta = (Jueces) elem;
            em.persist(ta);
        } else if (clazz == Ujieres.class) {
            Ujieres ta = (Ujieres) elem;
            em.persist(ta);
        } else if (clazz == Despachos.class) {
            Despachos ta = (Despachos) elem;
            em.persist(ta);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) throws SQLException, Exception {
        if (clazz == Departamentos.class) {
            Departamentos ta = (Departamentos) elem;
            em.merge(ta);
        } else if (clazz == Ciudades.class) {
            Ciudades ta = (Ciudades) elem;
            em.merge(ta);
        } else if (clazz == Actuarios.class) {
            Actuarios ta = (Actuarios) elem;
            em.merge(ta);
        } else if (clazz == Fueros.class) {
            Fueros ta = (Fueros) elem;
            em.merge(ta);
        } else if (clazz == Jueces.class) {
            Jueces ta = (Jueces) elem;
            em.merge(ta);
        } else if (clazz == Ujieres.class) {
            Ujieres ta = (Ujieres) elem;
            em.merge(ta);
        } else if (clazz == Despachos.class) {
            Despachos ta = (Despachos) elem;
            em.merge(ta);
        }        
    }
    
}
