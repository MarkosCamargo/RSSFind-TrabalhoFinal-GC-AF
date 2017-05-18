/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.udesc.ceavi.findnews.leitorrss;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author marcos
 */
public class ImailImpl implements IMail {

    @Override
    public void sendEmail(String emailTo, String senha, String emailFrom, String msg) {
        try {
            SimpleEmail email = new SimpleEmail();
            //Utilize o hostname do seu provedor de email
            System.out.println("alterando hostname...");
            email.setHostName("smtp-mail.outlook.com");//smtp-mail.outlook.com, smtp.gmail.com
            //Quando a porta utilizada não é a padrão (gmail = 465)
            email.setSmtpPort(587);//587
            //Adicione os destinatários
            email.addTo(emailTo, emailTo);//marcoscamargo826@gmail.com, markos_camargo@hotmail.com
            //Configure o seu email do qual enviará
            email.setFrom(emailFrom, emailFrom);
            //Adicione um assunto
            email.setSubject("[NoReply] noticias encontradas");
            //Adicione a mensagem do email
            email.setMsg(msg);
            //Para autenticar no servidor é necessário chamar os dois métodos abaixo
            System.out.println("autenticando...");
            email.setTLS(true);//SSL, TLS

            email.setAuthentication(emailTo, senha);
            System.out.println("enviando...");
            email.send();
            System.out.println("Email enviado!");
        } catch (EmailException e) {

        }
    }

}
