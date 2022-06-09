package hellojpa;

import javax.persistence.*;


//FK가 있는 곳이 연관관계의 주인이다.
//Member3 (N) : Team (1)
@Entity
public class Member3 {

    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team tttt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return tttt;
    }

    //연관관계 편의 메서드를 생성하자.
    //01번 버젼
    public void changeTeam(Team team) {
        this.tttt = team;
        tttt.getMember3().add(this);
    }

    public Team getTttt() {
        return tttt;
    }

    public void setTttt(Team tttt) {
        this.tttt = tttt;
    }
}
