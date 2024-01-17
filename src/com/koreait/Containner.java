package com.koreait;

import java.util.Scanner;

import com.koreait.controller.WiseSayingController;
import com.koreait.dao.WiseSayingDAO;
import com.koreait.service.WiseSayingService;

public class Containner {
	public static final String pattern = "%s  /  %s  /  %s  \n";
	public static final Scanner sc = new Scanner(System.in);
	public static final WiseSayingDAO wiseSayingDAO = WiseSayingDAO.getInstance();
	public static final WiseSayingService wiseSayingService = WiseSayingService.getInstance();
	public static final WiseSayingController wiseSayingController = WiseSayingController.getInstance();
	public static final App app = App.getInstance();

	public static void close() {
		sc.close();

	}

}
