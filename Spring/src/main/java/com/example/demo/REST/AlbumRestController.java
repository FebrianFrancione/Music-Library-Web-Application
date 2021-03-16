package com.example.demo.REST;

import com.example.demo.Entity.Album;
import com.example.demo.Service.AlbumService;
import com.example.demo.Service.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
//@RequestMapping(value = "/album")
@RequestMapping(value = "/album", produces = {MediaType.APPLICATION_JSON_VALUE}, method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT, RequestMethod.DELETE})
public class AlbumRestController implements WebMvcConfigurer {


    private AlbumService albumService;

    @Autowired
    public AlbumRestController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "/")
    @ResponseBody
    public void getHome() {
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
        model.addAttribute("message", "You successfully uploaded !");
        return "/Home";
    }


    @GetMapping(value = "/{ISRC}/{title}")
    @ResponseBody
    public Album getAlbum(@PathVariable String ISRC, @PathVariable("title") String title) throws Exception {
        return albumService.findByISRCAndTitle(ISRC, title);
    }

    @GetMapping(value = "/{ISRC}/{title}", produces = MediaType.TEXT_HTML_VALUE)
    public String getAlbumHtml(Model model, @PathVariable String ISRC, @PathVariable("title") String title) throws Exception {
        Album album = albumService.findByISRCAndTitle(ISRC, title);
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute("album1", album);
        return "Album";
    }


    @GetMapping(value = "/list")
    @ResponseBody
    public List<Album> getAlbums() {
        return albumService.getAlbums();
    }

    @GetMapping(value = "/list", produces = MediaType.TEXT_HTML_VALUE)
    public String getAlbumsHtml(Model model){
        model.addAttribute("imgUtil", new ImageUtil());
        model.addAttribute(albumService.getAlbums());
        return "Albums";
    }


    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public void createNewAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
    }

    @PostMapping(value = "/create/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
    public String createNewAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.createNewAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Created";
    }


    @DeleteMapping(value = "/{ISRC}")
    @ResponseBody
    public void deleteAlbum(@PathVariable("ISRC") String ISRC) {
        albumService.deleteAlbum(ISRC);
    }

    @DeleteMapping(value = "/{ISRC}", produces = MediaType.TEXT_HTML_VALUE)
    public String deleteAlbumHtml(@PathVariable("ISRC") String ISRC) {
        albumService.deleteAlbum(ISRC);
        return "Deleted";
    }

    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}")
    @ResponseBody
    public void modifyAlbum(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
    }

    @PutMapping(value = "/{ISRC}/{title}/{description}/{year}/{artist_first_name}/{artist_last_name}", produces = MediaType.TEXT_HTML_VALUE)
    public String modifyAlbumHtml(@PathVariable("ISRC") String ISRC, @PathVariable("title") String title, @PathVariable("description") String description, @PathVariable("year") int year, @PathVariable("artist_first_name") String artist_first_name, @PathVariable("artist_last_name") String artist_last_name) throws FileNotFoundException {
        albumService.modifyAlbum(ISRC, title, description, year, artist_first_name, artist_last_name);
        return "Modified";
    }


    //CRUD For Cover Images
    //add ISRC to find album
//    http://localhost:8080/album/image/ISRC
//    @GetMapping("/image/{ISRC}")
//    public String getCoverImage(Model model, @PathVariable("ISRC") String ISRC){
//        Album thisAlbum = albumService.getCoverImage(ISRC);
//        model.addAttribute("imgUtil", new ImageUtil());
//        model.addAttribute("album", thisAlbum);
//        return "Images";
//    }
//
//    @DeleteMapping("/image/delete")
//    public String deleteCoverImage(Model model){
////        albumService.deleteCoverImage();
//        return "Images";
//    }
//
//    @PutMapping("/image/put")
//    public String updateCoverImage(Model model){
////        albumService.updateImage();
//        return "Images";
//    }

}
