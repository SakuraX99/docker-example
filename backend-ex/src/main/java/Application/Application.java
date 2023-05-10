package Application;


import Application.domain.Test;
import Application.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class Application {


    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(TestRepository testRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                String serviceName = environment.getProperty("SVC_ID");
                Test test = new Test(serviceName);
                testRepository.save(test);
            }
        };
    }


    @RequestMapping("/")
    public String index() {
        return "wubba lubba dub dub";
    }


    @RequestMapping("/hello")
    public String helloWorld() {
        String serviceName = environment.getProperty("REQUESTED_SERVICE_NAME");
        String url = "http://" + serviceName + ":8080/welcome";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    @RequestMapping("/welcome")
    public String welcome() {
        String serviceName = environment.getProperty("SVC_ID");
        return "Welcome! I'm " + serviceName;
    }


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
