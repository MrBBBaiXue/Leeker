package com.bakneko;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Utility {

    public static String GetHttpResponse(String url)
    throws IOException, InterruptedException {
        // 发送 https 请求
        var request= HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client= HttpClient.newBuilder().build();
        HttpResponse<String>response=client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}