package com.koreait;

public class Util {
	public static boolean isEmpty(String content) {
		// TODO Auto-generated method stub
		return content == null || content.trim().length() == 0;
	}
	
	public static int getId(String cmd) {
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
}
