package com.lawyersys.pclsystembe.utilidades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

/**
 *
 * @author tatoa
 */
public class Log {
    
    static Logger logger = Logger.getLogger("com.api.jar");
    
    static String logFileName = "C:\\pclSystemFiles\\log.log";
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm-ss");
    
    static public void escribir(String nivel, String mensaje) throws IOException {
        
        // formateo la hora del evento
        LocalDateTime now = LocalDateTime.now();
        
        BufferedWriter bw = null;
        FileWriter fw = null;
        
//        logger.info(mensaje);         
        
        File file = new File(logFileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try
        {
            mensaje = "\n" + dtf.format(now) + " " + nivel.toUpperCase() + ": " + mensaje;
            
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(mensaje);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //Cierra instancias de FileWriter y BufferedWriter
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
        }   
        }
        
        
    }
    
}
