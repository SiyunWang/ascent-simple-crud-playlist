package com.kennysiyun.playlist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistService {
    List<Song> myPlaylist = new ArrayList<>();

    public Song addSong(Song song) {
        myPlaylist.add(song);
        return song;
    }

    public Song getSongById(int id) {
        for (Song song : myPlaylist) {
            if (id == song.getId()) {
                return song;
            }
        }
        return null;
    }

    public List<Song> getAllSongs() {
        return null;
    }

    public Song updateSongById(int id) {
        return null;
    }
}
