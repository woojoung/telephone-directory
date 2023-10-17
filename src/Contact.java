import java.io.Serializable;

public class Contact implements Serializable {
    // 연락처 저장 기본 정보, 인스턴스 변수 (non-static)
    String name, phone;

    // Contact 클래스 생성자
    public Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    // Contact 클래스 메소드
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
