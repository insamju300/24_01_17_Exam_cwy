package com.koreait.controller;

import java.util.Optional;
import java.util.Scanner;

import com.koreait.Containner;
import com.koreait.Util;
import com.koreait.dto.WiseSaying;
import com.koreait.service.WiseSayingService;

public class WiseSayingController {
	private WiseSayingService service;
	private Scanner sc;
	private static WiseSayingController wiseSayingController;

	private WiseSayingController() {
		service = Containner.wiseSayingService;
		sc = Containner.sc;
	}

	public static WiseSayingController getInstance() {
		if (wiseSayingController == null) {
			wiseSayingController = new WiseSayingController();
		}
		return wiseSayingController;
	}

	public void doInsert() {
		System.out.print("명언 : ");
		String content = sc.nextLine();
		if (Util.isEmpty(content)) {
			System.out.println("명언은 필수 입력 사항입니다.");
			return;
		}
		System.out.print("작가 : ");
		String author = sc.nextLine();
		if (Util.isEmpty(author)) {
			System.out.println("작가는 필수 입력 사항입니다.");
			return;
		}

		int id = service.insert(new WiseSaying(content, author));
		System.out.printf("%d번 명언이 등록되었습니다.\n", id);
	}

	public void showSayings() {
		System.out.printf(Containner.pattern, "번호", "작가", "명언");
		System.out.println("=".repeat(30));
		service.findAll().forEach((a) -> System.out.printf(Containner.pattern, "" + a.getId(), a.getAuthor(), a.getContent()));
		
	}

	public void doModify(String cmd) {
		int id = Util.getId(cmd);
		if (id == -1) {
			System.out.println("수정?id=번호 식으로 입력해주세요.");
			return;
		}
		Optional<WiseSaying> findOpt = service.findOne(id);
		if (findOpt.isEmpty()) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}
		WiseSaying wiseSaying = findOpt.get();

		System.out.printf("명언(기존) :%s\n", wiseSaying.getContent());
		System.out.printf("작가(기존) :%s\n", wiseSaying.getAuthor());
		System.out.print("명언 : ");
		String content = sc.nextLine();
		System.out.print("작가 : ");
		String author = sc.nextLine();
		if (Util.isEmpty(author) && Util.isEmpty(content)) {
			System.out.println("작가와 명언이 둘다 입력되지 않았습니다. 초기화면으로 돌아갑니다.");
			return;
		}
		service.doModify(wiseSaying, author, content);
		
		System.out.printf("%d번 명언이 수정되었습니다.\n", wiseSaying.getId());
		
	}

	public void showDetail(String cmd) {
		int id = Util.getId(cmd);
		if (id == -1) {
			System.out.println("상세보기?id=번호 식으로 입력해주세요.");
			return;
		}
		Optional<WiseSaying> findOpt = service.findOne(id);
		if (findOpt.isEmpty()) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}
		WiseSaying wiseSaying = findOpt.get();
		System.out.printf("번호 : %d\n", wiseSaying.getId());
		System.out.printf("날짜 : %s\n", wiseSaying.getRegDateString());
		System.out.printf("작가 : %s\n", wiseSaying.getAuthor());
		System.out.printf("내용 : %s\n", wiseSaying.getContent());
		
	}

	public void doDelete(String cmd) {
		int id = Util.getId(cmd);
		if (id == -1) {
			System.out.println("상세보기?id=번호 식으로 입력해주세요.");
			return;
		}
		Optional<WiseSaying> findOpt = service.findOne(id);
		if (findOpt.isEmpty()) {
			System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
			return;
		}

		service.delete(findOpt.get());
		System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
		
	}
	

}
