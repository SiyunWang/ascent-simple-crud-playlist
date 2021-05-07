package com.kennysiyun.playlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceTest {

    PlaylistService playlistService;

    List<Song> testPlaylist;

    @BeforeEach
    public void setUp() {
        testPlaylist = new ArrayList<>();
        playlistService = new PlaylistService();
        for (int i = 0; i < 10; i++) {
            Song song = new Song(i, "name" + i, "artist" + i, "album" + i);
            testPlaylist.add(song);
            playlistService.addSong(song);
        }
    }

    @Test
    void getSongById() {
        Song expected = testPlaylist.get(1);
        assertEquals(expected, playlistService.getSongById(expected.getId()));
    }

    @Test
    void getAllSongs() {
        List<Song> expected = testPlaylist;
        assertEquals(expected, playlistService.getAllSongs());
    }

    @Test
    void getSongsByName_Exists() {
        List<Song> expected = new ArrayList<>();
        expected.add(testPlaylist.get(1));
        assertEquals(expected, playlistService.getSongsByName("name1"));
    }

    @Test
    void getSongsByName_NotExists() {
        List<Song> expected = new ArrayList<>();
        assertEquals(expected, playlistService.getSongsByName("name not exist"));
    }

    @Test
    void getSongsByArtist_Exists() {
        List<Song> expected = new ArrayList<>();
        expected.add(testPlaylist.get(2));
        assertEquals(expected, playlistService.getSongsByArtist("artist2"));
    }

    @Test
    void getSongsByArtist_NotExists() {
        List<Song> expected = new ArrayList<>();
        assertEquals(expected, playlistService.getSongsByArtist("artist not exist"));
    }
}