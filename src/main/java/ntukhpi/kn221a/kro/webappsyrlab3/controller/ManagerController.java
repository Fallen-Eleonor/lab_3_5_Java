package ntukhpi.kn221a.kro.webappsyrlab3.controller;

import ntukhpi.kn221a.kro.webappsyrlab3.entity.Manager;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IManagerService;
import ntukhpi.kn221a.kro.webappsyrlab3.service.IPhoneService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@EnableMethodSecurity
@RequestMapping("managers")
public class ManagerController {
    private final String AddTitle = "Add Manager";
    private final String EditTitle = "Edit Manager";

    private final IManagerService managerService;
    private final IPhoneService phoneService;

    public ManagerController(IManagerService managerService, IPhoneService phoneService) {
        this.managerService = managerService;
        this.phoneService = phoneService;
    }

    @GetMapping("")
    public String showManagersPage(Model model) {
        model.addAttribute("managers", managerService.getAllEntities());
        return "/managers/managerList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save/{id}")
    public String saveManager(@ModelAttribute("manager") Manager manager, Model model) {
        if (manager.getId() == null || manager.getId() == 0) {
            Manager existingManager = managerService.getManagerByCode(manager.getCode());
            if (existingManager == null) {
                managerService.saveEntity(manager);
            } else {
                return LoadPage(model, manager, AddTitle, "Such a manager already exists in the db");
            }
        } else {
            Manager existingManager = managerService.getEntityById(manager.getId());
            if (existingManager != null) {
                managerService.updateEntity(manager);
            } else {
                return LoadPage(model, manager, EditTitle, "Such a manager does not exist in db");
            }
        }
        return "redirect:/managers";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/new")
    public String createManagerForm(Model model) {
        Manager newManager = new Manager(
                "",
                "",
                "000000",
                LocalDate.now(),
                Manager.Status.ACTIVE,
                false);

        model.addAttribute("manager", newManager);
        model.addAttribute("titleManager", "Add Manager");
        model.addAttribute("errorString", null);
        return "/managers/managerField";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/edit/{idEdit}")
    public String editManagerForm(@PathVariable Long idEdit, Model model) {
        Manager managerForUpdateInDB = managerService.getEntityById(idEdit);
        model.addAttribute("manager", managerForUpdateInDB);
        model.addAttribute("titleManager", "Edit Manager");
        model.addAttribute("errorString", null);
        return "/managers/managerField";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/del/{idManagerForDelete}")
    public String deleteManager(@PathVariable Long idManagerForDelete, Model model) {
        Manager delManagerInDB = managerService.getEntityById(idManagerForDelete);

        if(!phoneService.getAllPhonesByManagerId(idManagerForDelete).isEmpty()){
            String messageDeleteError = "All phones must be removed!";
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "/managers");
            return "delete_error";
        }

        if (delManagerInDB != null) {
            managerService.deleteEntityById(idManagerForDelete);
            return "redirect:/managers";
        } else {
            String messageDeleteError = "There is no such thing in the database!\n" +
                    "Object: HOSPITAL_DEPARTMENT, id=" + idManagerForDelete;
            model.addAttribute("error_del_message", messageDeleteError);
            model.addAttribute("ret_page", "/managers");
            return "delete_error";
        }
    }

    private String LoadPage(Model model, Manager manager, String title, String errorString) {
        model.addAttribute("manager", manager);
        model.addAttribute("titleManager", title);
        model.addAttribute("errorString", errorString);

        return "/managers/managerField";
    }
}
