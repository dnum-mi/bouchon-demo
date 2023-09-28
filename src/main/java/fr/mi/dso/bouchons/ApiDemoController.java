package fr.mi.dso.bouchons;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiDemoController {

    @Autowired
    public JavaMailSender emailSender;

    @GetMapping("/mail")
    public void bouchonMail(@RequestHeader Map<String, String> headers) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mon-app@interieur-numerique.com");
        message.setTo("p.leclainche@actongroup.com");
        String subject = "Mail de test";
        String content = " Header from Mellon :\n";
        content += "Mellon-NameID : " + headers.get("Mellon-NameID") + "\n";
        content += "Mellon-Groups : " + headers.get("Mellon-Groups") + "\n";
        content += "Mellon-Role : " + headers.get("Mellon-Role") + "\n";

        message.setSubject(subject);
        message.setText(content);
        emailSender.send(message);
    }

}
