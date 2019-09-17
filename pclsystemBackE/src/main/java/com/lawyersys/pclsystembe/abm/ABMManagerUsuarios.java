package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Empleados;
import com.lawyersys.pclsystembacke.entities.EstadosEmpleados;
import com.lawyersys.pclsystembacke.entities.Permisos;
import com.lawyersys.pclsystembacke.entities.RolesPermisos;
import com.lawyersys.pclsystembacke.entities.RolesUsuario;
import com.lawyersys.pclsystembacke.entities.Usuarios;
import com.lawyersys.pclsystembe.dtos.RolesPermisosDTO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public List<Object> findPermisosByRol(int rol) {
//        Query q = em.createNamedQuery("Permisos.findByCodRol")
//                .setParameter("codRol", rol);
        Query q = em.createNativeQuery("SELECT p.cod_permiso, p.descripcion\n" 
                + "FROM public.permisos p\n"
                + "JOIN roles_permisos rp ON rp.cod_permiso = p.cod_permiso\n"
                + "WHERE rp.cod_rol = (?1);");
        q.setParameter(1, rol);
        return q.getResultList();
    }
    
    public List<Object> find(String entidad, String id) {
        List<Object> elem = null;
        if (entidad == "Usuarios") {
            Query q = em.createNamedQuery(entidad + ".findByUsuario")
                    .setParameter("usuario", id);
            return q.getResultList();
        } else if (entidad == "Empleados") {
            Query q = em.createNamedQuery(entidad + ".findByCedula")
                    .setParameter("cedula", id);
            return q.getResultList();
        } else if (entidad == "RolesUsuario") {
            Query q = em.createNamedQuery(entidad + ".findByCodRol")
                    .setParameter("codRol", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "EstadosEmpleados") {
            Query q = em.createNamedQuery(entidad + ".findByCodEstado")
                    .setParameter("codEstado", Integer.parseInt(id));
            return q.getResultList();
        } else if (entidad == "Permisos") {
            Query q = em.createNamedQuery(entidad + ".findByCodPermiso")
                    .setParameter("codPermiso", Integer.parseInt(id));
            return q.getResultList();
        }
        return elem;
    }
    
    public Object findByName(String entidad, String usuario) {
        List<Object> elem = null;
        if (entidad == "Usuarios") {
            Query q = em.createNamedQuery(entidad + ".findByUsuario")
                    .setParameter("usuario", usuario);
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
        } else if (clazz == EstadosEmpleados.class) {
            EstadosEmpleados est = (EstadosEmpleados) elem;
            em.persist(est);
        } else if (clazz == RolesPermisosDTO.class) {
            
            RolesPermisosDTO rpdto = (RolesPermisosDTO) elem;
            RolesUsuario rol = new RolesUsuario();
            rol.setDescripcion(rpdto.getDescripcion());
            em.persist(rol);
            System.out.println(rpdto);
            if (rpdto.getPermisos() != null) {
                
                for (int cod : rpdto.getPermisos()) {
                    Query q1;
                    
                    // crea el objeto permiso del codigo recibido
                    q1 = em.createNamedQuery("Permisos.findByCodPermiso");
                    Permisos per = (Permisos) q1.setParameter("codPermiso", cod).getSingleResult();
                    per.setCodPermiso(cod);
                    
                    //crea los rolesPermisos y seteamos rol y permiso
                    RolesPermisos rolPer = new RolesPermisos();
                    rolPer.setCodRol(rol);
                    rolPer.setCodPermiso(per);
                    
                    em.persist(rolPer);
                }
            }
            
        }
    }

    public <S> void edit(Class<S> clazz, S elem) {
        if (clazz == Usuarios.class) {
            Usuarios usu = (Usuarios) elem;
            em.merge(usu);
        } else if (clazz == Empleados.class) {
            Empleados emp = (Empleados) elem;
            em.merge(emp);
        } else if (clazz == EstadosEmpleados.class) {
            EstadosEmpleados est = (EstadosEmpleados) elem;
            em.merge(est);
        } else if (clazz == RolesPermisosDTO.class) {
            
            Query q;
            
            RolesPermisosDTO rpdto = (RolesPermisosDTO) elem;
            
            // traigo el objeto del rol cuyos permisos vamos a actualizar
            q = em.createNamedQuery("RolesUsuario.findByCodRol");
            q.setParameter("codRol", rpdto.getCodRol());
            RolesUsuario rol = (RolesUsuario) q.getSingleResult();
            
            // si la descripcion del rol cambia, se actualiza
            if (!rpdto.getDescripcion().equals(rol.getDescripcion())) {
                rol.setDescripcion(rpdto.getDescripcion());
                em.merge(rol);
            }
                        
            // traigo la lista de codigos de permisos del rol especifico
            q = em.createNativeQuery("SELECT p.cod_permiso FROM permisos p "
                    + "JOIN roles_permisos rp ON rp.cod_permiso = p.cod_permiso "
                    + "JOIN roles_usuario r ON r.cod_rol = rp.cod_rol "
                    + "WHERE r.cod_rol = " + rol.getCodRol());
            List<Integer> listaCodPer = q.getResultList(); //lista de codigos del rol especifico
            List<Integer> codPerActualizado = rpdto.getPermisos(); //lista de codigos del rol a actualizar
            
            // agregamos los nuevos permisos que no estan cargados actualmente
            for (Integer codPermisoDTO : codPerActualizado) {
                if (!listaCodPer.contains(codPermisoDTO)) {
                    // traigo el permiso
                    q = em.createNamedQuery("Permisos.findByCodPermiso");
                    q.setParameter("codPermiso", codPermisoDTO);
                    Permisos per = (Permisos) q.getSingleResult();
                    per.setCodPermiso(codPermisoDTO);
                    
                    // creo los rolesPermiso y seteamos el rol y permiso
                    RolesPermisos rolPer = new RolesPermisos();
                    rolPer.setCodRol(rol);
                    rolPer.setCodPermiso(per);
                    
                    em.persist(rolPer);
                }
            }

            //buscamos los que estan en la base de datos pero no en la lista actualizada
            for (Integer codPermisoBD : listaCodPer) {
                if (!codPerActualizado.contains(codPermisoBD)) {
                    
                    // traigo el objeto del permiso que no esta en la lista actualizada y hay que eliminar
                    q = em.createNamedQuery("Permisos.findByCodPermiso");
                    q.setParameter("codPermiso", codPermisoBD);
                    Permisos per = (Permisos) q.getSingleResult();
                    
                    // traigo los objetos RolesPermisos que tengan como atributos el rol y permiso a eliminar
                    q = em.createNamedQuery("RolesPermisos.findByRolPermiso");
                    q.setParameter("codRol", rol);
                    q.setParameter("codPermiso", per);
                    RolesPermisos rp = (RolesPermisos) q.getSingleResult();
                    
                    em.remove(rp);
                }
            }

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
