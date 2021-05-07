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
        return myPlaylist;
    }

    public List<Song> getSongsByName(String name) {
        List<Song> resultList = new ArrayList<>();
        for (Song song : myPlaylist) {
            if (song.getName().equals(name)) {
                resultList.add(song);
            }
        }
        return resultList;
    }

    public List<Song> getSongsByArtist(String artist) {
        List<Song> resultList = new ArrayList<>();
        for (Song song : myPlaylist) {
            if (song.getArtist().equals(artist)) {
                resultList.add(song);
            }
        }
        return resultList;
    }

    public Song updateSongById(int id) {
        return null;
    }

    public Song deleteSongById(int anyInt) {
        return null;
    }
}
