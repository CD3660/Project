package machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class DAO {
	Scanner sc;
	int userIndex, itemSort;
	String[] searchLog;//[0] 검색어 [1] 검색 방법 1 = 이름 2= 종류
	CustomerDTO cDto;
	ItemDTO iDto;
	ArrayList<ItemDTO> itemDtos;
	ArrayList<OrderListDTO> orderListDtos;
	Connection conn;
	// 임시
	private final String DB_URL = "jdbc:mysql://localhost:3306/database";
	private final String DB_USER = "username";
	private final String DB_PASSWORD = "password";
	private final String itemSortName = "select idx, name, price, type, info from itemdto where name = ?";
	private final String itemSortType = "select idx, name, price, type, info from itemdto where type = ?";
	private final String[] itemSortStr;
	public DAO() {
		sc = new Scanner(System.in);
		searchLog = new String[3];
		itemSort = 1;
		itemSortStr = new String[7];
		itemSortStr[1] = " order by idx";
		itemSortStr[2] = " order by idx decs";
		itemSortStr[3] = " order by name";
		itemSortStr[4] = " order by name decs";
		itemSortStr[5] = " order by price";
		itemSortStr[6] = " order by price decs";
	}

	public void loginMenu() {
		point: while (true) {
			System.out.println("1.로그인 2.회원가입 0.종료");
			String temp = sc.nextLine();
			switch (temp) {
			case "1":
//				userIndex = login();
				if (userIndex != -1) {
					userMenu();
				}
				break;
			case "2":
//				createUser();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}
	}

	public void userMenu() {
		point: while (true) {
			System.out.println("1.회원정보수정 2.상품검색 3. 관리자 메뉴 4. 장바구니 5. 구매 내역 0. 로그아웃");
			String temp = sc.nextLine();
			switch (temp) {
			case "1":
//				editUser();
				break;
			case "2":
//				searchItem();
				break;
			case "3":
				managerMenu();
				break;
			case "4":
				cartMenu();
				break;
			case "5":
//				displayPurchased();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}
	}

	public boolean isManager() {

		return false;
	}

	public void managerMenu() {
		if (isManager()) {
			point: while (true) {
				System.out.println("1. 상품 추가 2. 상품 수정 3. 상품 삭제 4. 회원 탈퇴 수락 0. 돌아가기");
				String temp = sc.nextLine();
				switch (temp) {
				case "1":
//				addItem();
					break;
				case "2":
//				editItem();
					break;
				case "3":
//				deleteItem();
					break;
				case "4":
//				resignAccept();
					break;
				case "0":
					break point;
				default:
					System.out.println("입력 오류");
					break;
				}
			}
		}
	}

	public void cartMenu() {
		point: while (true) {
			System.out.println("1. 장바구니 구매 2. 장바구니 삭제 0. 돌아가기");
			String temp = sc.nextLine();
			switch (temp) {
			case "1":
//				purchaseCart();
				break;
			case "2":
//				deleteCart();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}

	}

	public void displayUserInfo(String id) {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"select address, phone, email, point, grade, manager from customerdto where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("주소 : " + rs.getString("address"));
				System.out.println("전화번호 : " + rs.getInt("phone"));
				System.out.println("이메일 : " + rs.getString("email"));
				System.out.println("잔여 포인트 : " + rs.getString("point"));
				if (rs.getInt("manager") == 1) {
					System.out.println("관리자 : TRUE");
				} else {
					System.out.println("관리자 : FALSE");
				}
			} else {
				System.out.println("올바른 아이디가 아닙니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}

	}

	public void displayItemList() {
		String temp = "";
		if(searchLog[1].equals("1")) {
			temp += itemSortName;
		} else {
			temp += itemSortType;
		}
		temp+= itemSortStr[itemSort];
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn
					.prepareStatement("");
			ps.setString(1, searchLog[0]);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print("상품번호 : " + rs.getInt("idx") + " 이름 : " + rs.getString("name"));
				System.out.println("가격 : " + rs.getInt("price") + " 종류 : " + rs.getString("type"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	public void displayItemInfo(int idx) {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn
					.prepareStatement("select idx, name, price, type, info from itemdto where idx = ?");
			ps.setInt(1, idx);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("상품번호 : " + rs.getInt("idx") + " 이름 : " + rs.getString("name"));
				System.out.println("가격 : " + rs.getInt("price") + " 종류 : " + rs.getString("type"));
				System.out.println("상품 정보");
				System.out.println(rs.getString("info"));
			} else {
				System.out.println("해당하는 상품이 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
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

	public void closeConn() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			conn = null;
		}
	}

	// 상품 정보 수정
	public void editItemPrice(int itemId, String newName, double newPrice, String newDescription) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection
						.prepareStatement("UPDATE products SET name=?, price=?, description=? WHERE id=?")) {
			statement.setString(1, "editItem" + newName);
			statement.setDouble(2, newPrice);
			statement.setString(3, newDescription);
			statement.setInt(4, itemId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
		}
	}

	// 상품 추가
	public void addItem(String name, double price, String description) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO products (name, price, description) VALUES (?, ?, ?)")) {
			statement.setString(1, "addItem" + name);
			statement.setDouble(2, price);
			statement.setString(3, description);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 상품 삭제
	public void deleteItem(int itemId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id=?")) {
			statement.setInt(1, itemId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원 탈퇴 수락
	public void resignAccept(int userId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
			statement.setInt(1, userId);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	public void createUser() {
		CustomerDTO cDto = new CustomerDTO();
		while (true) {
			System.out.println("회원가입을 위해 " + "아이디, 비밀번호 입력해주세요.");
			cDto.setId(sc.nextLine());
			cDto.setPw(sc.nextLine());
			try {
				Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = connection.prepareStatement("INSERT INTO MEMBER VALUES ( ? , ? )");
				ps.setString(1, cDto.getId());
				ps.setString(2, cDto.getPw());
				int result = ps.executeUpdate();
				if (result == 1) {
					System.out.println("회원가입이 되셨습니다. 로그인해주세요.");
					//login();
				} else {
					System.out.println("입력오류. 다시 진행해주세요.");
					break;
					
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
