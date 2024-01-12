package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.PlayList;
import com.example.demo.entity.Song;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;


@Controller
public class PlaylistController {
	@Autowired
	SongService songService;
	@Autowired
	PlaylistService playlistService;

	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model) {

		List<Song> songList = songService.fetchAllSongs();
		model.addAttribute("songs", songList);
		return "createPlaylist";
	}

	@PostMapping("/addPlayList")
	public String addPlaylist(@ModelAttribute PlayList playlist) {

		// Updating playlist table
		playlistService.addPlaylist(playlist);
		System.out.println(playlist);

//		Updating song table
		List<Song> songList = playlist.getSongs();
		for (Song s : songList) {
			s.getPlayList().add(playlist);
			songService.updateSong(s);
		}
		return "AdminHome";
	}
	
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model) {
		List<PlayList> allPlaylists=playlistService.fetchAllPlaylists();
		model.addAttribute("allPlaylists", allPlaylists);
		return "displayPlayLists";
	}
	

}
