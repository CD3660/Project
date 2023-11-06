package machine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.security.auth.login.LoginContext;

public class DAO {
	Scanner sc;
	int userIndex, itemSort;
	String[] searchLog;// [0] 검색어 [1] 검색 방법 1 = 이름 2= 종류
	CustomerDTO cDto;
	ItemDTO iDto;
	ArrayList<ItemDTO> itemDtos;
	ArrayList<OrderListDTO> orderListDtos;
	Connection conn;
	// 임시
	private final String DB_URL = "jdbc:oracle:thin:@118.40.91.135:1521:xe";
	private final String DB_USER = "BTEAM";
	private final String DB_PASSWORD = "BTEAM2";
	private final String itemSortName = " where name = ?";
	private final String itemSortType = " where type = ?";
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
	public String login(String username, String password) {
		System.out.println("아이디와 비밀번호를 입력해주세요.");
		String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sc.nextLine());
            ps.setString(2, sc.nextLine());
            ResultSet rs = ps.executeQuery();
            
            
            if (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        System.out.println("다시 입력 해주세요");
        return null;
 
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
				addItem();
					break;
				case "2":
				editItem();
					break;
				case "3":
				deleteItem();
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
		System.out.println("==========================================================================");
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
		System.out.println("==========================================================================");
	}

	public void displayItemList() {
<<<<<<< HEAD
		System.out.println("==========================================================================");
		for (ItemDTO dto : itemDtos) {
			System.out.print("상품번호 : " + dto.getIdx() + " 이름 : " + dto.getName());
			System.out.println("가격 : " + dto.getPrice() + " 종류 : " + dto.getType());
		}
		System.out.println("==========================================================================");
=======
<<<<<<< HEAD
		String temp = "";
		if (searchLog[1].equals("1")) {
=======
		String temp = "select idx, name, price, type, info from itemdto";
		if(searchLog[1].equals("1")) {
>>>>>>> main
			temp += itemSortName;
		} else {
			temp += itemSortType;
		}
		temp += itemSortStr[itemSort];
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
<<<<<<< HEAD
			PreparedStatement ps = conn.prepareStatement("");
=======
			PreparedStatement ps = conn.prepareStatement(temp);
>>>>>>> main
			ps.setString(1, searchLog[0]);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.print("상품번호 : " + rs.getInt("idx") + " 이름 : " + rs.getString("name"));
				System.out.println("가격 : " + rs.getInt("price") + " 종류 : " + rs.getString("type"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
		}
>>>>>>> bch
	}

	public void displayItemInfo(int idx) {
		System.out.println("==========================================================================");
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
		System.out.println("==========================================================================");
	}

	public void displayOrderList() {
		for (OrderListDTO dto : orderListDtos) {
			System.out.println("주문번호 : " + dto.getIndex() + " 상품번호 : " + dto.getIndex());
//			System.out.println("가격 : " + rs.getInt("price") + " 종류 : " + rs.getString("type"));
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
<<<<<<< HEAD
	public void editItem() {
=======
	public void editItemPrice(int itemId, String newName, double newPrice, String newinfo) {
>>>>>>> bch
		int idx = getInt();
//    	displayItem(idx);
		int temp = 0;
		while (true) {
			System.out.println("추가하려는 항목을 고르세요");
			System.out.println("1. 이름 2. 가격 3. 정보");
			temp = getInt();
			if (temp <= 3 && temp >= 1) {
				break;
			} else {
				System.out.println("1부터 3을 입력 하세요");
			}
		}
		String nameQ = "update products set name = ? where idx = ?";
		String priceQ = "update products set price = ? where idx = ?";
		String infoQ = "update products set info = ? where idx = ?";
		switch (temp) {
		case 1:

			try {
				PreparedStatement ps = conn.prepareStatement(nameQ);
				ps.setString(1, sc.nextLine());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				PreparedStatement ps = conn.prepareStatement(priceQ);
				ps.setInt(1, sc.nextInt());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			try {
				PreparedStatement ps = conn.prepareStatement(infoQ);
				ps.setString(1, sc.nextLine());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	// 상품 추가
	public void addItem() {
		System.out.println("상품을 추가합니다.");
		ItemDTO dto = new ItemDTO();
		System.out.println("이름을 입력하세요.");
		dto.setName(sc.nextLine());
		System.out.println("가격을 입력하세요.");
		dto.setPrice(getInt());
		System.out.println("종류를 입력하세요.");
		dto.setType(sc.nextLine());
		System.out.println("정보를 입력하세요.");
		dto.setInfo(sc.nextLine());

		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement(
						"INSERT INTO products (idx, name, price, type, info) VALUES (item_seq.nextval, ?, ?, ?, ?)")) {

			statement.setString(1, dto.getName());
			statement.setDouble(2, dto.getPrice());
<<<<<<< HEAD
			statement.setString(3, dto.getName());
=======
			statement.setString(3, dto.getInfo());
>>>>>>> bch
			statement.setString(4, dto.getType());
			int temp = statement.executeUpdate();
			System.out.println("추가 되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// 상품 삭제
	public void deleteItem() {
		System.out.println("삭제하려는 상품 번호를 입력 하세요.");
		int temp = getInt();
		
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM itemdto WHERE id=?");
			ps.setInt(1, temp);
			int tempx = ps.executeUpdate();
			if(tempx == 1) {
				System.out.println("삭제가 되었습니다.");
			}else {
				System.out.println("해당하는 상품이 없습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원 탈퇴 수락
	public void resignAccept(int userId) {
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE resign=1")) {
			
			int result = statement.executeUpdate();
			System.out.println(result +" 명 회원 탈퇴 수락 완료.");
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
<<<<<<< HEAD
		while (true) {
			if (option.equals("1") || option.equals("2")) {
				break;
			} else {
				System.out.println("잘못된 입력");
			}

			String temp = "select idx, name, price, type, info from itemdto";
			if (option.equals("1")) {
				temp += itemSortName;
				temp += itemSortStr[1];
			} else {
				temp += itemSortType;
				temp += itemSortStr[3];
			}

			try {
				Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = connection.prepareStatement("temp");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					ItemDTO dto = new ItemDTO();
					dto.setIdx(rs.getInt("idx"));
					dto.setName(rs.getString("name"));
					dto.setPrice(rs.getInt("price"));
					dto.setType(rs.getString("type"));
					dto.setInfo(rs.getString("info"));

				}
			} catch (SQLException e) {
				e.printStackTrace();

=======
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
					System.out.println(rs.getString(iDto.getName()));
				}
			} else if (option.equals("2")) {
				while (rs.next()) {
					System.out.println(rs.getString(iDto.getType()));
				}
>>>>>>> bch
			}

		}
	}

	// 상품 구매
	public void purchase() {
		ItemDTO iDto = new ItemDTO();
//		int itemOption = sc.nextLine()
		
		
		
		
	
	}
	
	
	
	
	

	public void purchaseCart() {
		System.out.println("장바구니 내역입니다.");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn
					.prepareStatement("select idx, item, price, quantity from orderlistdto, itemdto where id = ? and purchased = 0");
			ps.setString(1, cDto.getId());
			ResultSet rs = ps.executeQuery();
			orderListDtos = new ArrayList<>();
			while(rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setIndex(rs.getInt("idx"));
				dto.setItem(rs.getInt("item"));
				dto.setId(cDto.getId());
				dto.setQuantity(rs.getInt("quantity"));
				orderListDtos.add(dto);
			}
			displayOrderList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
