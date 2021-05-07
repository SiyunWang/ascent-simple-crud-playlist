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
}