package org.example;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String caminhoPdf = "C:\\Users\\felip\\Downloads\\fotos teste\\meu_pdf_gerado.pdf";

        // 2. Adicione ou remova os caminhos das imagens que você quer converter.
        List<String> caminhossImagens = Arrays.asList(
                "C:\\Users\\felip\\Downloads\\fotos teste\\s1mple.jpeg",
                "C:\\Users\\felip\\Downloads\\fotos teste\\fentanyljr.jpeg",
                "C:\\Users\\felip\\Downloads\\fotos teste\\aborgue.jpg",
                "C:\\Users\\felip\\Downloads\\fotos teste\\qualquer.jpeg"
        );
        // --- FIM DA CONFIGURAÇÃO ---


        // O resto do código funciona sem alterações.
        try {
            PdfWriter writer = new PdfWriter(caminhoPdf);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            System.out.println("Iniciando a criação do PDF em: " + caminhoPdf);

            for (int i = 0; i < caminhossImagens.size(); i++) {
                String caminhoImagem = caminhossImagens.get(i);
                File arquivoImagem = new File(caminhoImagem);

                if (!arquivoImagem.exists()) {
                    System.err.println("Aviso: Imagem não encontrada, pulando: " + caminhoImagem);
                    continue;
                }

                System.out.println("Adicionando imagem: " + caminhoImagem);
                ImageData data = ImageDataFactory.create(caminhoImagem);
                Image image = new Image(data);
                image.setAutoScaleWidth(true);
                document.add(image);

                if (i < caminhossImagens.size() - 1) {
                    document.add(new AreaBreak());
                }
            }

            document.close();
            System.out.println("PDF com " + caminhossImagens.size() + " página(s) gerado com sucesso!");

        } catch (FileNotFoundException e) {
            System.err.println("Erro ao criar o arquivo PDF. Verifique o caminho e as permissões: " + caminhoPdf);
        } catch (Exception e) {
            System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}