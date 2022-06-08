package hellojpa;

import javax.persistence.*;

@Entity
@SequenceGenerator(
        name = "MEMBER2_SEQ_GENERATOR",
        sequenceName = "MEMBER2_SEQ",
        initialValue = 1, allocationSize = 50)
public class Member2 {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO) //DB방언에 따라서 자동 생성
    //private String id;

    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                                    generator = "MEMBER2_SEQ_GENERATOR"

    )
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;


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
}
