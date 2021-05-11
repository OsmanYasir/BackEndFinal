package finalproject.tableandprofile;

import finalproject.tableandprofile.model.Users1;
import finalproject.tableandprofile.servis.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
public class UserController {

    @Autowired private UserService userService;

    @GetMapping("/users1")
    public String getCountries() {
        return "home";
    }


    //Modified method to Add a new user User
    @PostMapping(value="users/addNew")
    public RedirectView addNew(Users1 users1, RedirectAttributes redir) {

        userService.save(users1);

        RedirectView  redirectView= new RedirectView("/login",true);

        redir.addFlashAttribute("message",	"You successfully registered! You can now login");

        return redirectView;
    }

}
