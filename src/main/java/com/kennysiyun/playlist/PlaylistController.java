package com.kennysiyun.playlist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class PlaylistController {
    PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{id}")
    public Song getSongById(@PathVariable int id) {
        return playlistService.getSongById(id);
    }

    @GetMapping
    public List<Song> getAllSongs() {
        return playlistService.getAllSongs();
    }
}
