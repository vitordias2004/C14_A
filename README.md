# PDF Image Generator

![Java CI with Maven](https://github.com/vitordias2004/C14_A/workflows/Java%20CI%20with%20Maven/badge.svg)
![Java](https://img.shields.io/badge/Java-23-orange)
![Maven](https://img.shields.io/badge/Maven-Build-blue)
![iText7](https://img.shields.io/badge/iText7-7.1.18-green)

Projeto Java para conversão de imagens em documentos PDF utilizando a biblioteca iText7.

##  Descrição

Sistema para converter uma ou múltiplas imagens (`.png`, `.jpg`, `.jpeg`, etc.) em arquivo PDF, com validação automática, processamento individual e sistema de logging.

### Funcionalidades

- ✅ Conversão de múltiplas imagens em um único PDF
- ✅ Validação de imagens antes do processamento
- ✅ Uma imagem por página

## 🏗 Arquitetura

**PdfImageGenerator** - Gerencia criação do PDF e processamento de imagens  
**ImageValidator** - Valida existência e formato das imagens  
**PdfLogger** - Sistema de logging (info, erro, aviso, sucesso)

##  Como Usar

### Dependências Maven

```xml
<dependency>
    <groupId>com.itextpdf</groupId>
    <artifactId>itext7-core</artifactId>
    <version>7.1.18</version>
    <type>pom</type>
</dependency>

<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.9.0</version>
    <scope>test</scope>
</dependency>
```

### Exemplo de Código

```java
PdfImageGenerator generator = new PdfImageGenerator();

String caminhoPdf = "output/documento.pdf";
List<String> imagens = Arrays.asList(
    "imagens/foto1.png",
    "imagens/foto2.jpg",
    "imagens/foto3.png"
);

boolean sucesso = generator.gerarPdfComImagens(caminhoPdf, imagens);
```

### Instalação

```bash
git clone https://github.com/vitordias2004/C14_A.git
cd C14_A
mvn clean install
mvn exec:java -Dexec.mainClass="org.example.Main"
```

## 🔄CI/CD - GitHub Actions

Pipeline automatizado que executa em push e pull requests para `main`:

### Stages

**1. Test** - Executa testes unitários e gera relatórios (Surefire)  
**2. Build** - Compila o projeto e gera o JAR  
**3. Notify** - Envia notificação por email com status do pipeline

### Artefatos Gerados

-  **relatorio-testes**: Relatórios detalhados dos testes
-  **app-jar**: Arquivo JAR compilado


##  Testes

Cobertura completa de testes unitários:

- Adição de imagens válidas e inválidas
- Listas vazias, nulas e mistas
- Processamento de múltiplas imagens
- Caminhos inválidos e nulos
- Geração completa de PDF

```bash
mvn test
```

##  Estrutura

```
C14_A/
├── src/
│   ├── main/java/org/example/
│   │   ├── PdfImageGenerator.java
│   │   ├── ImageValidator.java
│   │   ├── PdfLogger.java
│   │   └── Main.java
│   └── test/java/
│       └── PdfImageGeneratorTest.java
├── .github/
│   ├── workflows/maven.yml
│   └── scripts/send_email.js
└── pom.xml
```

##  Tecnologias

- **Java 23** - Linguagem de programação
- **iText7** - Manipulação de PDF
- **JUnit 5** - Testes unitários
- **Maven** - Gerenciamento de dependências
- **GitHub Actions** - CI/CD

##  Notas

**Histórico de Bugs:** Após commit do Edu, 6 de 9 testes de `ImageValidator` falharam. Erro identificado e corrigido através da análise dos testes.
