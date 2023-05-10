package Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class Controller {

    private DataService dataService;

    public Controller(DataService dataService) {
        this.dataService = dataService;
    }

    @RequestMapping("/data")
    public String getAllData() {
        return dataService.getAllData();
    }

}
