package apimockingtest;


import apimocking.dto.ServiceData;
import apimocking.service.MyClient;
import com.github.jenspiegsa.wiremockextension.ConfigureWireMock;
import com.github.jenspiegsa.wiremockextension.InjectServer;
import com.github.jenspiegsa.wiremockextension.WireMockExtension;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.ConsoleNotifier;
import com.github.tomakehurst.wiremock.core.Options;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;


@ExtendWith(WireMockExtension.class)
public class MyClientTest {

    Logger logger = Logger.getLogger(MyClientTest.class);


    MyClient myClient;
    WebClient webClient;

    @InjectServer
    WireMockServer wireMockServer;

    @ConfigureWireMock
    Options options = wireMockConfig().
            port(8089)
            .notifier(new ConsoleNotifier(true));


    @BeforeEach
    public void setUp() {

        logger.info("Host port : " + wireMockServer.port());
        String baseUrl = String.format("http://localhost:%s/", 8085);
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(baseUrl);
        WebClient.builder().uriBuilderFactory(factory).build();
        webClient = WebClient.create(baseUrl);
        WireMock.configureFor("localhost", wireMockServer.port());
        myClient = new MyClient(webClient);
    }

    @Test
    public void retrieveAll() {

        stubFor(get(anyUrl())
                .willReturn(WireMock.aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBodyFile("service-returned-data.json")));
        List<ServiceData> serviceDataList = myClient.retrieveAll();
        logger.info("Status: " + serviceDataList.get(0).getStatus());
        logger.info("Data : " + Arrays.toString(serviceDataList.get(0).getData()));
        Assertions.assertTrue(serviceDataList.size() > 0);
        Assertions.assertEquals(serviceDataList.get(0).getStatus(), "success");
        //Is "Tiger Nixon" an employee?
        Assertions.assertTrue(Arrays.toString(serviceDataList.get(0).getData()).contains("Tiger Nixon"));
    }
}
