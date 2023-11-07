package machine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DAO {
	Scanner sc;
	int itemSort;
	String id;
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
	private final String itemSortName = " where name like ?";
	private final String itemSortType = " where type like ?";
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

	// 메뉴 구조 완료
	public void loginMenu() {
		point: while (true) {
			System.out.println("1.로그인 2.회원가입 0.종료");
			String temp = sc.nextLine();
			switch (temp) {
			case "1":
				id = login();
				if (id != null) {
					userMenu();
				}
				break;
			case "2":
				createUser();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;

			}
		}
	}

	// 병합 완료
	public String login() {
		System.out.println("아이디와 비밀번호를 입력해주세요.");
		String sql = "SELECT * FROM customerdto WHERE id = ? AND pw = ?";
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sc.nextLine());
			ps.setString(2, sc.nextLine());
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
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
				editUser();
				break;
			case "2":
				searchItem();
				break;
			case "3":
				managerMenu();
				break;
			case "4":
				cartMenu();
				break;
			case "5":
				displayPurchased();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}
	}

	// 관리자 확인 완료
	public boolean isManager() {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn
					.prepareStatement("select manager from customerdto where id = ? and manager = 1");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("관리자 계정");
				return true;
			} else {
				System.out.println("관리자가 아닌 아이디 입니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
		return false;
	}

	// 관리자 메뉴 완료
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
					resignAccept();
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

	// 장바구니 메뉴 완료
	public void cartMenu() {
		point: while (true) {
			System.out.println("1. 장바구니 구매 2. 장바구니 삭제 0. 돌아가기");
			String temp = sc.nextLine();
			switch (temp) {
			case "1":
				purchaseCart();
				break;
			case "2":
				deleteCart();
				break;
			case "0":
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}

	}

	// 장바구니 삭제 완료
	public void deleteCart() {
		System.out.println("장바구니를 삭제 하시겠습니까?");
		System.out.println("1.삭제 아무키.돌아가기");
		if (sc.nextLine().equals("1")) {
			try {
				PreparedStatement ps = conn.prepareStatement("DELETE FROM ORDERLISTDTO WHERE id=? and purchased = 0");
				ps.setString(1, id);
				int temp = ps.executeUpdate();
				System.out.println(temp + " 건 삭제 완료");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				closeConn();
			}
		}
	}

	// 사용자 정보 출력 완료
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
				System.out.println("전화번호 : " + rs.getString("phone"));
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

	// 상품 리스트 출력
	public void displayItemList() {
		System.out.println("==========================================================================");
		for (ItemDTO dto : itemDtos) {
			System.out.println("--------------------------------------------------------------------------");
			System.out.print("상품번호 : " + dto.getIdx() + " 이름 : " + dto.getName());
			System.out.println("가격 : " + dto.getPrice() + " 종류 : " + dto.getType());
			System.out.println("--------------------------------------------------------------------------");
		}
		System.out.println("===========================================================================");
	}

	// 상품 정보 출력 완료
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
				iDto = new ItemDTO();
				iDto.setIdx(rs.getInt("idx"));
				iDto.setName(rs.getString("name"));
				iDto.setPrice(rs.getInt("price"));
				iDto.setType(rs.getString("type"));
				iDto.setInfo(rs.getString("info"));
			} else {
				System.out.println("해당하는 상품이 없습니다.");
				iDto = null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
		System.out.println("==========================================================================");
	}

	// 주문 내역 출력 완료
	public void displayOrderList() {
		System.out.println("==========================================================================");
		for (OrderListDTO dto : orderListDtos) {
			System.out.println("주문번호 : " + dto.getIndex() + " 상품번호 : " + dto.getItem());
			System.out.println("가격 : " + dto.getPrice() + " 수량 : " + dto.getQuantity());
			if (dto.isPurchased()) {
				System.out.println("구매 일자 : " + dto.getDate());
			}
			System.out.println("--------------------------------------------------------------------------");
		}
		System.out.println("==========================================================================");
	}

	public void displayPurchased() {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"select idx, item, price, quantity from orderlistdto where id = ? and purchased = 1");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			orderListDtos = new ArrayList<>();
			while (rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setIndex(rs.getInt("idx"));
				dto.setItem(rs.getInt("item"));
				dto.setId(id);
				dto.setPrice(rs.getInt("price"));
				dto.setQuantity(rs.getInt("quantity"));
				orderListDtos.add(dto);
			}
			displayOrderList();
		} catch (SQLException e) {
			System.out.println("포인트가 부족합니다.");
		} finally {
			closeConn();
		}
	}

	// 숫자만 입력 받기
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

	// 연결 종료 병합 완료
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

	// 상품 정보 수정 병합 완료
	public void editItem() {
		System.out.println("원하는 상품의 번호를 입력하세요.");
		int idx = getInt();
		displayItemInfo(idx);
		if (iDto == null) {
			return;
		}
		int temp = 0;
		while (true) {
			System.out.println("수정하려는 항목을 고르세요");
			System.out.println("1. 이름 2. 가격 3. 정보");
			temp = getInt();
			if (temp <= 3 && temp >= 1) {
				break;
			} else {
				System.out.println("1부터 3을 입력 하세요");
			}
		}
		String nameQ = "update itemdto set name = ? where idx = ?";
		String priceQ = "update itemdto set price = ? where idx = ?";
		String infoQ = "update itemdto set info = ? where idx = ?";
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = null;
			switch (temp) {
			case 1:
				ps = conn.prepareStatement(nameQ);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, idx);
				break;
			case 2:
				ps = conn.prepareStatement(priceQ);
				ps.setInt(1, sc.nextInt());
				ps.setInt(2, idx);
				break;
			case 3:
				ps = conn.prepareStatement(infoQ);
				ps.setString(1, sc.nextLine());
				ps.setInt(2, idx);
				break;
			}
			if (ps.executeUpdate() == 1) {
				System.out.println("수정 완료");
			} else {
				System.out.println("오류 발생 다시 확인하세요");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 상품 추가 병합 완료
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

		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO itemdto (idx, name, price, type, info) VALUES (item_seq.nextval, ?, ?, ?, ?)");
			ps.setString(1, dto.getName());
			ps.setDouble(2, dto.getPrice());
			ps.setString(3, dto.getType());
			ps.setString(4, dto.getInfo());
			int temp = ps.executeUpdate();
			if (temp > 0) {
				System.out.println("추가 되었습니다");
			} else {
				System.out.println("입력 오류입니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}

	}

	// 상품 삭제 병합 완료
	public void deleteItem() {
		System.out.println("삭제하려는 상품 번호를 입력 하세요.");
		int temp = getInt();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM itemdto WHERE idx=?");
			ps.setInt(1, temp);
			int tempx = ps.executeUpdate();
			if (tempx == 1) {
				System.out.println("삭제가 되었습니다.");
			} else {
				System.out.println("해당하는 상품이 없습니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 회원 탈퇴 수락 병합 완료
	public void resignAccept() {
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("DELETE FROM customerdto WHERE resign=1");
			int result = ps.executeUpdate();
			System.out.println(result + " 명 회원 탈퇴 수락 완료.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

	// 회원가입 병합 완료
	public void createUser() {
		cDto = new CustomerDTO();
		while (true) {
			System.out.println("회원가입을 위해 " + "아이디, 비밀번호 입력해주세요.");
			cDto.setId(sc.nextLine());
			cDto.setPw(sc.nextLine());
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement("INSERT INTO customerdto (id, pw) VALUES ( ? , ? )");
				ps.setString(1, cDto.getId());
				ps.setString(2, cDto.getPw());
				int result = ps.executeUpdate();
				if (result == 1) {
					System.out.println("회원가입이 되셨습니다. 로그인해주세요.");
					break;
				} else {
					System.out.println("입력오류. 다시 진행해주세요.");
					break;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				closeConn();
			}
		}
	}

	// 상품 검색 병합 완료
	public void searchItem() {
		itemDtos = new ArrayList<>();
		point: while (true) {
			System.out.println("상품 검색");
			System.out.println("1.이름으로 검색  2.상품 종류로 검색 3.정렬방법변경 4.상품구매 0.돌아가기");
			String key = sc.nextLine();
			switch (key) {
			case "1":
				String temp = "select idx, name, price, type, info from itemdto";
				temp += itemSortName;
				temp += itemSortStr[1];
				try {
					conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					PreparedStatement ps = conn.prepareStatement(temp);
					System.out.println("검색어를 입력하세요.");
					String str = "%" + sc.nextLine() + "%";
					ps.setString(1, str);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						ItemDTO dto = new ItemDTO();
						dto.setIdx(rs.getInt("idx"));
						dto.setName(rs.getString("name"));
						dto.setPrice(rs.getInt("price"));
						dto.setType(rs.getString("type"));
						if (rs.getString("info") == null) {
							dto.setInfo("");
						} else {
							dto.setInfo(rs.getString("info"));
						}
						itemDtos.add(dto);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					closeConn();
				}
				displayItemList();
				break point;
			case "2":
				temp = "select idx, name, price, type, info from itemdto";
				temp += itemSortType;
				temp += itemSortStr[3];
				try {
					conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					PreparedStatement ps = conn.prepareStatement(temp);
					System.out.println("검색어를 입력하세요.");
					String str = "%" + sc.nextLine() + "%";
					ps.setString(1, str);
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						ItemDTO dto = new ItemDTO();
						dto.setIdx(rs.getInt("idx"));
						dto.setName(rs.getString("name"));
						dto.setPrice(rs.getInt("price"));
						dto.setType(rs.getString("type"));
						if (rs.getString("info") == null) {
							dto.setInfo("");
						} else {
							dto.setInfo(rs.getString("info"));
						}
						itemDtos.add(dto);
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					closeConn();
				}
				displayItemList();
				break point;
			case "3":
				System.out.println("정렬방법을 선택하세요");
				System.out.println("1.상품번호오름차순 2.상품번호내림차순 3.이름오름차순 4.이름내림차순 5.가격오름차순 6. 가격내림차순");
				while (true) {
					int a = getInt();
					if (a >= 1 && a <= 6) {
						itemSort = a;
						System.out.println("정렬방법 설정 완료");
						break;
					} else {
						System.out.println("잘못된 입력");
					}
				}
				break point;
			case "4":
				purchase();
				break point;
			default:
				System.out.println("입력 오류");
				break;
			}
		}

	}

	// 상품 구매
	public void purchase() {
		System.out.println("원하시는 상품번호를 선택하세요.");
		displayItemInfo(getInt());
		System.out.println("구매하실 수량을 선택해주세요.");
		int quantity = getInt();
		System.out.println(iDto.getName() + "을(를)" + " " + quantity + "개 선택하셨습니다.");

		while (true) {
			System.out.println("1.즉시 구매  2.장바구니 담기 0.돌아가기");
			String choose = sc.nextLine();
			if (choose.equals("1")) {
				purchaseNow(quantity);
			} else if (choose.equals("2")) {
				addCart(quantity);
			} else if (choose.equals("0")) {
				break;
			} else {
				System.out.println("다시 입력하세요");
			}
		}
	}

	// 즉시 구매 병합 완료
	public void purchaseNow(int quantity) {
		System.out.println("구매를 진행합니다");
		PreparedStatement ps;
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			ps = conn.prepareStatement("update customerdto set point = point - ? where id = ?");
			ps.setInt(1, iDto.getPrice() * quantity);
			ps.setString(2, id);
			int rs = ps.executeUpdate();
			if (rs == 1) {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

				ps = conn.prepareStatement(
						"insert into orderlistdto (idx, item, id, quantity, purchased, pdate, price) values (order_seq.nextval, ?, ?, ?, 1, sysdate, ?)");
				ps.setInt(1, iDto.getIdx());
				ps.setString(2, id);
				ps.setInt(3, quantity);
				ps.setInt(4, iDto.getPrice());
				ps.executeUpdate();
				System.out.println("구매완료");
			} else {
				System.out.println("구매 실패");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 장바구니 추가 병합 완료
	public void addCart(int quantity) {
		System.out.println("장바구니에 담았습니다");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"insert into orderlistdto (idx, item, id, quantity, purchased, price) values (order_seq.nextval, ?, ?, ?, 0, ?)");
			ps.setInt(1, iDto.getIdx());
			ps.setString(2, id);
			ps.setInt(3, quantity);
			ps.setInt(4, iDto.getPrice());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 장바구니 구매 완료
	public void purchaseCart() {
		System.out.println("장바구니 내역입니다.");
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement(
					"select idx, item, price, quantity from orderlistdto where id = ? and purchased = 0");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			orderListDtos = new ArrayList<>();
			while (rs.next()) {
				OrderListDTO dto = new OrderListDTO();
				dto.setIndex(rs.getInt("idx"));
				dto.setItem(rs.getInt("item"));
				dto.setId(id);
				dto.setPrice(rs.getInt("price"));
				dto.setQuantity(rs.getInt("quantity"));
				orderListDtos.add(dto);
			}
			displayOrderList();
			int sum = 0;
			for (OrderListDTO dto : orderListDtos) {
				sum += dto.getPrice() * dto.getQuantity();
			}
			System.out.println(orderListDtos.size() + " 건의 총 결제 금액 : " + sum);
			System.out.println("==========================================================================");
			System.out.println("1. 구매 아무키. 돌아가기");
			String temp = sc.nextLine();
			if (temp.equals("1")) {
				ps = conn.prepareStatement("update customerdto set point = point - ? where id = ?");
				ps.setInt(1, sum);
				ps.setString(2, id);
				if (ps.executeUpdate() == 1) {
					for (OrderListDTO dto : orderListDtos) {
						ps = conn.prepareStatement(
								"update orderlistdto set purchased = 1, pdate = sysdate where idx = ?");
						ps.setInt(1, dto.getIndex());
						ps.execute();
					}
					System.out.println("구매 완료");
				} else {
					System.out.println("포인트가 부족합니다.");
				}
			}
		} catch (SQLException e) {
			System.out.println("포인트가 부족합니다.");
		} finally {
			closeConn();
		}
	}

	// 회원 정보 수정 병합 완료
	private void editUser() {
		point: while (true) {
			displayUserInfo(id);
			System.out.println("회원 정보 메뉴입니다.");
			System.out.println("1.비밀번호수정 2.주소수정 3.전화번호수정 4.이메일수정 5.포인트충전 6.회원탈퇴 신청 0.돌아가기");
			int key = getInt();
			switch (key) {
			case 1:
				editUserPw();
				break;
			case 2:
				editUserAddress();
				break;
			case 3:
				editUserPhone();
				break;
			case 4:
				editUserEmail();
				break;
			case 5:
				editUserPoint();
				break;
			case 6:
				editUserResign();
				break;
			case 0:
				break point;
			default:
				System.out.println("입력 범위 초과");
				break;
			}
		}
	}

	// 비밀번호 수정 병합 완료
	public void editUserPw() {
		System.out.println("변경할 비밀번호를 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set pw= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, id);
			if (ps.executeUpdate() == 1) {
				System.out.println("비밀번호 변경 완료");
			} else {
				System.out.println("입력 오류입니다. 8~16byte의 크기로 입력하세요.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}

	}

	// 주소 수정 병합 완료
	public void editUserAddress() {
		while (true) {
			System.out.println("1.주소검색 2.주소변경 0.돌아가기");
			String temp = sc.nextLine();
			if (temp.equals("1")) {
				jusoAPI();
			}
			if (temp.equals("2")) {
				System.out.println("변경하려는 주소를 입력하세요.");
				String str1 = sc.nextLine();
				try {
					conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					PreparedStatement ps = conn.prepareStatement("update CustomerDTO set address= ? where id = ?");
					ps.setString(1, str1);
					ps.setString(2, id);
					if (ps.executeUpdate() == 1) {
						System.out.println("주소 변경 완료");
					} else {
						System.out.println("입력 오류입니다.");
					}
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				} finally {
					closeConn();
				}
			}
			if (temp.equals("0")) {
				break;
			} else {
				System.out.println("입력 오류");
			}
		}
		

	}

	// 전화번호 수정 병합 완료
	public void editUserPhone() {
		System.out.println("변경할 번호를 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set phone= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, id);
			if (ps.executeUpdate() == 1) {
				System.out.println("전화번호 변경 완료");
			} else {
				System.out.println("입력 오류입니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}

	}

	// 이메일 수정 병합 완료
	public void editUserEmail() {
		System.out.println("변경할 이메일을 입력해주세요.");
		String str1 = sc.nextLine();
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("update CustomerDTO set email= ? where id = ?");
			ps.setString(1, str1);
			ps.setString(2, id);
			if (ps.executeUpdate() == 1) {
				System.out.println("이메일 변경 완료");
			} else {
				System.out.println("입력 오류입니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 포인트 수정 병합 완료
	public void editUserPoint() {
		System.out.println("충전할 포인트를 입력해주세요.");
		int value = getInt();
		if (value < 0) {
			System.out.println("음수 입력 불가입니다.");
			return;
		}
		try {
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement ps = conn.prepareStatement("select point from customerdto where id = ?");
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int point = rs.getInt("point");
			ps = conn.prepareStatement("update CustomerDTO set point= ? where id = ?");
			ps.setInt(1, point + value);
			ps.setString(2, id);
			if (ps.executeUpdate() == 1) {
				System.out.println("포인트 충전 완료");
			} else {
				System.out.println("입력 오류입니다.");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			closeConn();
		}
	}

	// 회원 탈퇴 신청 완료
	public void editUserResign() {
		System.out.println("회원 탈퇴 신청을 하시겠습니까?");
		System.out.println("1.회원 탈퇴 아무키.취소");
		String str1 = sc.nextLine();
		if (str1.equals("1")) {
			try {
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement("update CustomerDTO set resign= 1 where id = ?");
				ps.setString(1, id);
				if (ps.executeUpdate() == 1) {
					System.out.println("회원 탈퇴 신청 완료");
				} else {
					System.out.println("입력 오류입니다.");
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				closeConn();
			}
		}
	}

	//주소 api를 활용한 주소지 검색
	public void jusoAPI() {
		HttpClient client = HttpClients.createDefault();
		while (true) {
			try {
				System.out.println("주소를 검색하세요. 0.돌아가기");
				String search = sc.nextLine();
				if (search.equals("0")) {
					break;
				}
				URIBuilder builder = new URIBuilder("https://business.juso.go.kr/addrlink/addrLinkApiJsonp.do");
				builder.addParameter("curruntPage", "1");
				builder.addParameter("countPerPage", "30");
				builder.addParameter("keyword", search);
				builder.addParameter("confmKey", "devU01TX0FVVEgyMDIzMTEwNzE2MTE1MTExNDI0OTI=");
				builder.addParameter("resultType", "json");
				HttpGet request = new HttpGet(builder.build());

				HttpResponse response = client.execute(request);
				String responseBody = EntityUtils.toString(response.getEntity());
				responseBody = responseBody.substring(1, responseBody.length() - 1);
				JsonElement jElement = JsonParser.parseString(responseBody);
				JsonObject jobj = jElement.getAsJsonObject().getAsJsonObject("results");
				JsonArray juso = (JsonArray) jobj.get("juso");
				for (int i = 0; i < juso.size(); i++) {
					JsonObject tempObj = juso.get(i).getAsJsonObject();
					System.out.println((i + 1) + ". " + tempObj.get("roadAddr") + " " + tempObj.get("zipNo"));
				}
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
