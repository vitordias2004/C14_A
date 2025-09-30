package org.example;

import java.io.File;

public class ImageValidator {

    private static final String[] EXTENSOES_VALIDAS = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};

    public boolean validarImagem(String caminhoImagem) {
        if (caminhoImagem == null || caminhoImagem.trim().isEmpty()) {
            System.out.println("Caminho da imagem está vazio ou nulo ");
            return false;
        }

        File arquivoImagem = new File(caminhoImagem);

        if (!arquivoImagem.exists()) {
            System.out.println("Aviso: Imagem não encontrada, pulando: " + caminhoImagem);
            return false;
        }

        if (!possuiExtensaoValida(caminhoImagem)) {
            System.out.println("Aviso: Extensão de arquivo não suportada: " + caminhoImagem);
            return false;
        }

        return true;
    }

    public boolean possuiExtensaoValida(String caminhoImagem) {
        String nomeArquivo = caminhoImagem.toLowerCase();

        for (String extensao : EXTENSOES_VALIDAS) {
            if (nomeArquivo.endsWith(extensao)) {
                return true;
            }
        }
        return false;
    }
}