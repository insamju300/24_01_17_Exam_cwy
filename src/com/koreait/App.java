package com.koreait;

import java.util.Optional;
import java.util.Scanner;

import com.koreait.dao.WiseSayingDAO;
import com.koreait.dto.WiseSaying;

public class App {
	WiseSayingDAO dao;
	Scanner sc;
	private final String pattern = "%s  /  %s  /  %s  \n";
	{
		dao = WiseSayingDAO.getInstance();
		sc = new Scanner(System.in);
	}

	public void run() {
		System.out.println("== 명언 앱 실행 ==");

		while (true) {
			System.out.print("명령어 ) ");
			String cmd = sc.nextLine();
			if (cmd.equals("등록")) {
				System.out.print("명언 : ");
				String content = sc.nextLine();
				if (isEmpty(content)) {
					System.out.println("명언은 필수 입력 사항입니다.");
					continue;
				}
				System.out.print("작가 : ");
				String author = sc.nextLine();
				if (isEmpty(author)) {
					System.out.println("작가는 필수 입력 사항입니다.");
					continue;
				}

				int id = dao.insert(new WiseSaying(content, author));
				System.out.printf("%d번 명언이 등록되었습니다.\n", id);

			} else if (cmd.equals("목록")) {
				System.out.printf(pattern, "번호", "작가", "명언");
				System.out.println("=".repeat(30));
				dao.findAll().forEach((a) -> System.out.printf(pattern, "" + a.getId(), a.getAuthor(), a.getContent()));

			} else if (cmd.startsWith("수정")) {
				int id = getId(cmd);
				if (id == -1) {
					System.out.println("수정?id=번호 식으로 입력해주세요.");
					continue;
				}
				Optional<WiseSaying> findOpt = dao.findOne(id);
				if (findOpt.isEmpty()) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}
				WiseSaying wiseSaying = findOpt.get();

				System.out.printf("명언(기존) :%s\n", wiseSaying.getContent());
				System.out.printf("작가(기존) :%s\n", wiseSaying.getAuthor());
				System.out.print("명언 : ");
				String content = sc.nextLine();
				System.out.print("작가 : ");
				String author = sc.nextLine();
				if (isEmpty(author) && isEmpty(content)) {
					System.out.println("작가와 명언이 둘다 입력되지 않았습니다. 초기화면으로 돌아갑니다.");
					continue;
				}
				if (!isEmpty(author)) {
					wiseSaying.setAuthor(author);
				}
				if (!isEmpty(content)) {
					wiseSaying.setContent(content);
				}
				System.out.printf("%d번 명언이 수정되었습니다.\n", wiseSaying.getId());

			} else if (cmd.startsWith("상세보기")) {
				int id = getId(cmd);
				if (id == -1) {
					System.out.println("상세보기?id=번호 식으로 입력해주세요.");
					continue;
				}
				Optional<WiseSaying> findOpt = dao.findOne(id);
				if (findOpt.isEmpty()) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}
				WiseSaying wiseSaying = findOpt.get();
				System.out.printf("번호 : %d\n", wiseSaying.getId());
				System.out.printf("날짜 : %s\n", wiseSaying.getRegDateString());
				System.out.printf("작가 : %s\n", wiseSaying.getAuthor());
				System.out.printf("내용 : %s\n", wiseSaying.getContent());

			} else if (cmd.startsWith("삭제")) {
				int id = getId(cmd);
				if (id == -1) {
					System.out.println("상세보기?id=번호 식으로 입력해주세요.");
					continue;
				}
				Optional<WiseSaying> findOpt = dao.findOne(id);
				if (findOpt.isEmpty()) {
					System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
					continue;
				}

				dao.delete(findOpt.get());
				System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
			} else if (cmd.equals("종료")) {
				System.out.println("종료기능은 필요할거 같아서..");
				break;
			} else {
				System.out.println("잘못된 명령어 입니다.");
			}
		}
	}

	private int getId(String cmd) {
		int result = -1;
		String[] bits = cmd.split("\\?id=");
		if (bits.length == 2) {
			switch (bits[0].trim()) {
			case "수정":
			case "상세보기":
			case "삭제":
				break;
			default:
				return result;

			}
			try {
				result = Integer.parseInt(bits[1]);
			} catch (NumberFormatException e) {

			}
		}
		return result;
	}

	private boolean isEmpty(String content) {
		// TODO Auto-generated method stub
		return content == null || content.trim().length() == 0;
	}

}
