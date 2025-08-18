package org.example;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;

public class Main {
    public static void main(String[] args) throws Exception {
        // Arquivo de entrada (PNG) e saída (PDF)
        String caminhoImagem = "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png";
        String caminhoPdf = "D:/Videos/Content/sla.pdf";

        // Criar escritor de PDF
        PdfWriter writer = new PdfWriter(caminhoPdf);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Carregar imagem
        ImageData data = ImageDataFactory.create(caminhoImagem);
        Image image = new Image(data);

        // Adicionar imagem ao PDF
        document.add(image);

        // Fechar
        document.close();
        System.out.println("PDF gerado: " + caminhoPdf);
    }
}
