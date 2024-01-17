package com.koreait.service;

import java.util.List;
import java.util.Optional;

import com.koreait.Containner;
import com.koreait.Util;
import com.koreait.dao.WiseSayingDAO;
import com.koreait.dto.WiseSaying;

public class WiseSayingService {

	private static WiseSayingService wiseSayingService;
	private WiseSayingDAO dao;
	
	private WiseSayingService () {
		dao = Containner.wiseSayingDAO;
	}
	
	public static WiseSayingService getInstance() {
		if(wiseSayingService==null) {
			wiseSayingService = new WiseSayingService();
		}
		return wiseSayingService;
	}

	public int insert(WiseSaying wiseSaying) {
		// TODO Auto-generated method stub
		return dao.insert(wiseSaying);
	}

	public List<WiseSaying> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	public Optional<WiseSaying> findOne(int id) {
		// TODO Auto-generated method stub
		return dao.findOne(id);
	}

	public void doModify(WiseSaying wiseSaying, String author, String content) {
		if (!Util.isEmpty(author)) {
			wiseSaying.setAuthor(author);
		}
		if (!Util.isEmpty(content)) {
			wiseSaying.setContent(content);
		}
		
	}

	public void delete(WiseSaying wiseSaying) {
		dao.delete(wiseSaying);
		
	}

}
