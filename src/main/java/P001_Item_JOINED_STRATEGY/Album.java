package P001_Item_JOINED_STRATEGY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album extends Item{


    private String artist;



}
