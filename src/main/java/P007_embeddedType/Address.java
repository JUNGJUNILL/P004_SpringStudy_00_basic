package P007_embeddedType;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    //주소속성
    private String city;
    private String street;
    private String zipcode;
    //-------------------------------

    public Address(){}; //기본 생성자 필수
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    /*
    값 타입의 비교
    • 동일성(identity) 비교: 인스턴스의 참조 값을 비교, == 사용
    • 동등성(equivalence) 비교: 인스턴스의 값을 비교, equals()
    사용
    • 값 타입은 a.equals(b)를 사용해서 동등성 비교를 해야 함
    • 값 타입의 equals() 메소드를 적절하게 재정의(주로 모든 필드
    사용)
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(street, address.street) && Objects.equals(zipcode, address.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, street, zipcode);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
