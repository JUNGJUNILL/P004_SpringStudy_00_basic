package P008_embeddedTypeCollection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Mebmer_em01 {


    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String username;


    @Embedded
    private Address_em01 homeAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
            @JoinColumn(name = "MEMBER_ID")
    )
    @Column(name = "FOOD_NAME")
    private Set<String> favoriteFoods = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "ADDRESS_EM01", joinColumns =
            @JoinColumn(name = "MEMBER_ID")
    )
    private List<Address_em01> addressHistory = new ArrayList<>();

    public Address_em01 getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address_em01 homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address_em01> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address_em01> addressHistory) {
        this.addressHistory = addressHistory;
    }

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
