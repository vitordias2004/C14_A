import org.example.ImageValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.nio.file.Path;

public class ImageValidatorTest {

    @TempDir
    Path tempDir;

    private String imagemValida;

    @BeforeEach
    public void setup() throws Exception {
        imagemValida = criarImagemTeste(tempDir, "teste_valido.png");
    }

    private String criarImagemTeste(Path dir, String nome) throws Exception {
        BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                img.setRGB(x, y, Color.BLUE.getRGB());
            }
        }
        File arquivo = dir.resolve(nome).toFile();
        ImageIO.write(img, "png", arquivo);
        return arquivo.getAbsolutePath();
    }

    @Test
    public void testarExtensaoValidaTrue() {
        String extensao = "sla.jpg";
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.possuiExtensaoValida(extensao));
    }

    @Test
    public void testarExtensaoValidaFalse() {
        String extensao = "doido.mp4";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.possuiExtensaoValida(extensao));
    }

    @Test
    public void testarArquivoSemExtensao() {
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.possuiExtensaoValida("arquivo_sem_extensao"));
    }

    @Test
    public void testarCaminhoComPontosMultiplos() {
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.possuiExtensaoValida("minha.imagem.favorita.jpg"));
    }

    @Test
    public void testarCaminhoVazio() {
        String caminho = "";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarCaminhoInexistente() {
        String caminho = "nadaver.jpg";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarCaminhoComEspacos() {
        String caminho = "   ";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarCaminhoNulo() {
        String caminho = null;
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarValidarImagem() {
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.validarImagem(imagemValida));
    }
}