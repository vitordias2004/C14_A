package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String caminhoPdf = "D:/Videos/Content/teste.pdf";

        List<String> caminhoImagens = Arrays.asList(
                "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png",
                "D:/Videos/Counter-strike 2/Counter-strike 2 Screenshot 2025.04.30 - 12.25.19.11.png",
                "D:/Videos/Elden Ring/Elden Ring Screenshot 2024.06.25 - 19.03.37.02.png"
        );

        PdfImageGenerator generator = new PdfImageGenerator();
        generator.gerarPdfComImagens(caminhoPdf, caminhoImagens);
    }
}