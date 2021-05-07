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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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



}

// "{\"id\":\"9\",\"name\":\"random-name\",\"artist\":\"random-artist\",\"album\":\"default\"}"
// "{\"userName\":\"testUserDetails\",\"firstName\":\"xxx\",\"lastName\":\"xxx\",\"password\":\"xxx\"}"

//    public void createEmployeeAPI() throws Exception
//    {
//        mvc.perform( MockMvcRequestBuilders
//                .post("/employees")
//                .content(asJsonString(new EmployeeVO(null, "firstName4", "lastName4", "email4@mail.com")))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.employeeId").exists());
//    }