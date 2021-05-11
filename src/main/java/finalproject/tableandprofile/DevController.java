package finalproject.tableandprofile;

import finalproject.tableandprofile.model.Post;
import finalproject.tableandprofile.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Optional;

@EnableSwagger2
@Controller
public class DevController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/techers")
    public String devsection(Model model){
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "teachers";
    }

    @GetMapping("/teachers/add")
    public String devAdd(Model model){
        return "dev-add";
    }

    @PostMapping("/teachers/add")
    public String devPostAdd(@RequestParam int id , @RequestParam String name, @RequestParam String email, @RequestParam String field, Model model){
        Post post = new Post(id, name, email, field );
        postRepository.save(post);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/{id}")
    public String devUpdate(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/teachers";
        }
        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "dev-details";
    }

    @GetMapping("/teachers/{id}/edit")
    public String devEdit(@PathVariable(value = "id") long id, Model model){
        if(!postRepository.existsById(id)){
            return "redirect:/teachers";
        }
        Optional<Post> post=postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res);
        return "teachers-edit";
    }

    @PostMapping("/teachers-section/{id}/edit")
    public String devPostEdit(@PathVariable(value = "id") long id,  @RequestParam String name, @RequestParam String email, @RequestParam String field, Model model){
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);
        post.setFname(name);
        post.setEmail(email);
        post.setField(field);
        postRepository.save(post);
        return "redirect:/teachers-section";
    }

    @PostMapping("/teachers-section/{id}/delete")
    public String devPostDelete(@PathVariable(value = "id") long id, Model model){
        Post post = postRepository.findById(id).orElseThrow(IllegalStateException::new);

        postRepository.delete(post);
        return "redirect:/teachers-section";
    }
}