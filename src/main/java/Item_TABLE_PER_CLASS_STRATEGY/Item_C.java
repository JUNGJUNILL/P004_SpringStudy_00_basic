package Item_TABLE_PER_CLASS_STRATEGY;


import javax.persistence.*;

//추천하는 방법은 아님
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) //default는 SINGLE_TABLE이다. 테이블 하나로 다 때려 박는 방법
public abstract class Item_C {


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
