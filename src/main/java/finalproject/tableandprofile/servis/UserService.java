package finalproject.tableandprofile.servis;

import finalproject.tableandprofile.model.Users1;
import finalproject.tableandprofile.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    @Autowired private BCryptPasswordEncoder encoder;

    @Autowired private UserRepository userRepository;

//    String encryptedPassword = passwordEncoder.encode(user.getPassword());
//   user.setPassword(encryptedPassword);

    public void save(Users1 users1) {

        users1.setPassword(encoder.encode(users1.getPassword()));

        userRepository.save(users1);
    }

}