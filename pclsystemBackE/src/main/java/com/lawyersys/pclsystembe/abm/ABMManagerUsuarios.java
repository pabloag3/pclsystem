package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Empleados;
import com.lawyersys.pclsystembacke.entities.EstadosUsuarios;
import com.lawyersys.pclsystembacke.entities.RolesPermisos;
import com.lawyersys.pclsystembacke.entities.RolesUsuario;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author tatoa
 */
@Stateless(name="ABMManagerUsuarios")
public class ABMManagerUsuarios {
    
    @PersistenceContext(unitName = "lawyersys")
    EntityManager em; // Para ejecutar queries en la base de datos

    public List<Object> findAll(String entidad) {
        Query q = em.createNamedQuery(entidad + ".findAll");
        return q.getResultList();
    }
    
    public Object find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "Usuarios") {
            Query q = em.createNamedQuery(entidad + ".findByUsuario")
                    .setParameter("usuario", id);
            return q.getSingleResult();
        } else if (entidad == "Empleados") {
            Query q = em.createNamedQuery(entidad + ".findByCedula")
                    .setParameter("cedula", id);
            return q.getSingleResult();
        } else if (entidad == "RolesUsuario") {
            Query q = em.createNamedQuery(entidad + ".findByCodRol")
                    .setParameter("codRol", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "EstadosUsuarios") {
            Query q = em.createNamedQuery(entidad + ".findByCodEstado")
                    .setParameter("codEstado", Integer.parseInt(id));
            return q.getSingleResult();
        } else if (entidad == "Permisos") {
            Query q = em.createNamedQuery(entidad + ".findByCodPermiso")
                    .setParameter("codPermiso", Integer.parseInt(id));
            return q.getSingleResult();
        }
        return elem;
    }

    public <S> void create(Class<S> clazz, S elem) {
        if (clazz == Usuarios.class) {
            Usuarios usu = (Usuarios) elem;
            em.persist(usu);
        } else if (clazz == Empleados.class) {
            Empleados emp = (Empleados) elem;
            em.persist(emp);
        } else if (clazz == EstadosUsuarios.class) {
            EstadosUsuarios est = (EstadosUsuarios) elem;
            em.persist(est);
        } else if (clazz == RolesUsuario.class) {
            RolesUsuario rol = (RolesUsuario) elem;
            em.persist(rol);
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == Usuarios.class) {
            Usuarios usu = (Usuarios) elem;
            em.merge(usu);
        } else if (clazz == Empleados.class) {
            Empleados emp = (Empleados) elem;
            em.merge(emp);
        } else if (clazz == EstadosUsuarios.class) {
            EstadosUsuarios est = (EstadosUsuarios) elem;
            em.merge(est);
        } else if (clazz == RolesUsuario.class) {
            RolesUsuario rol = (RolesUsuario) elem;
            em.merge(rol);
        }
    }

    public <S> void remove(Class<S> clazz, Integer id) {
        Query q;
        
        if (clazz == RolesUsuario.class) {
            // busca el objeto del rol a eliminar a partir del id
            q = em.createNamedQuery("RolesUsuario.findByCodRol");
            q.setParameter("codRol", id);
            RolesUsuario rol = (RolesUsuario) q.getSingleResult();
            
            // crea query para traer y contar los usuarios que poseen este rol
            q = em.createNamedQuery("Usuarios.findByCodRol")
                    .setParameter("codRol", rol.getCodRol());
            int usuario = q.getResultList().size();
            
            // si no hay usuarios con el rol
            if (usuario < 1) {
                q = em.createNamedQuery("RolesPermisos.findByCodRol");
                q.setParameter("codRol", rol.getCodRol());
                List<RolesPermisos> rp = q.getResultList();

                for(RolesPermisos per : rp){
                    em.remove(per);
                }

                em.remove(rol);
            }
        }
        
    }

    
    

    
}
