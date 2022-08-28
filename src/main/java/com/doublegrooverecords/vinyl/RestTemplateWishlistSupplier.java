package com.doublegrooverecords.vinyl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestTemplateWishlistSupplier implements WishlistSupplier {

    @Value("${services.token}")
    String token;

    @Value("${services.url.base}")
    String baseUrl;

    @Value("${services.url.port}")
    int port;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> fetchAllItemsForUser(Long customerId) throws TimedOutException, CustomerNotFoundException {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = String.format("%s:%d/wishlists/%d", baseUrl, port, customerId);

        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        final RequestEntity<Void> entity = RequestEntity.get(url).headers(headers).build();
        final ResponseEntity<ProductResponse[]> exchange = restTemplate.exchange(entity, ProductResponse[].class);

        return Arrays.stream(exchange.getBody())
                .map(productResponse -> productRepository.findById(productResponse.id))
                .collect(Collectors.toList());
    }

    static class ProductResponse {
        private Long id;

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Override
    public boolean addItemToWishlist(Long customerId, Product product) throws TimedOutException, CustomerNotFoundException {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = String.format("%s:%d/wishlists/%d/add", baseUrl, port, customerId);

        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        try {
            final ResponseEntity<Void> exchange = restTemplate.exchange(RequestEntity.post(url).headers(headers).body(product), Void.class);

            if (exchange.getStatusCode().is2xxSuccessful())
                return true;
        } catch (HttpClientErrorException.Conflict e) {
            return false;
        }
        return false;
    }

    public boolean clearAll(Long customerId) {
        final RestTemplate restTemplate = new RestTemplate();
        final String url = String.format("%s:%d/wishlists/%d/clear_all", baseUrl, port, customerId);

        final HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        final ResponseEntity<Void> exchange = restTemplate.exchange(RequestEntity.post(url).headers(headers).build(), Void.class);

        if (exchange.getStatusCode().is2xxSuccessful())
            return true;

        return false;
    }
}
