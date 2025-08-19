# Conversor de Imagem para PDF (Java + iText7)

Este projeto demonstra como converter uma imagem (`.png`, `.jpg`, etc.) em um arquivo PDF usando a biblioteca **iText7** em Java.

##  Funcionalidades
- Lê uma imagem a partir de um caminho local.
- Cria um documento PDF.
- Insere a imagem no documento.
- Salva o PDF no diretório especificado.

##  Estrutura do código
O código principal está na classe `Main.java`, e realiza os seguintes passos:
1. Define os caminhos da imagem de entrada e do PDF de saída.
2. Cria um `PdfWriter` e um `PdfDocument`.
3. Carrega a imagem usando `ImageDataFactory`.
4. Adiciona a imagem ao documento PDF.
5. Fecha o documento e exibe uma mensagem de sucesso.

##  Como executar
1. Clone o repositório ou copie o código para um projeto Java.
2. Adicione a dependência do **iText7** ao seu `pom.xml` (caso use Maven):

```xml
<dependency>
  <groupId>com.itextpdf</groupId>
  <artifactId>itext7-core</artifactId>
  <version>7.1.18</version>
  <type>pom</type>
</dependency>
