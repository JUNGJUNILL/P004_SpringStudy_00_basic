package P004_Item_MappedSuperClass;

import hellojpa.Team;

import javax.persistence.*;

@Entity
public class Member_M extends BaseEntity{


    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;


    @ManyToOne
    @JoinColumn(name = "TEAM_ID" ,insertable = false, updatable = false)
    private Team_M tttt;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker_M locker;

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

    public Team_M getTttt() {
        return tttt;
    }

    public void setTttt(Team_M tttt) {
        this.tttt = tttt;
    }

    public Locker_M getLocker() {
        return locker;
    }

    public void setLocker(Locker_M locker) {
        this.locker = locker;
    }
}
