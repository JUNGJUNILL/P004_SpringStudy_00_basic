package Item_JOINED_STRATEGY;


import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //default는 SINGLE_TABLE이다.
@DiscriminatorColumn //DTYPE이라는 컬럼이 생성(clientgubun역할을 하는 컬럼)
public class Item {


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
