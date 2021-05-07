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
        assertEquals(expected, playlistService.getSongById(expected.getId()), "You should get the song with that id");
    }

    @Test
    void getAllSongs() {
        List<Song> expected = testPlaylist;
        assertEquals(expected, playlistService.getAllSongs(), "You should get a list of all songs from playlist");
    }

    @Test
    void getSongsByName_Exists() {
        List<Song> expected = new ArrayList<>();
        expected.add(testPlaylist.get(1));
        assertEquals(expected, playlistService.getSongsByName("name1"),"You should get all songs with that name in a list");
    }

    @Test
    void getSongsByName_NotExists() {
        List<Song> expected = new ArrayList<>();
        assertEquals(expected, playlistService.getSongsByName("name not exist"), "If name doesnt exists you should get empty list");
    }

    @Test
    void getSongsByArtist_Exists() {
        List<Song> expected = new ArrayList<>();
        expected.add(testPlaylist.get(2));
        assertEquals(expected, playlistService.getSongsByArtist("artist2"), "You should get all songs with that artist in a list");
    }

    @Test
    void getSongsByArtist_NotExists() {
        List<Song> expected = new ArrayList<>();
        assertEquals(expected, playlistService.getSongsByArtist("artist not exist"), "If artist doesnt exists you should get empty list");
    }

    @Test
    void addSongShouldAddToEndOfPlaylist() {
        Song songToAdd = new Song(100, "Newest Song", "Newest Artist");
        playlistService.addSong(songToAdd);
        assertEquals(songToAdd, playlistService.getSongById(100), "Last song added should exist in playlist");
    }

    @Test
    void updateSongById_Exists() {
        Song updateSong = new Song(5, "updatedSong", "updatedArtist");
        assertEquals(updateSong, playlistService.updateSongById(5, updateSong), "Updating a song with an Id that exists should replace the old song with the new song");
    }

    @Test
    void updateSongById_NotExists() {
        Song updateSong = new Song(14, "will this update", "i dont know artist");
        assertNull(playlistService.updateSongById(14, updateSong), "Updating a song with an Id that does not exists should return null");
    }

    @Test
    void deleteSongById_Exists() {
        Song expected = playlistService.getSongById(8);
        assertEquals(expected, playlistService.deleteSongById(8), "If Id exists, you should get back the Song you want to delete");
        assertEquals(9, playlistService.getAllSongs().size(), "Deleting a song should decrease the size by one");
    }

    @Test
    void deleteSongById_NotExists() {
        assertNull(playlistService.deleteSongById(19), "If id doesn't exist, you should get null");
        assertEquals(10, playlistService.getAllSongs().size(), "If id doesn't exist, the size of the list should remain the same");
    }
}