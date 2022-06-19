package P004_Item_MappedSuperClass;

import hellojpa.Member3;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team_M extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tttt") //읽기 전용이다.
    private List<Member_M> members=new ArrayList<>();

    //연관관계 편의 메서드를 생성하자.
    //02번 버젼
    public void addMember(Member_M member_m){
        member_m.setTttt(this);
        members.add(member_m);
    }

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

}
