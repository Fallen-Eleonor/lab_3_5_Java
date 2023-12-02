package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.service.EmailSenderService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IFileCreator;
//import ntukhpi.kn221a.kro.webappsyrlab3.service.JsonCreationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;

@Controller
@RequestMapping("email")
public class EmailSenderController {
    private final IFileCreator fileCreator;

    public EmailSenderController(IFileCreator fileCreator){
        this.fileCreator = fileCreator;
    }

    @GetMapping("")
    public String showPage(Model model) {
        return "/send_email";
    }

    @PostMapping("/send")
    public ResponseEntity<String> saveHospitalDepartment(@RequestParam String email) {
        File file = fileCreator.createFileFromDatabase("managers");

        boolean isSent = EmailSenderService.sendEmailWithAttachment("sender@gmail.com",
                email,
                "Managers list and phones",
                file,
                false);

        return isSent ? ResponseEntity.ok("Email sent successfully")
                : ResponseEntity.status(500).body("Email doesn't sent");
    }
}
