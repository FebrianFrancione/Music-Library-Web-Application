package services;

import core.Artist;
import implementation.ArtistsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet(name = "ArtistsServlet", urlPatterns = {"/artists"})
public class ArtistsServlet extends HttpServlet {
    private static final long serialVersionUID = 4L;
    private ArtistsManager artistsManager;
    private String message;
    private int status = HttpServletResponse.SC_OK; //By default 200 OK

    public void init(){
        artistsManager = new ArtistsManager();
        artistsManager.setServletContext(this.getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = (request.getParameter("nickname") == null || request.getParameter("nickname").isEmpty())
                ? null : request.getParameter("nickname");
        String first_name = (request.getParameter("first_name") == null) ? null : request.getParameter("first_name");
        String last_name = (request.getParameter("last_name") == null) ? null : request.getParameter("last_name");
        String biography = (request.getParameter("biography") == null) ? null : request.getParameter("biography");

        //Still need to set the HTTP response status and code
        if(nickname == null || first_name == null || last_name == null || biography == null){
            message = "Error: all fields are mandatory\n";
            status = HttpServletResponse.SC_BAD_REQUEST;
        }

        else{
            Artist newArtist = artistsManager.createArtist(nickname, first_name, last_name, biography);

            if(newArtist != null){
                message = "Artist created:\n" + newArtist + "\n";
                status = HttpServletResponse.SC_CREATED;
            }
            else{
                message = nickname + " already exists, please use a unique nickname\n";
                status = HttpServletResponse.SC_BAD_REQUEST;
            }
        }

        sendResponse(response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nickname = request.getParameter("nickname");

        if(nickname == null || nickname.isEmpty()) {
            CopyOnWriteArrayList<Artist> artists = artistsManager.getList();

            message = artists.isEmpty() ? "There are no artists to display\n"
                    : ("Artists :\n" + artistsManager.getAllArtistsByNickname() + "\n");

            status = HttpServletResponse.SC_OK;
        }else{
            Artist artist = artistsManager.getArtist(nickname);
            if(artist != null){
                message = artist + "\n";
                status = HttpServletResponse.SC_OK;
            }
            else{
                message = " Artist " + nickname + " not found\n";
                status = HttpServletResponse.SC_NOT_FOUND;
            }
        }

        sendResponse(response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ArrayList<String> fields = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String data = br.readLine();
        String[] args = data.split("#");

        if(args.length != 4){
            message = "Error: all fields are mandatory\n";
            status = HttpServletResponse.SC_BAD_REQUEST;
            sendResponse(response);
            return;
        }

        else{
            for (String arg : args) {

                if(!validateValues(arg)){
                    sendResponse(response);
                    return;
                }

                fields.add(arg.split("=")[1]);
            }
        }

        if (artistsManager.updateArtist(fields.get(0), fields.get(1), fields.get(2), fields.get(3))) {
            message = "Artist updated: \n" + artistsManager.getArtist(fields.get(0)) + "\n";
            status = HttpServletResponse.SC_OK;
        } else {
            message = " Artist " + fields.get(0) + " not found\n";
            status = HttpServletResponse.SC_NOT_FOUND;
        }

        sendResponse(response);

    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String nickname = request.getParameter("nickname");

        if(nickname == null || nickname.isEmpty()){
            message = "Error: nickname field is missing\n";
            status = HttpServletResponse.SC_BAD_REQUEST;
        }

        else{
            if(artistsManager.deleteArtist(nickname)){
                message = "Artist " + nickname + " successfully deleted!\n";
                status = HttpServletResponse.SC_OK;
            }

            else{
                message = "Artist " + nickname + " was not found\n";
                status = HttpServletResponse.SC_NOT_FOUND;
            }
        }

        sendResponse(response);
    }

    private boolean validateValues(String arg){

        String[] keyValues = arg.split("=");

        if(keyValues.length != 2){
            message = "Error: all fields are mandatory\n";
            status = HttpServletResponse.SC_BAD_REQUEST;
            return false;
        }

        String value = keyValues[1];

        if (value == null || value.isEmpty()) {
            message = "Error: all fields are mandatory\n";
            status = HttpServletResponse.SC_BAD_REQUEST;
            return false;
        }

        return true;
    }

    private void sendResponse(HttpServletResponse response){
        try{
            response.setStatus(status);
            OutputStream out = response.getOutputStream();
            out.write(message.getBytes());
            out.flush();
        }
        catch (Exception e){
            System.out.println("Error sending response");
        }
    }
}