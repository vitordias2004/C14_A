package org.example;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;

import java.io.FileNotFoundException;
import java.util.List;

public class PdfImageGenerator {

    private ImageValidator imageValidator;
    private PdfLogger logger;

    public PdfImageGenerator() {
        this.imageValidator = new ImageValidator();
        this.logger = new PdfLogger();
    }

    public boolean gerarPdfComImagens(String caminhoPdf, List<String> caminhoImagens) {
        if (caminhoImagens == null || caminhoImagens.isEmpty()) {
            logger.logError("Lista de imagens está vazia ou nula");
            return false;
        }

        try {
            PdfWriter writer = new PdfWriter(caminhoPdf);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            logger.logInfo("Iniciando a criação do PDF em: " + caminhoPdf);

            int imagensAdicionadas = processarImagens(document, caminhoImagens);

            document.close();

            if (imagensAdicionadas > 0) {
                logger.logSucesso("PDF com " + imagensAdicionadas + " página(s) gerado com sucesso!");
                return true;
            } else {
                logger.logWarning("Nenhuma imagem válida foi encontrada para gerar o PDF");
                return false;
            }

        } catch (FileNotFoundException e) {
            logger.logError("Erro ao criar o arquivo PDF. Verifique o caminho e as permissões: " + caminhoPdf);
            return false;
        } catch (Exception e) {
            logger.logError("Erro inesperado ao gerar PDF: " + e.getMessage());
            return false;
        }
    }

    public int processarImagens(Document document, List<String> caminhoImagens) {
        int imagensAdicionadas = 0;

        for (int i = 0; i < caminhoImagens.size(); i++) {
            String caminhoImagem = caminhoImagens.get(i);

            if (imageValidator.validarImagem(caminhoImagem)) {
                if (adicionarImagemAoDocumento(document, caminhoImagem)) {
                    imagensAdicionadas++;

                    // Adiciona quebra de página se não for a última imagem
                    if (i < caminhoImagens.size() - 1) {
                        document.add(new AreaBreak());
                    }
                }
            }
        }

        return imagensAdicionadas;
    }

    public boolean adicionarImagemAoDocumento(Document document, String caminhoImagem) {
        try {

            ImageData data = ImageDataFactory.create(caminhoImagem);
            Image image = new Image(data);
            image.setAutoScaleWidth(true);
            document.add(image);

            logger.logInfo("Adicionando imagem: " + caminhoImagem);
            return true;

        } catch (Exception e) {
            logger.logError("Erro ao adicionar imagem " + caminhoImagem + ": " + e.getMessage());
            return false;
        }
    }
}