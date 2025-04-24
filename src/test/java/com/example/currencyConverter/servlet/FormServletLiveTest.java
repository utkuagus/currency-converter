package com.example.currencyConverter.servlet;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormServletLiveTest {

    @Test
    public void whenPostRequestUsingHttpClient_thenCorrect()
            throws Exception {

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost method = new HttpPost(
                "http://localhost:8080/myapp/calculateServlet");

        List<BasicNameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("height", String.valueOf(2)));
        nvps.add(new BasicNameValuePair("weight", String.valueOf(80)));

        method.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse httpResponse = client.execute(method);

        assertEquals("Success", httpResponse
                .getHeaders("Test")[0].getValue());
        assertEquals("20.0", httpResponse
                .getHeaders("BMI")[0].getValue());
    }
}