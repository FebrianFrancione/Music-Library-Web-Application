package client;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.Scanner;

public class ConcurrentClients {

    private static PoolingHttpClientConnectionManager cm;

    public static void main(String[] args) throws InterruptedException {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(10);
        cm.setDefaultMaxPerRoute(10);
        showAll();
        addAlbums();
        updateAlbums();
        showAll();
    }

    private static void showAll() throws InterruptedException{
        CloseableHttpClient client1 = HttpClients.custom().setConnectionManager(cm).build();
        CloseableHttpClient client2 = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet get1 = new HttpGet("http://localhost:8080/core/album/list");
        HttpGet get2 = new HttpGet("http://localhost:8080/core/album/list");

        GetThread thread1 = new GetThread(client1, get1);
        GetThread thread2 = new GetThread(client2, get2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    public static void readResponse(CloseableHttpResponse response) throws IOException{
        Scanner sc = new Scanner(response.getEntity().getContent());
        StringBuilder result = new StringBuilder();
        while(sc.hasNext()){
            result.append(sc.nextLine());
            result.append("\n");
        }
        response.close();
        System.out.println(result.toString());
    }

    private static void updateAlbums() throws InterruptedException{
        String[] updatedUrls = {
                "http://localhost:8080/core/album/01album/updated_title/updated_desc/2021/updated_artist",
                "http://localhost:8080/core/album/04album/updated_title2/updated_desc2/2020/updated_artist2"
        };

        CloseableHttpClient client1 = HttpClients.custom().setConnectionManager(cm).build();
        HttpPut request = new HttpPut(updatedUrls[0]);
        MultiHttpClientConnThread thread1 = new MultiHttpClientConnThread(client1, request);

        CloseableHttpClient client2 = HttpClients.custom().setConnectionManager(cm).build();
        HttpPut request2 = new HttpPut(updatedUrls[1]);
        MultiHttpClientConnThread thread2 = new MultiHttpClientConnThread(client2, request2);

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }

    private static void addAlbums()throws InterruptedException {
        String[] Posturls = {
                "http://localhost:8080/core/album/create/01album/01Title/Description1/2015/Artist1",
                "http://localhost:8080/core/album/create/02album/02Title/Description2/2016/Artist2",
                "http://localhost:8080/core/album/create/03album/03Title/Description3/2017/Artist3",
                "http://localhost:8080/core/album/create/04album/04Title/Description4/2017/Artist4"
        };

        CloseableHttpClient[] clients = {
                HttpClients.custom().setConnectionManager(cm).build(),
                HttpClients.custom().setConnectionManager(cm).build(),
                HttpClients.custom().setConnectionManager(cm).build(),
                HttpClients.custom().setConnectionManager(cm).build()
        };

        PostThread[] threads = new PostThread[clients.length];
        for (int i = 0; i < threads.length; i++) {
            HttpPost post = new HttpPost(Posturls[i]);
            threads[i] = new PostThread(clients[i], post);
        }

        // start the threads
        for (PostThread thread : threads) {
            thread.start();
        }

        // join the threads
        for (PostThread thread : threads) {
            thread.join();
        }
    }

    static class GetThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpGet get;

        public GetThread(CloseableHttpClient httpClient, HttpGet get) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.get = get;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(
                        get, context);
                Thread.sleep(3000);
                readResponse(response);
            } catch (ClientProtocolException ex) {
                System.out.println(ex);
            } catch (IOException | InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }

    static class PostThread extends Thread {

        private final CloseableHttpClient httpClient;
        private final HttpContext context;
        private final HttpPost httpPost;

        public PostThread(CloseableHttpClient httpClient, HttpPost httpPost) {
            this.httpClient = httpClient;
            this.context = HttpClientContext.create();
            this.httpPost = httpPost;
        }

        @Override
        public void run() {
            try {
                CloseableHttpResponse response = httpClient.execute(
                        httpPost, context);
                Thread.sleep(1000);
                readResponse(response);
            } catch (ClientProtocolException ex) {
                // Handle protocol errors
            } catch (IOException ex) {
                // Handle I/O errors
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MultiHttpClientConnThread extends Thread {
        private CloseableHttpClient client;
        private HttpPut put;

        public MultiHttpClientConnThread(CloseableHttpClient httpClient, HttpPut httpPut){
            this.client = httpClient;
            this.put = httpPut;
        }
        // standard constructors
        public void run(){
            try {
                CloseableHttpResponse response = client.execute(put);
                Thread.sleep(100);
                readResponse(response);
            } catch (ClientProtocolException ex) {
            } catch (IOException ex) {
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
