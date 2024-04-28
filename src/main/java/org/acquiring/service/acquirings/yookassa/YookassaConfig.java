package org.acquiring.service.acquirings.yookassa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@Slf4j
public class YookassaConfig
{

    @Value("${acquiring.yookassa.restClient.url}")
    String restClientUrl;

    @Value("${acquiring.yookassa.shopId}")
    String shopId;

    @Value("${acquiring.yookassa.token}")
    String token;

    @Bean
    YookassaRestClient yookassaRestClient(){
        RestClient client = RestClient.builder()
                .baseUrl(restClientUrl)
                .build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter
                                    .create(client))
                .build();
        return factory.createClient(YookassaRestClient.class);
    }

    @Bean
    YookassaParams yookassaParams(){
        YookassaParams params = new YookassaParams();
        params.setToken(token);
        params.setShopId(shopId);

        log.info("Init yookassa params:\n  shopid:{}\n  token:{}",shopId,token.substring(0, 3) + "*****");
        return  params;
    }

}
