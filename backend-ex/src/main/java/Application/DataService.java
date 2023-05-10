package Application;

import Application.domain.Test;
import Application.repository.TestRepository;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    private TestRepository testRepository;

    public DataService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public String getAllData() {
        String data = "";
        for (Test t : testRepository.findAll()){
            data = data + "(" + t.getTestName() + ") ";
        }
        return "data: " + data;
    }
}
