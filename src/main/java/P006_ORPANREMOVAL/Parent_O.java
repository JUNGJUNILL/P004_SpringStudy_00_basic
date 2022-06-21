package P006_ORPANREMOVAL;


import P005_CASECADE.Child;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Parent_O {

    @Id
    @GeneratedValue
    @Column(name = "parent_id")
    private Long id;
    private String name;

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Child_O> childList =new ArrayList<>();

    public List<Child_O> getChildList() {
        return childList;
    }

    public void setChildList(List<Child_O> childList) {
        this.childList = childList;
    }

    public void addChild(Child_O child){
        childList.add(child);
        child.setParent(this);
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
