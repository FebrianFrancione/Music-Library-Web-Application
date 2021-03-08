package com.example.demo.DAO;
import com.example.demo.Entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Integer>{

}
