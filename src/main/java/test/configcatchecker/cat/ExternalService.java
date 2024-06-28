package test.configcatchecker.cat;

import jakarta.annotation.PreDestroy;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class ExternalService {
    private static final int NUMBER_OF_THREADS = 1000;
    private static final int NUMBER_OF_REQUESTS_IN_THREAD = 100;
    private ExecutorService service = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    @Resource
    private CatService catService;

    public void starter() {
        for (int i = 0; i < NUMBER_OF_THREADS; i++) {
            service.execute(() -> {
                for(int j = 0; j < NUMBER_OF_REQUESTS_IN_THREAD; j ++) {
                    catService.executeTestRun();
                }
                System.out.println("Thread  " + Thread.currentThread().getName() + " Finished");
            });
        }
    }

    @PreDestroy
    public void preDestroy() {
        service.shutdown();
    }
}
