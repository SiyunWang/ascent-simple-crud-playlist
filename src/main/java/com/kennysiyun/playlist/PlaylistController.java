package com.kennysiyun.playlist;

import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Song addSong(@RequestBody Song song) {
        return playlistService.addSong(song);
    }

    @PutMapping("/{id}")
    public Song updateSongById(@PathVariable int id) {
        return playlistService.updateSongById(id);
    }

    @DeleteMapping("/{id}")
    public Song deleteSongById(@PathVariable int id) {
        return playlistService.deleteSongById(id);
    }
}
