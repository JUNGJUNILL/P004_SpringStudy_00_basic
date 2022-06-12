package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {


    @Id
    @GeneratedValue
    @Column(name = "MEMBERPRODUCT_ID")
    private Long id;


    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member5 member5;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    private int orderamount;
    private int price;
    private LocalDateTime orderdate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(int orderamount) {
        this.orderamount = orderamount;
    }

    public LocalDateTime getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(LocalDateTime orderdate) {
        this.orderdate = orderdate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
