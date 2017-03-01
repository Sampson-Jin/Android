package com.example.service;

import java.util.List;

public interface IMusicService {

	public void MPlay(String path,List<String> lists,int position);
	public void MPause();
	public void MStop();
	public void MRPlay();
	public int getState();
//	public int setPosition(int position);
}
