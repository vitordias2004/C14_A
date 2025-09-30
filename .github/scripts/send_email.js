const nodemailer = require('nodemailer');

async function enviarEmail() {
    // Pegar variáveis de ambiente
    const username = process.env.EMAIL_USERNAME;
    const password = process.env.EMAIL_PASSWORD;
    const destinatario = process.env.EMAIL_TO;

    // Informações do CI
    const repo = process.env.GITHUB_REPOSITORY || 'Desconhecido';
    const branch = process.env.GITHUB_REF_NAME || 'Desconhecido';
    const autor = process.env.GITHUB_ACTOR || 'Desconhecido';
    const commit = process.env.GITHUB_SHA ? process.env.GITHUB_SHA.substring(0, 7) : 'N/A';
    const statusTest = process.env.TEST_STATUS || 'unknown';
    const statusBuild = process.env.BUILD_STATUS || 'unknown';
    const runUrl = process.env.RUN_URL || '#';

    // Validar variáveis obrigatórias
    if (!username || !password || !destinatario) {
        console.error('❌ Erro: Variáveis EMAIL_USERNAME, EMAIL_PASSWORD ou EMAIL_TO não configuradas!');
        process.exit(1);
    }

    // Configurar transportador SMTP
    const transporter = nodemailer.createTransport({
        host: 'smtp.gmail.com',
        port: 587,
        secure: false, // true para porta 465, false para outras portas
        auth: {
            user: username,
            pass: password
        }
    });

    // Determinar emoji de status
    const emojiStatus = (statusTest === 'success' && statusBuild === 'success') ? '✅' : '❌';
    const corTest = statusTest === 'success' ? 'green' : 'red';
    const corBuild = statusBuild === 'success' ? 'green' : 'red';

    // Configurar email
    const mailOptions = {
        from: `"CI/CD Bot" <${username}>`,
        to: destinatario,
        subject: `${emojiStatus} CI/CD Pipeline - ${repo}`,
        html: `
            <html>
                <head>
                    <style>
                        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }
                        .container { max-width: 600px; margin: 0 auto; padding: 20px; }
                        .header { background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); 
                                  color: white; padding: 20px; border-radius: 8px 8px 0 0; }
                        .content { background: #f8f9fa; padding: 20px; border-radius: 0 0 8px 8px; }
                        .info-row { padding: 10px; margin: 5px 0; background: white; border-radius: 4px; }
                        .status { padding: 5px 10px; border-radius: 4px; font-weight: bold; display: inline-block; }
                        .success { background: #d4edda; color: #155724; }
                        .failure { background: #f8d7da; color: #721c24; }
                        .btn { background: #667eea; color: white; padding: 10px 20px; 
                               text-decoration: none; border-radius: 4px; display: inline-block; margin-top: 15px; }
                        hr { border: none; border-top: 2px solid #dee2e6; margin: 20px 0; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <div class="header">
                            <h2>🔔 Notificação de CI/CD</h2>
                            <p style="margin: 0;">Pipeline finalizado</p>
                        </div>
                        <div class="content">
                            <div class="info-row">
                                <strong>📦 Repositório:</strong> ${repo}
                            </div>
                            <div class="info-row">
                                <strong>🌿 Branch:</strong> ${branch}
                            </div>
                            <div class="info-row">
                                <strong>👤 Autor:</strong> ${autor}
                            </div>
                            <div class="info-row">
                                <strong>🔖 Commit:</strong> ${commit}
                            </div>
                            
                            <hr>
                            
                            <h3 style="margin-top: 20px;">Status dos Jobs:</h3>
                            <div class="info-row">
                                <strong>🧪 Testes:</strong> 
                                <span class="status" style="background: ${corTest === 'green' ? '#d4edda' : '#f8d7da'}; 
                                                           color: ${corTest === 'green' ? '#155724' : '#721c24'}">
                                    ${statusTest.toUpperCase()}
                                </span>
                            </div>
                            <div class="info-row">
                                <strong>🔨 Build:</strong> 
                                <span class="status" style="background: ${corBuild === 'green' ? '#d4edda' : '#f8d7da'}; 
                                                           color: ${corBuild === 'green' ? '#155724' : '#721c24'}">
                                    ${statusBuild.toUpperCase()}
                                </span>
                            </div>
                            
                            <div style="text-align: center; margin-top: 20px;">
                                <a href="${runUrl}" class="btn">Ver Detalhes no GitHub</a>
                            </div>
                        </div>
                    </div>
                </body>
            </html>
        `
    };

    // Enviar email
    try {
        console.log('📤 Enviando email...');
        const info = await transporter.sendMail(mailOptions);
        console.log('✅ Email enviado com sucesso!');
        console.log('   Message ID:', info.messageId);
        console.log('   Para:', destinatario);
    } catch (error) {
        console.error('❌ Erro ao enviar email:', error.message);
        process.exit(1);
    }
}

// Executar
enviarEmail();