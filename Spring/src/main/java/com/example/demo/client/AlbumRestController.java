package com.example.demo.client;

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
@RequestMapping(value = "/album/html", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
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

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String showHomeHtml(Model model){
        model.addAttribute("album", new Album());
        model.addAttribute("message", "You successfully uploaded !");
        return "/Home";
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

    @GetMapping("/find")
    public String getAlbumHtml(Model model, @ModelAttribute("album") Album album) {
        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty()){
            return "Error";
        }
        Album foundAlbum = albumService.findByISRCAndTitle(album.getISRC(), album.getTitle());
        if(foundAlbum == null){
            return "Error";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album", foundAlbum);
        return "Album";
    }

    @PostMapping( "/create")
    public String createNewAlbum(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty() || cover_image_name.isEmpty()){
            return "Error";
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.createNewAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name(), cover_image_name, image_mime, cover_imageBytes)){
                return "Created";
            }else{
                return "Error";
            }

        }
    }

    @DeleteMapping( "/delete")
    public String deleteAlbum(@ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
        if (ISRC.trim().isEmpty()) {
            return "Error";
        } else {

            if (albumService.deleteAlbum(ISRC)) {
                return "Deleted";
            } else {
                return "Error";
            }
        }
    }

    @PutMapping( "/update")
    public String modifyAlbum(@ModelAttribute("album") Album album) throws FileNotFoundException {
        if(album.getISRC().trim().isEmpty() || album.getTitle().isEmpty() || album.getYear() == 0 || album.getArtist_first_name().isEmpty() || album.getArtist_last_name().isEmpty()){
            return "Error";
        }else{
            if(albumService.modifyAlbum(album.getISRC(), album.getTitle(), album.getDescription(), album.getYear(), album.getArtist_first_name(), album.getArtist_last_name())){
                return "Modified";
            }else {
                return "Error";
            }
        }
    }

    // Cover Image
    @GetMapping("/find/img")
    public String getCoverImage(Model model, @ModelAttribute("album") Album album){
        Album foundAlbum = albumService.getCoverImage(album.getISRC(), album.getTitle());
        if(foundAlbum == null){
            return "Error";
        }
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album", foundAlbum);
        return "Images";
    }

    @PutMapping( "/update/img")
    public String modifyCover(@ModelAttribute("album") Album album, @RequestParam("file") MultipartFile file) throws IOException {

        String cover_image_name = FilenameUtils.removeExtension(file.getOriginalFilename());
        String image_mime = FilenameUtils.getExtension(file.getOriginalFilename());
        byte[] cover_imageBytes = file.getBytes();

        if(album.getISRC().trim().isEmpty() || cover_image_name.isEmpty()){
            return "Error";
        }
        else{
            // Check if ISRC already existed or not
            if(albumService.updateCoverImage(album.getISRC(), cover_image_name, image_mime, cover_imageBytes)){
                return "Modified";
            }else{
                return "Error";
            }

        }
    }

    @DeleteMapping( "/delete/img")
    public String deleteImg(@ModelAttribute("album") Album album) {
        String ISRC = album.getISRC();
        if (ISRC.trim().isEmpty()) {
            return "Error";
        } else {

            if (albumService.deleteCoverImage(ISRC)) {
                return "Deleted";
            } else {
                return "Error";
            }
        }
    }
}
