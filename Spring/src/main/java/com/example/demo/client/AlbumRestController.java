package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ImageUtil;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping(value = "/album", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class AlbumRestController implements WebMvcConfigurer {


    private AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/Get")
    public String showOne(Model model){
        model.addAttribute("album", new Album());
        return "Album";
    }

    @GetMapping("/Post")
    public String showPost(Model model){
        model.addAttribute("album", new Album());
        return "Post";
    }
    @GetMapping("/Delete")
    public String showDelete(Model model){
        model.addAttribute("album", new Album());
        return "Delete";
    }

    @GetMapping("/Modify")
    public String showModify(Model model){
        model.addAttribute("album", new Album());
        return "Modify";
    }

    @GetMapping("/Images")
    public String showImages(Model model){
        model.addAttribute("album", new Album());
        return "Images";
    }

    @GetMapping(value = "/")
    @ResponseBody
    public ResponseEntity getHome() {
        return ResponseEntity.ok("Home Page - Enter a request");
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHomeHtml(Model model){
//        if (cover_image.isEmpty()) {
//            model.addAttribute("message", "Please select a file to upload.");
//            return "redirect:/Home";
//        }
//        String cover_image_name = StringUtils.cleanPath(cover_image.getOriginalFilename());

//        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
//        model.addAttribute("message", "You successfully uploaded " + cover_image_name + '!');
        model.addAttribute("album", new Album());
//        model.addAttribute("message", "You successfully uploaded !");
        return "/Home";
    }

    /* Get a specific album

    @GetMapping(value = "/{ISRC}/{title}")
    @ResponseBody
    public ResponseEntity getAlbum(@PathVariable String ISRC, @PathVariable("title") String title){
        Album album = albumService.findByISRCAndTitle(ISRC, title);
        if(album != null){
            return ResponseEntity.ok(album);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Album: " + ISRC + " not found!");
        }
    }

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

    @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
    public String getAlbumsHtml(Model model){
        List<Album> list = albumService.getAlbums();
        if(list == null){
            return "Error";
        }else {
            model.addAttribute("imgUtil", new ImageUtil());
            model.addAttribute(list);
            return "Albums";
        }
    }

    /* Post

    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public ResponseEntity createNewAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(ISRC.trim().isEmpty() || title.isEmpty() || year == 0 || artist_first_name.isEmpty() || artist_last_name.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return ResponseEntity.ok("Successfully created new album: " + ISRC);
    }

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

    //Combining both methods together
    //must be done in postman
    @PostMapping(value = "/upload2")
    @ResponseBody
    public ResponseEntity uploadFile2(@RequestPart("ISRC") String ISRC, @RequestPart("title") String title, @RequestPart("description") String description,@RequestPart("year") String year, @RequestPart("artist_first_name") String artist_first_name, @RequestPart("artist_first_name") String artist_last_name, @RequestPart("cover_image") MultipartFile cover_image) throws IOException {
        String cover_image_name = FilenameUtils.removeExtension(cover_image.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(cover_image.getOriginalFilename());
        byte[] cover_imageBytes = cover_image.getBytes();
        int yearInt = Integer.parseInt(year);
        Album album = albumService.upload2(ISRC,title, description, yearInt, artist_first_name, artist_last_name,cover_image_name, image_mime, cover_imageBytes);
        if(album != null){
            return ResponseEntity.ok("Successfully uploaded album + cover image info: " + ISRC);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("One of the parameters is wrong");
        }

    }



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


    // Browser
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    // GET

    @GetMapping("/find")
//    public String getAlbumHtml(Model model, @ModelAttribute("album") Album album) {
    public String getAlbumHtml(Model model, @ModelAttribute("album") Album album) {
        Album foundAlbum = albumService.findByISRCAndTitle(album.getISRC(), album.getTitle());
        if(foundAlbum == null){
            return "Error";
        }
//        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album", foundAlbum);
        return "Album";
    }


    // POST

    @PostMapping( "/create")
    public String createNewAlbumHtml(Model model, @ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty() || cover_image_name.isEmpty()){
            return "Error";
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.createNewAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name(), cover_image_name, image_mime, cover_imageBytes)){
                model.addAttribute("updated", true);
                return "Post";
            }else{
                return "Error";
            }

        }
    }

    // This also works. If you click the button, the browser will show the message.

    @PostMapping( "/createJson")
    @ResponseBody
    public ResponseEntity createNewAlbum(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty() || cover_image_name.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }else{
            // Check if ISRC already existed or not
            if(albumService.createNewAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name(), cover_image_name, image_mime, cover_imageBytes)){
                return ResponseEntity.ok("Successfully created new album: " + album.getISRC());
            }else{
                String message = "The album already existed.";
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        }
    }




    // Delete

    @DeleteMapping( "/delete")
    public String deleteAlbum(Model model, @ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
        if (ISRC.trim().isEmpty()) {
            return "Error";
        } else {

            if (albumService.deleteAlbum(ISRC)) {
                model.addAttribute("deleted", true);
                return "Delete";
            } else {
                return "Error";
            }
        }
    }

    /*
    @DeleteMapping(value = "/delete")
    @ResponseBody
    public ResponseEntity deleteAlbum(@ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
        if(ISRC.trim().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC cannot be empty");
        }else{
            if(albumService.deleteAlbum(ISRC)) {
                return ResponseEntity.ok("Successfully deleted the album: " + ISRC);
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ISRC has not been found");
            }
        }
    }
     */


    // Put

    @PutMapping( "/update")
    public String modifyAlbum(Model model, @ModelAttribute("album") Album album) throws FileNotFoundException {
        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty()){
            return "Error";
        }else{
            if(albumService.modifyAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name())){
                model.addAttribute("modified", true);
                return "Modify";
            }else {
                return "Error";
            }
        }
    }

    /*
    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("A parameter is incorrect or is empty!");
        }else{
            if(albumService.modifyAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name())){
                return ResponseEntity.ok("Successfully modified the album: " + ISRC);
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Album does not exist.");
            }
        }

    }
     */


    // Get Cover Image

    @GetMapping("/find/img")
    public String getCoverImage(Model model, @ModelAttribute("album") Album album){
        Album foundAlbum = albumService.getCoverImage(album.getISRC(), album.getTitle());
        if(foundAlbum == null){
            return "Error";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album", foundAlbum);
        model.addAttribute("get", true);
        return "Images";
    }


    // Put Cover Image

    @PutMapping( "/update/img")
    public String modifyCover(Model model, @ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || cover_image_name.isEmpty()){
            return "Error";
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.updateCoverImage(album.getISRC(), cover_image_name, image_mime, cover_imageBytes)){
                model.addAttribute("modified", true);
                return "Images";
            }else{
                return "Error";
            }

        }
    }

    /*
    @PutMapping( "/update/img")
    @ResponseBody
    public ResponseEntity modifyCover(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || cover_image_name.isEmpty()){
            String message = "A parameter is incorrect or is empty!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.updateCoverImage(album.getISRC(), cover_image_name, image_mime, cover_imageBytes)){
                return ResponseEntity.ok("Successfully modified the album: " + album.getISRC());
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Album does not exist.");
            }

        }
    }
     */


    // Delete Cover Image

    @DeleteMapping( "/delete/img")
    public String deleteImg(Model model, @ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
        if (ISRC.trim().isEmpty()) {
            return "Error";
        } else {

            if (albumService.deleteCoverImage(ISRC)) {
                model.addAttribute("deleted", true);
                return "Images";
            } else {
                return "Error";
            }
        }
    }

    /*
    @DeleteMapping(value = "/delete/img")
    @ResponseBody
    public ResponseEntity deleteImg(@ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
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
     */





}
