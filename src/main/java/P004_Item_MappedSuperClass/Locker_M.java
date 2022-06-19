package P004_Item_MappedSuperClass;

import hellojpa.Member4;

import javax.persistence.*;

@Entity
public class Locker_M {


    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;
    private String name;

    @OneToOne(mappedBy = "locker")
    private Member_M  member_m;
}
