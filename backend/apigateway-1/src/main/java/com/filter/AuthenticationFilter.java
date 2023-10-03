package com.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import com.exception.UnauthorisedException;
import com.util.JwtUtil;

import reactor.core.publisher.Mono;

import org.springframework.core.io.buffer.DataBuffer;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
        	ServerHttpRequest request = null;
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
            	
            	System.out.println("There");
            	try {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                	System.out.println("inside if");
                    throw new UnauthorisedException("Missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                System.out.println(authHeader);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                	System.out.println("authheader");
                    authHeader = authHeader.substring(7);
                }
                
                	System.out.println("try");
//                    //REST call to AUTH service
//                    template.getForObject("http://IDENTITY-SERVICE//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);
                    
                    request = exchange.getRequest().mutate().header("User", jwtUtil.extractUsername(authHeader)).build();
                    
                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    ServerHttpResponse response = exchange.getResponse();
                    
                    // Set HTTP status code (e.g., 200 OK)
                    response.setStatusCode(HttpStatus.FORBIDDEN);
                    
                    // Set response headers (if needed)
                    response.getHeaders().add("Content-Type", "application/json");
                    
                    // Create the response content
                    String responseBody = "{\"message\": \"Unauthorised access \"}";

                    // Convert the response content to a DataBuffer
                    DataBuffer buffer = response.bufferFactory().wrap(responseBody.getBytes());

                    // Set the response content using writeWith
                    return response.writeWith(Mono.just(buffer));
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });
    }
    public static class Config {

    }
}
