package hellojpa;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "tttt") //읽기 전용이다.
    private List<Member3> members=new ArrayList<>();

    //연관관계 편의 메서드를 생성하자.
    //02번 버젼
    public void addMember(Member3 member3){
        member3.setTttt(this);
        members.add(member3);
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

    public List<Member3> getMember3() {
        return members;
    }

    public void setMember3(List<Member3> member3) {
        this.members = member3;
    }
}
