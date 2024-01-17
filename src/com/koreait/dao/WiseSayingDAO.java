package com.koreait.dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.koreait.dto.WiseSaying;

public class WiseSayingDAO {
	private int autoIncrement = -1;
	private List<WiseSaying> db = null;
	private static WiseSayingDAO wiseSayingDAO;
	
	private WiseSayingDAO () {
		db = new ArrayList<>();
		autoIncrement = 1;
	}
	
	public static WiseSayingDAO getInstance() {
		if(wiseSayingDAO==null) {
			wiseSayingDAO = new WiseSayingDAO();
		}
		return wiseSayingDAO;
	}
	
	public Optional<WiseSaying> findOne(int id) {
		return db.stream().filter(a->a.getId()==id).findFirst();
	}
	
	public List<WiseSaying> findAll(){
		return db.stream().sorted(Comparator.comparing(WiseSaying::getId).reversed()).toList();
	}
	
	public void delete(WiseSaying wiseSaying) {
		db.remove(wiseSaying);
	}
	
	public int insert(WiseSaying wiseSaying) {
		db.add(new WiseSaying(autoIncrement, LocalDateTime.now(), wiseSaying.getContent(), wiseSaying.getAuthor()));
		return autoIncrement++;
	}
	
	public boolean isNotExists(int id) {
		return db.stream().filter(a->a.getId()==id).findFirst().isEmpty();	
	}

}
