package P007_embeddedType;


import P004_Item_MappedSuperClass.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member_em {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;

    //기간속성
    @Embedded
    private Period period;

    //주소속성
    @Embedded
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name ="city",
                                                column =@Column(name="work_city")),
            @AttributeOverride(name ="street",
                                                column =@Column(name="work_street")),
            @AttributeOverride(name ="zipcode",
                                                column =@Column(name="work_zipcode")),
    })
    private Address workAdress;


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
