package com.kennysiyun.playlist;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistService {
    List<Song> myPlaylist = new ArrayList<>();

    public void addSong(Song song) {
        myPlaylist.add(song);
    }

    public Song getSongById(int anyInt) {
        for (Song song : myPlaylist) {
            if (anyInt == song.getId()) {
                return song;
            }
        }
        return null;
    }

    public List<Song> getAllSongs() {
        return null;
    }
}
