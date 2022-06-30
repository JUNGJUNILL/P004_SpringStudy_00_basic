package P009_embeddedTypeCollection;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS_EM02")
public class AddressEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address_em02 address;

    public AddressEntity(String city, String street, String zipCode){
            this.address = new Address_em02(city,street,zipCode);
    }

    public AddressEntity(Address_em02 address){
        this.address=address;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address_em02 getAddress() {
        return address;
    }

    public void setAddress(Address_em02 address) {
        this.address = address;
    }
}
