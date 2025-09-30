import org.example.PdfImageGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfImageGeneratorTest {

    @TempDir
    Path tempDir;

    private String imagemTeste1;
    private String imagemTeste2;
    private String imagemTeste3;

    @BeforeEach
    public void setup() throws Exception {
        imagemTeste1 = criarImagemTeste(tempDir, "imagem1.png", Color.RED);
        imagemTeste2 = criarImagemTeste(tempDir, "imagem2.png", Color.BLUE);
        imagemTeste3 = criarImagemTeste(tempDir, "imagem3.png", Color.GREEN);
    }


    private String criarImagemTeste(Path dir, String nome, Color cor) throws Exception { //cria imagens pro teste (sla como)
        BufferedImage img = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 200; y++) {
                img.setRGB(x, y, cor.getRGB());
            }
        }
        File arquivo = dir.resolve(nome).toFile();
        ImageIO.write(img, "png", arquivo);
        return arquivo.getAbsolutePath();
    }

    @Test
    public void testarAddImageTrue() throws FileNotFoundException {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste.pdf").toString();

        PdfWriter writer = new PdfWriter(caminhoPdf);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertTrue(generator.adicionarImagemAoDocumento(document, imagemTeste1));
        document.close();
    }

    @Test
    public void testarAddImageFalse() throws FileNotFoundException {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste.pdf").toString();
        String caminhoImagem = "caminhoerrado.png";

        PdfWriter writer = new PdfWriter(caminhoPdf);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertFalse(generator.adicionarImagemAoDocumento(document, caminhoImagem));
        document.close();
    }

    @Test
    public void testarGerarPdfComListaVazia() {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste_vazio.pdf").toString();
        List<String> caminhoImagens = new ArrayList<>();

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }

    @Test
    public void testarGerarPdfComListaNula() {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste_nulo.pdf").toString();
        List<String> caminhoImagens = null;

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }

    @Test
    public void testarProcessarImagem() throws FileNotFoundException {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste.pdf").toString();
        List<String> caminhoImagens = Arrays.asList(imagemTeste1, imagemTeste2, imagemTeste3);

        PdfWriter writer = new PdfWriter(caminhoPdf);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertEquals(3, generator.processarImagens(document, caminhoImagens));
        document.close();
    }

    @Test
    public void testarGerarPdfFalse() {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = ""; // Caminho inválido
        List<String> caminhoImagens = Arrays.asList(imagemTeste1, imagemTeste2, imagemTeste3);

        assertFalse(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }

    @Test
    public void testarGerarPdfTrue() {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste_completo.pdf").toString();
        List<String> caminhoImagens = Arrays.asList(imagemTeste1, imagemTeste2, imagemTeste3);

        assertTrue(generator.gerarPdfComImagens(caminhoPdf, caminhoImagens));
    }

    @Test
    public void testarAdicionarImagemComCaminhoNulo() throws FileNotFoundException {
        PdfImageGenerator generator = new PdfImageGenerator();
        String caminhoPdf = tempDir.resolve("teste_nulo.pdf").toString();
        String caminhoImagem = null;

        PdfWriter writer = new PdfWriter(caminhoPdf);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        assertFalse(generator.adicionarImagemAoDocumento(document, caminhoImagem));
        document.close();
    }
}