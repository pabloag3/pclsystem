package com.lawyersys.notificacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author carlo
 */
public class ModeloEmail {
    
    
    public ArrayList<Actividad> listarActividad() throws MalformedURLException, IOException, JSONException {
        //321654
         ArrayList<Actividad> listaActividades = new ArrayList<Actividad>();
        String url = "http://localhost:8080/pclsystemBackE-2.0-SNAPSHOT/pclsystem/actividades/traer-pendientes-caducados";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();
        
        JSONArray actividadJSON = new JSONArray(response2.toString());
    
        System.out.println("LOgintud: "+actividadJSON.length());
   
        Actividad actividad = null;

        for (int i = 0; i < actividadJSON.length(); i++) {
            String observacion = (actividadJSON.getJSONObject(i).get("observacion") == null) ? (String)actividadJSON.getJSONObject(i).get("observacion") : "null"; ;
            actividad = new Actividad(actividadJSON.getJSONObject(i).getString("descripcion"),
                    observacion,
                    actividadJSON.getJSONObject(i).getJSONObject("cedulaResponsable").getString("cedula"),
                    actividadJSON.getJSONObject(i).getString("fecha"),
                    actividadJSON.getJSONObject(i).getInt("diaNotificable"));

            listaActividades.add(actividad);

        }

        System.out.println(listaActividades);

        return listaActividades;
    }
    
    public String treaerEmail(String cedula) throws MalformedURLException, IOException, JSONException {
        
        String url = "http://localhost:8080/pclsystemBackE-2.0-SNAPSHOT/pclsystem/usuarios/traer-por-cedula/" + cedula;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        //con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();

        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response2 = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response2.append(inputLine);
        }
        in.close();
        
        System.out.println("Respuesta:" + response2.toString());
        JSONArray usuarioJSON = new JSONArray(response2.toString());
        
        String email = usuarioJSON.getJSONObject(0).getString("correoElectronico");
        
        System.out.println("Email: " + email);

      

        return email;
    }
    
}
