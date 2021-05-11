package finalproject.tableandprofile;

import finalproject.tableandprofile.model.Post;
import finalproject.tableandprofile.servis.PostService;
import finalproject.tableandprofile.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Optional;

@EnableSwagger2
@Controller
public class MainController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home(Model model, String keyword) {
//        Iterable<Post> posts = postRepository.findAll();
//        Iterable<Post> posts1 = postService.findByKeyword(keyword);

        if(keyword!=null){
            model.addAttribute("posts",postService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("posts", postService.getPosts());
        }
        return "home";
    }

    @GetMapping("/homeFiltered")
    public String homeFiltered(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "home";
    }

    @GetMapping("/home-details/{id}")
    public String homeMore(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/";
        }
        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "home-details";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title","Page about us");
        return "about";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        return "Profile";
    }

}

