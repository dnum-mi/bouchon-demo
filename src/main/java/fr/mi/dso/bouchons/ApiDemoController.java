package fr.mi.dso.bouchons;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiDemoController {

    @Autowired
    private JavaMailSender sender;

    @GetMapping("/mails")
    public String bouchonMail(@RequestHeader Map<String, String> headers, @RequestHeader("Mellon-Role") String role) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("mon-app@interieur-numerique.com");
        message.setTo("p.leclainche@actongroup.com");
        String subject = "Mail de test";
        String content = " Header from Mellon :\n";
        System.out.println(" HEADER ROLE is " + role);
        System.out.println(" HEADERS ");
        for (Entry<String, String> entry : headers.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println(" END HEADERS ");

        content += "Mellon-NameID : " + headers.get("mellon-nameid") + "\n";
        content += "Mellon-Groups : " + headers.get("mellon-groups") + "\n";
        content += "Mellon-Role : " + headers.get("mellon-role") + "\n";

        message.setSubject(subject);
        message.setText(content);
        sender.send(message);

        return "ok";
    }

}
