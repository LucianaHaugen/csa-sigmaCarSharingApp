package com.sigmatechnology.csa.repository;

import com.sigmatechnology.csa.entity.user.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lucianahaugen on 11/09/17.
 */

/**
 * Through ComandLineRunner interface; This class will be automatically executed by SpringBoot application at start up.
 * And we can populate our database exactly after the application gets started.
 */
@Component
public class DbSeeder implements CommandLineRunner {

    private UserRepo userRepository;

    public DbSeeder() {
    }

    public DbSeeder(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        User user1 = new User("luh", true, 0, 0);
        User user2 = new User( "nig", true, 1, 400);
        User user3 = new User("sof", false, 0, 0);
        User user4 = new User("bob", true, 5, 200);
        User user5 = new User("denis", true, 4, 400);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        this.userRepository.create(user1);
        this.userRepository.create(user2);
        this.userRepository.create(user3);
        this.userRepository.create(user4);
        this.userRepository.create(user5);



    }
}
