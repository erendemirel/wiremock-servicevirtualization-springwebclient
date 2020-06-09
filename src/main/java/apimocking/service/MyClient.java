package apimocking.service;

import apimocking.dto.ServiceData;
import apimocking.endpoints.MyClientUrls;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Slf4j
public class MyClient {

    private WebClient webClient;

    public MyClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<ServiceData> retrieveAll() {

        return webClient.get().uri(MyClientUrls.GET_ALL)
                .retrieve()
                .bodyToFlux(ServiceData.class)
                .collectList()
                .block();
    }
}