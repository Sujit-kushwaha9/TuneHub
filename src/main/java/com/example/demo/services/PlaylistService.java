package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.PlayList;

@Service
public interface PlaylistService {

	public void addPlaylist(PlayList playlist);

	public List<PlayList> fetchAllPlaylists();



}
