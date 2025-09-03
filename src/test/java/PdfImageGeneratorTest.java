import org.example.PdfImageGenerator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfImageGeneratorTest {

    @Test
    public void testarAddImageTrue() throws FileNotFoundException {

        PdfImageGenerator generator = new PdfImageGenerator();

        String caminhoPdf = "D:/Videos/Content/teste.pdf";
        String caminhoImagem = "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png";

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(caminhoPdf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertTrue(generator.adicionarImagemAoDocumento(document, caminhoImagem));
        document.close();

    }

    @Test
    public void testarAddImageFalse() throws FileNotFoundException {

        PdfImageGenerator generator = new PdfImageGenerator();

        String caminhoPdf = "teste.pdf";
        String caminhoImagem = "caminhoerrado.png";

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(caminhoPdf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertFalse(generator.adicionarImagemAoDocumento(document, caminhoImagem));
        document.close();

    }

    @Test
    public void testarGerarPdfComListaVazia(){
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = "D:/Videos/Content/teste_vazio.pdf";
        List<String> caminhoImagens = new ArrayList<>();

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }

    @Test
    public void testarGerarPdfComListaNula(){
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = "D:/Videos/Content/teste_nulo.pdf";
        List<String> caminhoImagens = null;

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }


    @Test
    public void testarProcessarImagem(){
        PdfImageGenerator generator = new PdfImageGenerator();

        String caminhoPdf = "teste.pdf";
        List<String> caminhoImagens = Arrays.asList(
                "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png",
                "D:/Videos/Counter-strike 2/Counter-strike 2 Screenshot 2025.04.30 - 12.25.19.11.png",
                "D:/Videos/Elden Ring/Elden Ring Screenshot 2024.06.25 - 19.03.37.02.png"
        );

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(caminhoPdf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertSame(3, generator.processarImagens(document, caminhoImagens));
        document.close();
    }

    @Test
    public void testarGerarPdfFalse(){
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = "";
        List<String> caminhoImagens = Arrays.asList(
                "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png",
                "D:/Videos/Counter-strike 2/Counter-strike 2 Screenshot 2025.04.30 - 12.25.19.11.png",
                "D:/Videos/Elden Ring/Elden Ring Screenshot 2024.06.25 - 19.03.37.02.png"
        );

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));

    }

    @Test
    public void testarGerarPdfTrue(){
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = "D:/Videos/Content/teste.pdf";
        List<String> caminhoImagens = Arrays.asList(
                "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png",
                "D:/Videos/Counter-strike 2/Counter-strike 2 Screenshot 2025.04.30 - 12.25.19.11.png",
                "D:/Videos/Elden Ring/Elden Ring Screenshot 2024.06.25 - 19.03.37.02.png"
        );

        assertTrue(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));

    }

    @Test
    public void testarAdicionarImagemComCaminhoNulo(){
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = "teste_nulo.pdf";
        String caminhoImagem = null;

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(caminhoPdf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertFalse(generator.adicionarImagemAoDocumento(document, caminhoImagem));

        document.close();
    }

}
