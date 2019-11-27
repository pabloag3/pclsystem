package com.lawyersys.pclsystembe.abm;

import com.lawyersys.pclsystembacke.entities.Actuarios;
import com.lawyersys.pclsystembacke.entities.Ciudades;
import com.lawyersys.pclsystembacke.entities.Departamentos;
import com.lawyersys.pclsystembacke.entities.Despachos;
import com.lawyersys.pclsystembacke.entities.Fueros;
import com.lawyersys.pclsystembacke.entities.Jueces;
import com.lawyersys.pclsystembacke.entities.Ujieres;
import com.lawyersys.pclsystembe.dtos.ExpedientesPorFueroDTO;
import com.lawyersys.pclsystembe.dtos.GastoPorFueroPorMesDTO;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    public List<ExpedientesPorFueroDTO> cantExpedientesPorFuero() {
        
        List<ExpedientesPorFueroDTO> listaFinal = new ArrayList<ExpedientesPorFueroDTO>();
        
        List<Object[]> resultado = new ArrayList<Object[]>();
        
        Query q = em.createNativeQuery("SELECT f.cod_fuero, f.tipo_fuero, count(e.*)\n"
                + "FROM fueros f\n"
                + "JOIN despachos d ON d.cod_fuero = f.cod_fuero\n"
                + "JOIN expedientes e ON e.cod_despacho = d.cod_despacho\n"
                + "GROUP BY f.cod_fuero, f.tipo_fuero;");
        resultado = (List<Object[]>)q.getResultList();
        
        if (resultado == null || resultado.isEmpty()) {
            listaFinal = new ArrayList<ExpedientesPorFueroDTO>();
        }
        else {
            for (Object[] linea : resultado) {
                ExpedientesPorFueroDTO epf;
                int codFuero = Integer.parseInt(linea[0].toString());
                String tipoFuero = linea[1].toString();
                int cantExpedientes = Integer.parseInt(linea[2].toString());
                epf = new ExpedientesPorFueroDTO(codFuero, tipoFuero, cantExpedientes);
                listaFinal.add(epf);
            }
        }
        
        return listaFinal;
        
    }
    
    public List<GastoPorFueroPorMesDTO> gastoPorFueroPorMes() {
        
        List<GastoPorFueroPorMesDTO> listaFinal = new ArrayList<GastoPorFueroPorMesDTO>();
        
        List<Object[]> resultado = new ArrayList<Object[]>();
        
        Query q = em.createNativeQuery("SELECT f.cod_fuero, f.tipo_fuero, \n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 1\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"ENERO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 2\n" +
            "		AND ff.cod_fuero = f.cod_fuero	\n" +
            "	) AS \"FEBRERO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 3\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"MARZO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 4\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"ABRIL\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 5\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"MAYO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 6\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"JUNIO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 7\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"JULIO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 8\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"AGOSTO\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 9\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"SEPTIEMBRE\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 10\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"OCTUBRE\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 11\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"NOVIEMBRE\",\n" +
            "	(SELECT COALESCE(sum(dc.monto), 0)\n" +
            "	FROM detalle_cuenta dc\n" +
            "	JOIN expedientes ex ON ex.cod_expediente = dc.cod_expediente\n" +
            "	JOIN despachos d ON d.cod_despacho = ex.cod_despacho\n" +
            "	JOIN fueros ff ON ff.cod_fuero = d.cod_fuero\n" +
            "	WHERE EXTRACT (MONTH FROM dc.fecha) = 12\n" +
            "		AND ff.cod_fuero = f.cod_fuero\n" +
            "	) AS \"DICIEMBRE\"	\n" +
            "FROM fueros f\n" +
            "JOIN despachos des ON des.cod_fuero = f.cod_fuero\n" +
            "JOIN expedientes ex ON ex.cod_despacho = des.cod_despacho\n" +
            "JOIN detalle_cuenta dc ON dc.cod_expediente = ex.cod_expediente\n" +
            "GROUP BY f.cod_fuero, f.tipo_fuero\n" +
            ";");
        resultado = (List<Object[]>)q.getResultList();
       
        if (resultado == null || resultado.isEmpty()) {
            listaFinal = new ArrayList<GastoPorFueroPorMesDTO>();
        }
        else {
            
            for (Object[] linea : resultado) {

                GastoPorFueroPorMesDTO gpfpm;
                
                int codFuero = Integer.parseInt(linea[0].toString());
                String tipoFuero = linea[1].toString();
                int enero = Integer.parseInt(linea[2].toString());
                int febrero = Integer.parseInt(linea[3].toString());
                int marzo = Integer.parseInt(linea[4].toString());
                int abril = Integer.parseInt(linea[5].toString());
                int mayo = Integer.parseInt(linea[6].toString());
                int junio = Integer.parseInt(linea[7].toString());
                int julio = Integer.parseInt(linea[8].toString());
                int agosto = Integer.parseInt(linea[9].toString());
                int septiembre = Integer.parseInt(linea[10].toString());
                int octubre = Integer.parseInt(linea[11].toString());
                int noviembre = Integer.parseInt(linea[12].toString());
                int diciembre = Integer.parseInt(linea[13].toString());
                
                gpfpm = new GastoPorFueroPorMesDTO(codFuero, tipoFuero, enero, febrero, marzo, abril, mayo, junio, julio, agosto, septiembre, octubre, noviembre, diciembre);
                listaFinal.add(gpfpm);
            }
        }
        
        return listaFinal;
        
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
