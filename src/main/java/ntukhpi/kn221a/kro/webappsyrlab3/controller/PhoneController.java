package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Manager;
import ntukhpi.kn221a.kro.webappsyrlab3.entity.Phone;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IManagerService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IPhoneService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@EnableMethodSecurity
@RequestMapping("/phones")
public class PhoneController {
    private final String addTitle = "Add Phone";
    private final String editTitle = "Edit Phone";

    private final IPhoneService phoneService;
    private final IManagerService managerService;
    private Manager manager;

    public PhoneController(IPhoneService phoneService, IManagerService managerService) {
        this.phoneService = phoneService;
        this.managerService = managerService;
    }

    @GetMapping("")
    public String showPhonesPage(@RequestParam("id") Long managerId, Model model) {
        manager = managerService.getEntityById(managerId);
        model.addAttribute("phones", phoneService.getAllPhonesByManagerId(managerId));
        return "/phones/phoneList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{id}")
    public String savePhone(@ModelAttribute("phone") Phone phone, Model model) {
        phone.setManager(manager);

        if (phone.getId() == null || phone.getId() == 0) {
            Phone existingPhone = phoneService.getPhoneByNumber(phone.getNumber());
            if (existingPhone == null) {
                phoneService.saveEntity(phone);
            } else {
                return loadPage(model, phone, addTitle, "A phone with this number already exists in the database");
            }
        } else {
            Phone existingPhone = phoneService.getEntityById(phone.getId());
            if (existingPhone != null) {
                phoneService.updateEntity(phone);
            } else {
                return loadPage(model, phone, editTitle, "Phone not found in the database");
            }
        }
        return getRedirectPath();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String createPhoneForm(Model model) {
        Phone newPhone = new Phone(
                manager,
                "+380",
                true,
                LocalTime.now()
        );

        model.addAttribute("phone", newPhone);
        model.addAttribute("titlePhone", addTitle);
        model.addAttribute("errorString", null);
        return "/phones/phoneField";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{id}")
    public String editPhoneForm(@PathVariable Long id, Model model) {
        Phone phoneForUpdate = phoneService.getEntityById(id);
        model.addAttribute("phone", phoneForUpdate);
        model.addAttribute("titlePhone", editTitle);
        model.addAttribute("errorString", null);
        return "/phones/phoneField";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/del/{id}")
    public String deletePhone(@PathVariable Long id, Model model) {
        Phone phoneToDelete = phoneService.getEntityById(id);
        if (phoneToDelete != null) {
            phoneService.deleteEntityById(id);
        } else {
            // Handle error when trying to delete a non-existing phone
        }
        return getRedirectPath();
    }

    private String loadPage(Model model, Phone phone, String title, String errorString) {
        model.addAttribute("phone", phone);
        model.addAttribute("titlePhone", title);
        model.addAttribute("errorString", errorString);
        return "/phones/phoneField";
    }

    private String getRedirectPath() {
        return "redirect:/phones?id=" + manager.getId();
    }
}
