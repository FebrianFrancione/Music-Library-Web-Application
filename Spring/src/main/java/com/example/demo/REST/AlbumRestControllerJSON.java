package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Exceptions.RepException;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/album/json", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class AlbumRestControllerJSON implements WebMvcConfigurer {

    private AlbumService albumService;

    @Autowired
    public AlbumRestControllerJSON(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity getHome() {
        return ResponseEntity.ok("Home Page - Enter a request");
    }

    @GetMapping(value = "/list")
    @ResponseBody
    public ResponseEntity getAlbums() {
        List<Album> entityList = albumService.getAlbums();
        if(entityList != null) {
            return ResponseEntity.ok(entityList);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Albums found!");
        }
    }

    @GetMapping(value = "/{ISRC}/{title}")
    @ResponseBody
    public ResponseEntity getAlbum(@PathVariable String ISRC, @PathVariable("title") String title){
        if(ISRC.trim().isEmpty() || title.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Album album = albumService.findByISRCAndTitle(ISRC, title);
        if(album != null){
            return ResponseEntity.ok(album);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album: " + ISRC + " not found!");
        }
    }

    // Must include file
    @PostMapping(value = "/create")
    @ResponseBody
    public ResponseEntity createNewAlbum(@RequestPart("ISRC") String ISRC, @RequestPart("title") String title, @RequestPart("description") String description,@RequestPart("year") String year, @RequestPart("artist_first_name") String artist_first_name, @RequestPart("artist_first_name") String artist_last_name, @RequestPart("cover_image") MultipartFile cover_image) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(cover_image.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(cover_image.getOriginalFilename());
        byte[] cover_imageBytes = cover_image.getBytes();

        int yearInt = Integer.parseInt(year);

        if(ISRC.trim().isEmpty() || title.isEmpty() || yearInt == 0 || artist_first_name.isEmpty() || artist_last_name.isEmpty() || cover_image_name.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        else{
            if(albumService.createNewAlbum(ISRC,title, description, yearInt, artist_first_name, artist_last_name,cover_image_name, image_mime, cover_imageBytes)){
                return ResponseEntity.ok("Successfully created new album: " + ISRC);
            }else{
                String message = "The album already existed.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }

        }
    }

    @DeleteMapping(value = "/delete/{ISRC}")
    @ResponseBody
    public ResponseEntity deleteAlbum(@PathVariable("ISRC") String ISRC) {
        if(ISRC.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC cannot be empty");
        }else{
            if(albumService.deleteAlbum(ISRC) == true) {
                return ResponseEntity.ok("Successfully deleted the album: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC has not been found");
            }
        }
    }

    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public ResponseEntity modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(ISRC.trim().isEmpty() || title.isEmpty() || year == 0 || artist_first_name.isEmpty() || artist_last_name.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A parameter is incorrect or is empty!");
        }else{
            if(albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name)){
                return ResponseEntity.ok("Successfully modified the album: " + ISRC);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Album has not been modified, errors in parameters are present");
            }
        }
    }


    // Cover Image
    @GetMapping(value = "/img/{ISRC}/{title}")
    @ResponseBody
    public ResponseEntity getImg(@PathVariable String ISRC, @PathVariable("title") String title){
        if(ISRC.trim().isEmpty() || title.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        Album album = albumService.getCoverImage(ISRC, title);
        if(album != null){
            if(album.getCover_image_name().isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album: " + ISRC + " does not have a cover image.");
            }
            return ResponseEntity.ok(album.getCover_image());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album: " + ISRC + " not found!");
        }
    }

    @PutMapping( "/img/update")
    @ResponseBody
    public ResponseEntity modifyCover(@RequestPart("ISRC") String ISRC, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(ISRC.trim().isEmpty() || cover_image_name.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.updateCoverImage(ISRC, cover_image_name, image_mime, cover_imageBytes)){
                return ResponseEntity.ok("Successfully modified the image: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The image does not exist.");
            }

        }
    }

    @DeleteMapping(value = "/img/delete/{ISRC}")
    @ResponseBody
    public ResponseEntity deleteImg(@RequestPart("ISRC") String ISRC) {
        if(ISRC.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC cannot be empty");
        }else{
            if(albumService.deleteCoverImage(ISRC)) {
                return ResponseEntity.ok("Successfully deleted the album: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC has not been found");
            }
        }
    }






    // Old Version

    /* Get a specific album

    @GetMapping(value = "/{ISRC}/{title}", produces = MediaType.TEXT_HTML_VALUE)
    public String getAlbumHtml(Model model, @PathVariable String ISRC, @PathVariable("title") String title) throws Exception {
        Album album = albumService.findByISRCAndTitle(ISRC, title);
        if(album == null){
            return "Error";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album1", album);
        return "Album";
    }
     */

    /* Post

    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
    public String createNewAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(ISRC.trim().isEmpty() || title.isEmpty() || year == 0 || artist_first_name.isEmpty() || artist_last_name.isEmpty()){
            return "Error";
        }
        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Created";
    }
     */

    /* Not used

    @PostMapping(value = "/upload")
    @ResponseBody
    public ResponseEntity uploadFile(@RequestPart("ISRC") String ISRC, @RequestPart("title") String title, @RequestPart("cover_image") MultipartFile cover_image) throws IOException {
        if(!ISRC.trim().isEmpty() || !cover_image.isEmpty()){
            String cover_image_name = FilenameUtils.removeExtension(cover_image.getOriginalFilename());
            String image_mime = FilenameUtils.getExtension(cover_image.getOriginalFilename());
            byte[] cover_imageBytes = cover_image.getBytes();
            Album album = albumService.upload(ISRC, title, cover_image_name, image_mime, cover_imageBytes);

            if(album != null){
                return ResponseEntity.ok("Successfully uploaded cover image info: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("One of the parameters is wrong");
            }
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC or Image cannot be empty");
        }

    }

     */

    /* Delete

    @DeleteMapping(value = "/delete/{ISRC}")
    @ResponseBody
    public ResponseEntity deleteAlbum(@PathVariable("ISRC") String ISRC) {
        if(ISRC.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC cannot be empty");
        }else{
            if(albumService.deleteAlbum(ISRC) == true) {
                return ResponseEntity.ok("Successfully deleted the album: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC has not been found");
            }
        }
    }

    @DeleteMapping(value = "/delete/{ISRC}", produces = MediaType.TEXT_HTML_VALUE)
    public String deleteAlbumHtml(@PathVariable("ISRC") String ISRC) {
        if (ISRC.trim().isEmpty()) {
            return "Error";
        } else {
            if (albumService.deleteAlbum(ISRC) == true) {
                return "Deleted";
            } else {
                return "Error";
            }
        }
    }
     */


    /* PUT

    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public ResponseEntity modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name)){
            return ResponseEntity.ok("Successfully modified the album: " + ISRC);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Album has not been modified, errors in parameters are present");
        }

    }

    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
    public String modifyAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name)){
            return "Modified";
        }else {
            return "Error";
        }
    }
     */




}
