package org.example;

public class PdfLogger {

    public void logInfo(String mensagem) {
        System.out.println("[INFO] " + mensagem);
    }

    public void logSucesso(String mensagem) {
        System.out.println("[SUCESSO] " + mensagem);
    }

    public void logWarning(String mensagem) {
        System.out.println("[AVISO] " + mensagem);
    }

    public void logError(String mensagem) {
        System.out.println("[ERRO] " + mensagem);
    }

}