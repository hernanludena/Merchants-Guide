package com.hludena;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.hludena.service.ExpressionProcessingService;

/**
 * La clase MerchantsGuideApp procesa un archivo de texto para interpretar y analizar expresiones.
 */
public class MerchantsGuideApp {

    public static void main(String[] args) {

        ExpressionProcessingService processingService = new ExpressionProcessingService();

        String fileName = "C:/develop/java/IDEA/fisa/MerchantsGuideToTheGalaxyM/src/main/resources/InpuText.txt";

        // Uso de Try-con-Recursos para el manejo automático del cierre de recursos.
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                 processingService.processLine(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + fileName);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + fileName);
            e.printStackTrace();
        }
    }

}
