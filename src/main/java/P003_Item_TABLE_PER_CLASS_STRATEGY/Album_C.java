package P003_Item_TABLE_PER_CLASS_STRATEGY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album_C extends Item_C {


    private String artist;



}
