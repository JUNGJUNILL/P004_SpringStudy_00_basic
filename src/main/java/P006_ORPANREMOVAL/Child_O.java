package P006_ORPANREMOVAL;


import P005_CASECADE.Parent;

import javax.persistence.*;

@Entity
public class Child_O {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent_O parent;

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

    public Parent_O getParent() {
        return parent;
    }

    public void setParent(Parent_O parent) {
        this.parent = parent;
    }
}
