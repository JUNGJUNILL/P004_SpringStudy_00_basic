package Item_SINGLE_TABLE_STRATEGY;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //default는 SINGLE_TABLE이다. 테이블 하나로 다 때려 박는 방법
@DiscriminatorColumn //DTYPE이라는 컬럼이 생성(clientgubun역할을 하는 컬럼) , SINGLE_TABLE 일 경우 안써도 DTYPE이 자동으로 생성
public class Item_S {


    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
