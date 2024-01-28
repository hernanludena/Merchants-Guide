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

        if (args.length < 1) {
            System.err.println("Por favor, proporciona la ruta del archivo de texto.");
            return;
        }

        //String fileName = "/InputText.txt"; // Nombre del archivo en resources
        String filePath = args[0];

        ExpressionProcessingService processingService = new ExpressionProcessingService();

        // Uso de Try-con-Recursos para el manejo automático del cierre de recursos.
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processingService.processLine(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            e.printStackTrace();
        }
    }

}
