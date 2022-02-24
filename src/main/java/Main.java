
import es.albarregas.modelo.Usuario;
import es.albarregas.modelo.Direccion;
import es.albarregas.modelo.Permiso;
import es.albarregas.modelo.HibernateUtil;
import es.albarregas.modelo.Permiso.Estado;
import es.albarregas.modelo.UsuarioDireccion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.query.Query;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jesus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new Main();
    }

    public Main() {
        // Inserciones
//        int id = insertDatos();

        // Producto cartesiano de dos entidades
        getProductoCartesiano();
        
        // Listado de nombres. Obtenemos el resultado como una lista de objetos
        getNombres();
        
        // Obtenemos el resultado como una lista de array de objetos. Obtenemos el nombre, password y direccion de cada usuario
        getNombresPasswordsYCalle();
        
        // Obtenemos el resultado como una lista de mapas parametrizado a String y Object
        getIdsNombresYPasswords();
        
        // Obtenemos el resultado como una lista de un nuevo bean UsuarioDireccion
        getUsuarioDireccion();

        // Listado de nombre y password utilizando list
        getNombrePasswordList();

        // Listado de usuarios de una dirección utilizando ROW
        getDireccionesRow();

        // Listado de usuarios de una dirección utilizando ROW y subconsultas
        getDireccionesRowSubconsulta();

        // Obtenemos el número de permisos como un Object con uniqueResult
        getNumeroPermisos();

        // Claúsula GROUP BY
        cuentaPermisos();

        // Colecciones
        getUsuariosNumPermisos();

        // Obtenemos el usuario que vive en la "Calle 1"
        getUsuarioCalle("Calle 1");

        // Obtenemos los usuarios que no tienen dirección asignada
        getUsuariosSinDireccion();

        // Obtenemos los usuarios que tienen permisos con estado PENDIENTE
        getUsuariosPendiente();

        // Obtenemos los usuarios que viven en una determinada calle y tiene permisos de lectura
        getUsuariosDirecLectura("Calle 1");

        // Lista de usuarios que tengan un permiso a activo con subconsultas
        getUsuariosPermisoActivo();

        // Listado de usuario y calle utilizando consultas nativas
        getUsuariosCalleSQL();

        // Usuario con nombre y código postal utilizando una consulta por nombre
        getNombreCp(1);

        // Paginación de los resultados. Obtenemos los permisos del 2 al 4 clasificados alfabéticamente
        getPermisosPaginados();

        System.exit(0);

    }

    private int insertDatos() {
        // Se obtiene la sesion
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction();

        Direccion direccion1 = new Direccion("Calle 1", "12345");
        Direccion direccion2 = new Direccion("Calle 2", "54321");
        Permiso permiso1 = new Permiso("Lectura de archivos", Estado.PENDIENTE);
        Permiso permiso2 = new Permiso("Creación de archivos", Estado.PENDIENTE);
        Permiso permiso3 = new Permiso("Eliminación de archivos", Estado.ACTIVO);
        Permiso permiso4 = new Permiso("Modificación de archivos", Estado.ACTIVO);
        Permiso permiso5 = new Permiso("Sin permisos", Estado.INACTIVO);
        List<Permiso> permisos1 = new ArrayList<>();
        permisos1.add(permiso1);
        permisos1.add(permiso2);
        Usuario usuario1 = new Usuario("usuario 1", "usur-1", "abcdefg", new Date(), direccion1, permisos1);
        List<Permiso> permisos2 = new ArrayList<>();
        permisos2.add(permiso3);
        permisos2.add(permiso4);
        Usuario usuario2 = new Usuario("usuario 2", "usur-2", "dabdser", new Date(), direccion2, permisos2);
        List<Permiso> permisos3 = new ArrayList<>();
        permisos3.add(permiso5);
        Usuario usuario3 = new Usuario("usuario 3", "usur-3", "pouebrf", new Date(), null, permisos3);
        // Almacenamos en la base de datos
        s.save(direccion1);
        s.save(direccion2);
        s.save(permiso1);
        s.save(permiso2);
        s.save(permiso3);
        s.save(permiso4);
        s.save(permiso5);
        s.save(usuario1);
        s.save(usuario2);
        s.save(usuario3);
//        s.save(vuelo1);
        s.getTransaction().commit();

        s.close();

        return permiso1.getId();

    }

    private void getProductoCartesiano() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u FROM Usuario AS u,Permiso as p";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<Usuario> listado = query.list();
            Iterator<Usuario> it = listado.iterator();
            System.out.println("\nPRODUCTO CARTESIANO DE DOS ENTIDADES");
            System.out.println("=====================================");
            while (it.hasNext()) {
                Usuario usuario = it.next();
                System.out.println("Nombre: " + usuario.getNombre());
            }
            s.getTransaction().commit();
        }
    }

    private void getNombres() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u.nombre FROM Usuario AS u";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<String> listado = query.list();
            Iterator<String> it = listado.iterator();
            System.out.println("\nLISTA DE LOS NOMBRES DE USUARIO");
            System.out.println("===============================");
            while (it.hasNext()) {
                System.out.println("Nombre: " + it.next());
            }
            s.getTransaction().commit();
        }
    }

    private void getNombresPasswordsYCalle() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u.nombre, u.password,u.direccion.calle FROM Usuario AS u";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<Object[]> listado = query.list();
            System.out.println("\nLISTA DE LOS NOMBRES Y PASSWORDS DE USUARIO");
            System.out.println("===========================================");
            for (Object[] array : listado) {
                for (int i = 0; i < array.length; i++) {
                    System.out.print(array[i] + " - ");
                }
                System.out.println();
            }
            s.getTransaction().commit();
        }

    }

    private void getIdsNombresYPasswords() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new map(u.id AS identificador, u.nombre AS nombre, u.password AS pass) FROM Usuario AS u";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<Map> listaResultados = query.list();
            System.out.println("\nLISTA DE LOS ID, NOMBRE Y PASSWORD DE LOS USUARIOS UTILIZANDO UN MAPA");
            System.out.println("=====================================================================");
            for (int i = 0; i < listaResultados.size(); i++) {
                Map mapa = listaResultados.get(i);
                System.out.println("Datos del mapa " + i);
                Set llaves = mapa.keySet();
                Iterator<String> it = llaves.iterator();
                while (it.hasNext()) {
                    String llave = it.next();
                    System.out.println("\t" + llave + "\t" + mapa.get(llave));
                }
            }
            s.getTransaction().commit();
        }
    }

    private void getUsuarioDireccion() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new es.albarregas.modelo.UsuarioDireccion(u.nombre, d.calle, d.codigoPostal, count(p)) FROM Usuario AS u "
                    + "LEFT OUTER JOIN u.direccion AS d LEFT OUTER JOIN u.permisos AS p GROUP BY u.id";
            s.beginTransaction();

            Query query = s.createQuery(hql);

            List<UsuarioDireccion> listaResultados = query.list();
            System.out.println("\nLISTADO DE USUARIOS UTILIZANDO UN BEAN AUXILIAR");
            System.out.println("===============================================");
            for (int i = 0; i < listaResultados.size(); i++) {
                UsuarioDireccion usuarioDireccion = listaResultados.get(i);
                System.out.println("* " + usuarioDireccion.getNombre() + ", dirección: " + usuarioDireccion.getCalle() + " - " + usuarioDireccion.getCodigoPostal() + ", permisos: " + usuarioDireccion.getNumeroPermisos());
            }

            s.getTransaction().commit();
        }

    }

    private void getNombrePasswordList() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT new list(u.nombre,u.password) FROM Usuario AS u";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<List> listado = query.list();

            System.out.println("\nLISTADO DE NOMBRE Y PASSWORD UTILIZANDO LIST");
            System.out.println("=============================================");
            for (int i = 0; i < listado.size(); i++) {
                System.out.println("Nombre: " + listado.get(i).get(0) + " password: " + listado.get(0).get(1));
            }
            s.getTransaction().commit();
        }
    }

    private void getDireccionesRow() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u.nombre FROM Usuario AS u WHERE (u.direccion.calle,u.direccion.codigoPostal) = ('Calle 1','12345')";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            String usuario = (String) query.uniqueResult();

            System.out.println("\nLISTADO DE NOMBRE UTILIZANDO ROW");
            System.out.println("=================================");

            System.out.println("Nombre: " + usuario);

            s.getTransaction().commit();
        }
    }

    private void getDireccionesRowSubconsulta() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT u.nombre FROM Usuario AS u WHERE (u.direccion.calle,u.direccion.codigoPostal) = "
                    + "(select d.calle,d.codigoPostal from Direccion as d where d.id=2)";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            String usuario = (String) query.uniqueResult();

            System.out.println("\nLISTADO DE NOMBRE UTILIZANDO ROW Y SUBCONSULTAS");
            System.out.println("================================================");

            System.out.println("Nombre: " + usuario);

            s.getTransaction().commit();
        }
    }
    
    private void getNumeroPermisos() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT COUNT(*) FROM Permiso";
            s.beginTransaction();

            Query query = s.createQuery(hql);
            Long numero = (Long) (query.uniqueResult());
            System.out.println("\nNÚMERO DE PERMISOS TOTALES");
            System.out.println("==========================");
            System.out.println("Número de permisos: " + numero);
            s.getTransaction().commit();
        }
    }

    private void cuentaPermisos() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "SELECT p.estado, COUNT(p.estado) FROM Permiso AS p GROUP BY p.estado";

            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<Object[]> datos = query.list();
            System.out.println("\nNÚMERO DE PERMISOS POR CADA ESTADO");
            System.out.println("==================================");
            for (int i = 0; i < datos.size(); i++) {
                Object[] datoActual = datos.get(i);

                System.out.println(datoActual[0] + "(" + datoActual[1] + ")");
            }

            s.getTransaction().commit();
        }
    }

    private void getUsuariosNumPermisos() {
        String hql = "SELECT u.nombre,SIZE(u.permisos) FROM Usuario AS u GROUP BY u.nombre";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);
            List<Object[]> listaResultados = query.list();
            System.out.println("\nDATOS DEL USUARIOS Y NÚMERO DE PERMISOS QUE POSEE");
            System.out.println("=================================================");
            for (int i = 0; i < listaResultados.size(); i++) {
                Object[] resultado = listaResultados.get(i);
                System.out.println("Usuario: " + resultado[0] + " - " + resultado[1]);
            }
            s.getTransaction().commit();
        }
    }

    private void getUsuarioCalle(String calleEnt) {
        String hql = "SELECT u.nombre,u.direccion.calle FROM Usuario AS u WHERE u.direccion.calle = :calle";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);
            query.setParameter("calle", calleEnt);
            List<Object[]> listaResultados = query.list();
            System.out.println("\nDATOS DEL USUARIOS Y LAS CALLES DONDE VIVEN");
            System.out.println("===========================================");
            for (int i = 0; i < listaResultados.size(); i++) {
                Object[] resultado = listaResultados.get(i);
                System.out.println("Usuario: " + resultado[0] + " - " + resultado[1]);
            }
            s.getTransaction().commit();
        }
    }

    private void getUsuariosSinDireccion() {
        String hql = "SELECT u FROM Usuario AS u LEFT JOIN u.direccion AS d WHERE d.id IS NULL";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);

            List<Usuario> usuarios = query.list();
            System.out.println("\nUSUARIOS SIN DIRECCIÓN");
            System.out.println("======================");
            Iterator<Usuario> itUsuario = usuarios.iterator();
            while (itUsuario.hasNext()) {
                Usuario usuario = itUsuario.next();
                System.out.println("Usuario: " + usuario.getNombre());
            }

            s.getTransaction().commit();
        }
    }

    private void getUsuariosPendiente() {
        String hql = "SELECT u FROM Usuario AS u INNER JOIN u.permisos AS p WHERE p.estado = :estado";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);
            query.setParameter("estado", Permiso.Estado.PENDIENTE);
            List<Usuario> usuarios = query.list();
            System.out.println("\nUSUARIOS CON PERMISOS EN ESTADO PENDIENTE");
            System.out.println("=========================================");
            Iterator<Usuario> itUsuario = usuarios.iterator();
            while (itUsuario.hasNext()) {
                Usuario usuario = itUsuario.next();
                System.out.println("Usuario: " + usuario.getNombre());
            }

            s.getTransaction().commit();
        }
    }

    private void getUsuariosDirecLectura(String calleEnt) {
        String hql = "SELECT u FROM Usuario AS u INNER JOIN u.direccion AS d INNER JOIN u.permisos AS p WHERE d.calle = :calle AND p.nombre like :nombre";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);
            query.setParameter("calle", calleEnt);
            query.setParameter("nombre", "lectura%");
            List<Usuario> usuarios = query.list();
            System.out.println("\nUSUARIOS QUE VIVEN EN UNA DETERMINADA CALLE Y TIENEN PERMISOS DE LECTURA");
            System.out.println("========================================================================");
            Iterator<Usuario> itUsuario = usuarios.iterator();
            while (itUsuario.hasNext()) {
                Usuario usuario = itUsuario.next();
                System.out.println("Usuario: " + usuario.getNombre());
            }

            s.getTransaction().commit();
        }
    }

    private void getUsuariosPermisoActivo() {
        String hql = "SELECT DISTINCT(u) FROM Usuario AS u INNER JOIN u.permisos AS p WHERE p IN (SELECT p FROM Permiso p WHERE p.estado = 'ACTIVO') ";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createQuery(hql);

            List<Usuario> usuarios = query.list();
            System.out.println("\nUSUARIOS CON ALGÚN PERMISO ACTIVO");
            System.out.println("=================================");
            Iterator<Usuario> itUsuario = usuarios.iterator();
            while (itUsuario.hasNext()) {
                Usuario usuario = itUsuario.next();
                System.out.println("Usuario: " + usuario.getNombre());
            }

            s.getTransaction().commit();
        }
    }

    private void getUsuariosCalleSQL() {
        String sql = "SELECT u.nombre,d.calle FROM usuarios AS u INNER JOIN direcciones AS d ON(u.idusuario=d.iddireccion)";
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {
            s.beginTransaction();

            Query query = s.createNativeQuery(sql);

            List<Object[]> datos = query.list();
            System.out.println("\nNOMBRE USUARIO Y DIRECCION CON SQL NATIVO");
            System.out.println("==========================================");
            Iterator<Object[]> it = datos.iterator();
            while (it.hasNext()) {
                Object[] objeto = it.next();
                System.out.println("Usuario: " + objeto[0] + " dirección: " + objeto[1]);
            }

            s.getTransaction().commit();
        }
    }

    private void getNombreCp(Integer idEnt) {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            s.beginTransaction();

            Object[] objeto = (Object[]) s.createNamedQuery("nombreCP").setParameter("id", idEnt).uniqueResult();

            System.out.println("\nNOMBRE Y CÓDIGO POSTAL DE UN USUARIO");
            System.out.println("=====================================");
            System.out.println("Nombre: " + objeto[0] + " código postal: " + objeto[1]);
            s.getTransaction().commit();
        }
    }

    private void getPermisosPaginados() {
        try (Session s = HibernateUtil.getSessionFactory().openSession()) {

            s.beginTransaction();
            Query query = s.createNamedQuery("paginacion");
            query.setMaxResults(3);
            query.setFirstResult(1);
            List<String> permisos = query.getResultList();

            System.out.println("\nPERMISOS PAGINADOS");
            System.out.println("===================");
            Iterator<String> it = permisos.iterator();
            while (it.hasNext()) {
                System.out.println("Permiso: " + it.next());
            }
            s.getTransaction().commit();
        }
    }

}
