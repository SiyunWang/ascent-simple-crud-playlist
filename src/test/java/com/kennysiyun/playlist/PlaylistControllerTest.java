package com.kennysiyun.playlist;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void getAllSongs() throws Exception {
        when(playlistService.getAllSongs()).thenReturn(songs);
        mockMvc.perform(get("/songs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(10)));
    }

    @Test
    void addSong() throws Exception {
        Song expected = new Song(9, "random-name", "random-artist");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(expected);
        when(playlistService.addSong(any(Song.class))).thenReturn(expected);
        mockMvc.perform(post("/songs")
                            .content("{\"id\":\"9\",\"name\":\"random-name\",\"artist\":\"random-artist\",\"album\":\"default\"}")
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("random-name"));
    }

    @Test
    void updateSongById() throws Exception {
        Song expected = new Song (1, "New Song", "New Artist", "New Album");
        when(playlistService.updateSongById(anyInt())).thenReturn(expected);

        mockMvc.perform(put("/songs/1")
                            .content("{\"id\":\"1\",\"name\":\"New Song\",\"artist\":\"New Artist\",\"album\":\"New Album\"}")
                            .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("New Song"));

    }



}