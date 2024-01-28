package com.hludena;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import com.hludena.service.ExpressionProcessingService;

/**
 * La clase MerchantsGuideApp procesa un archivo de texto para interpretar y analizar expresiones.
 */
public class MerchantsGuideApp {

    public static void main(String[] args) {

        ExpressionProcessingService processingService = new ExpressionProcessingService();

        String fileName = "/InputText.txt"; // Nombre del archivo en resources

        // Uso de Try-con-Recursos para el manejo automático del cierre de recursos.
        try (InputStream inputStream = MerchantsGuideApp.class.getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            
            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo no encontrado: " + fileName);
            }

            String line;
            while ((line = reader.readLine()) != null) {
                processingService.processLine(line);
            }
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + fileName);
            e.printStackTrace();
        }
    }

}
