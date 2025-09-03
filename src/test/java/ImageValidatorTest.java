import org.example.ImageValidator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ImageValidatorTest {

    @Test
    public void testarExtensaoValidaTrue(){
        String extensao = "sla.jpg";
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.possuiExtensaoValida(extensao));
    }

    @Test
    public void testarExtensaoValidaFalse(){
        String extensao = "doido.mp4";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.possuiExtensaoValida(extensao));
    }

    @Test
    public void testarArquivoSemExtensao(){
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.possuiExtensaoValida("arquivo_sem_extensao"));
    }

    @Test
    public void testarCaminhoComPontosMultiplos(){
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.possuiExtensaoValida("minha.imagem.favorita.jpg"));
    }

    @Test
    public void testarCaminhoVazio(){
        String caminho = "";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }


    @Test
    public void testarCaminhoInexistente(){
        String caminho = "nadaver.jpg";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarCaminhoComEspacos(){
        String caminho = "   ";
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarCaminhoNulo(){
        String caminho = null;
        ImageValidator imagem = new ImageValidator();
        assertFalse(imagem.validarImagem(caminho));
    }

    @Test
    public void testarValidarImagem(){
        String caminho = "D:/Videos/The Witcher 3/The Witcher 3 Screenshot 2024.12.08 - 20.25.34.17.png";
        ImageValidator imagem = new ImageValidator();
        assertTrue(imagem.validarImagem(caminho));
    }





}
