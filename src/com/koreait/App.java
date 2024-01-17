package com.koreait;

import java.util.Scanner;

import com.koreait.controller.WiseSayingController;

public class App {
	private Scanner sc;
	private static  App app;
	private WiseSayingController controller;
	
	private App () {
		sc = Containner.sc;
		controller = Containner.wiseSayingController;
		
	}
	
	public static App getInstance() {
		if(app==null) {
			app = new App();
		}
		return app;
	}
	
	
	
	public void run() {
		System.out.println("== 명언 앱 실행 ==");

		while (true) {
			System.out.print("명령어 ) ");
			String cmd = sc.nextLine();
			if (cmd.equals("등록")) {
				controller.doInsert();

			} else if (cmd.equals("목록")) {
				controller.showSayings();

			} else if (cmd.startsWith("수정")) {
				controller.doModify(cmd);

			} else if (cmd.startsWith("상세보기")) {
				controller.showDetail(cmd);

			} else if (cmd.startsWith("삭제")) {
				controller.doDelete(cmd);
			} else if (cmd.equals("종료")) {
				System.out.println("종료기능은 필요할거 같아서..");
				break;
			} else {
				System.out.println("잘못된 명령어 입니다.");
			}
		}
	}







}
