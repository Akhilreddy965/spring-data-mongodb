package com.example.mongodbdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@SpringBootApplication
public class MongodbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbDemoApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(UserRepository repository) {
        return args -> {
            User user = new User("1", "John Doe", "johndoe@example.com");
            repository.save(user);
            System.out.println("User saved: " + user);

            List<User> users = repository.findAll();
            users.forEach(System.out::println);
        };
    }
}

@Document(collection = "users")
class User {
    @Id
    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "User{id='" + id + "', name='" + name + "', email='" + email + "'}";
    }
}

@Repository
interface UserRepository extends MongoRepository<User, String> {
}
