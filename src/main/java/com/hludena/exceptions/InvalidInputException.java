package com.hludena.exceptions;

/**
 * Es una excepción personalizada que se lanza cuando se detecta una entrada inválida en la aplicación.
 */
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

