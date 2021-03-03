package client;

import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ArtistsClient {

    public void showAll(){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet request = new HttpGet("http://localhost:8980/demo_war_exploded/artists");
            ResponseHandler<String> responseHandler = readResponse();
            String result = client.execute(request, responseHandler);
            System.out.println(result);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void getArtist(String nickname){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpGet request = new HttpGet("http://localhost:8980/demo_war_exploded/artists?nickname=" + nickname);
            ResponseHandler<String> responseHandler = readResponse();
            String result = client.execute(request, responseHandler);
            System.out.println();
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void sendArtist(String nickname, String first_name, String last_name, String biography){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpPost request = new HttpPost("http://localhost:8980/demo_war_exploded/artists?nickname=" + nickname + "&first_name=" + first_name + "&last_name=" + last_name + "&biography=" + biography);
            ResponseHandler<String> responseHandler = readResponse();
            String result = client.execute(request, responseHandler);
            System.out.println();
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void updateArtist(String nickname, String first_name, String last_name, String biography){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpPut request = new HttpPut("http://localhost:8980/demo_war_exploded/artists");
            request.setEntity(new StringEntity("nickname=" + nickname + "#first_name=" + first_name + "#last_name=" + last_name + "#biography=" + biography));
            ResponseHandler<String> responseHandler = readResponse();
            String result = client.execute(request, responseHandler);
            System.out.println();
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void deleteArtist(String nickname){
        try(CloseableHttpClient client = HttpClients.createDefault()){
            HttpDelete request = new HttpDelete("http://localhost:8980/demo_war_exploded/artists?nickname=" + nickname);
            ResponseHandler<String> responseHandler = readResponse();
            String result = client.execute(request, responseHandler);
            System.out.println();
            System.out.println(result);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static ResponseHandler readResponse() {
        ResponseHandler<String> responseHandler = response -> {
            int status = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity) : null;
        };

        return responseHandler;
    }
}

//curl -v -d "nickname=febu&first_name=feb&last_name=fran&biography=dsdsdsds" http://localhost:8980/demo_war/artists

// curl -v http://localhost:8980/demo_war/artists