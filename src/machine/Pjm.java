package machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Pjm {
	Scanner sc;
	int userIndex, itemSort;
	String[] searchLog;
	CustomerDTO cDto;
	ItemDTO iDto;
	OrderListDTO oDto;
	private Object dao;
	Connection conn;
	// �엫�떆
	private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
	private static final String DB_USER = "username";
	private static final String DB_PASSWORD = "password";

	// 회원정보수정,아이디,
	public void pjm() {

		System.out.println("변경할 비밀번호를 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set pw= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, cDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void address() {

		System.out.println("변경할 주소를 입력해주세요");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set address= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, cDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void phone() {

		System.out.println("변경할 번호를 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set phone= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, cDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void email() {

		System.out.println("변경할 이메일을 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set email= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, cDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void point() {

		System.out.println("충전할 포인트를 입력해주세요.");

		int value = getInt();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("");
			ResultSet rs = ps.executeQuery();
			ps = conn.prepareStatement("update CustomerDTO set point= ? where id = ?");
			ps.setInt(1, value);
			ps.setString(2, cDto.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getInt() {
		while (true) {
			try {
				int i = Integer.parseInt(sc.nextLine());
				return i;
			} catch (Exception e) {
				System.out.println("숫자를 입력하세요.");
			}
		}
	}

	public void resign() {

		System.out.println("회원 탈퇴 신청을 하시겠습니까?");
		System.out.println("1.회원 탈퇴 아무키.취소");
		String str1 = sc.nextLine();
		if (str1.equals("1")) {
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement("update CustomerDTO set resign= ? where id = ?");
				ps.setInt(1, 1);
				ps.setString(2, cDto.getId());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}