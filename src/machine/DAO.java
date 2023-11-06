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
	String[] searchLog;
	CustomerDTO cDto;
	ItemDTO iDto;
	OrderListDTO oDto;
	ArrayList<ItemDTO> itemDtos;

	
	
	
	
	
	// 임시
	private static final String DB_URL = "jdbc:mysql://localhost:3306/database";
	private static final String DB_USER = "username";
	private static final String DB_PASSWORD = "password";

	public DAO() {
		sc = new Scanner(System.in);
		searchLog = new String[2];
		itemSort = 1;
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

	public void display() {

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
					// login();
				} else {
					System.out.println("입력오류. 다시 진행해주세요.");
					break;

				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	// 상품 검색
	public void searchItem() {

		itemDtos = new ArrayList<>();
		
		System.out.println("상품 검색을 선택하셨습니다. 1.이름으로 검색  2.상품 종류로 검색");
		String option = sc.nextLine();
		String temp = "select idx, name, price, type, info from itemdto";
	      if(searchLog[1].equals("1")) {
	         temp += itemSortName;
	      } else {
	         temp += itemSortType;
	      }
	      temp+= itemSortStr[itemSort];
		try {
			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = connection.prepareStatement("temp");
			ResultSet rs = ps.executeQuery();
			if (option.equals("1")) {
				while (rs.next()) {
					System.out.println(rs.getString(iDto.getProductName()));
				}
			} else if (option.equals("2")) {
				while (rs.next()) {
					System.out.println(rs.getString(iDto.getType()));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}
	
	
	
	
	
	//상품 구매
	public void purchase() {
		ItemDTO iDto = new ItemDTO();
		int itemOption = sc.nextLine()
		
		
		
		
	
	}
	
	
	
	
	
}