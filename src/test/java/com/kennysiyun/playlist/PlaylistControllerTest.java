package com.kennysiyun.playlist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest
class PlaylistControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlaylistService playlistService;

    List<Song> songs;

    @BeforeEach
    void setUp() {
        songs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            songs.add(new Song(i, "songName" + i, "artist" + i, "album" + i));
        }
    }

    @Test
    void getSongById() throws Exception {
        Song expected = new Song(9, "random name", "random artist");
        when(playlistService.getSongById(9)).thenReturn(expected);
        mockMvc.perform(get("/songs/9"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(9));
    }

}