package com.socialapp.repositories;

import com.socialapp.entities.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepo extends JpaRepository<Bookmark, Long > {
    List<Bookmark> findByUserId(Long userId);
}
