package jobaskronom.demo.infranstructure;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

@Component
public class HttpClient {
    private final WebClient webClient = WebClient.create();

    public <T> T doPost(String url, Class<T> type, Consumer<HttpHeaders> headersConsumer) {
        try {
            return webClient.get()
                    .uri(url)
                    .headers(headersConsumer)
                    .retrieve()
                    .toEntity(type)
                    .toFuture()
                    .get()
                    .getBody();
        } catch (InterruptedException | ExecutionException e) {
//            logger
            System.out.println("Вызов POST запроса по url = " + url + ", завершился с ошибкой. " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
