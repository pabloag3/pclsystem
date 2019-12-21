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
    
    
    public ArrayList<Cuentas> listarCuentas() throws MalformedURLException, IOException, JSONException {
        //321654
        ArrayList<Cuentas> listaCuentas = new ArrayList<Cuentas>();
        String url = "http://localhost:8080/pclsystemBackE-2.0-SNAPSHOT/pclsystem/cuentas/traer-cuentas-pendientes-de-cliente";
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
        
        JSONArray cuentaJSON = new JSONArray(response2.toString());
   
        Cuentas cuenta = null;

        for (int i = 0; i < cuentaJSON.length(); i++) {
            
            
            
            String correoElectronico = (cuentaJSON.getJSONObject(i).getJSONObject("codCaso").getJSONObject("codCliente").get("correoElectronico") != null) ? 
                    (String)cuentaJSON.getJSONObject(i).getJSONObject("codCaso").getJSONObject("codCliente").get("correoElectronico") : "null"; ;
            
            
            cuenta = new Cuentas(cuentaJSON.getJSONObject(i).getInt("total"),
                    cuentaJSON.getJSONObject(i).getInt("saldo"),
                    cuentaJSON.getJSONObject(i).getString("descripcion"), correoElectronico
                    );

            listaCuentas.add(cuenta);

        }

        System.out.println(listaCuentas);

        return listaCuentas;
    }
    
}
