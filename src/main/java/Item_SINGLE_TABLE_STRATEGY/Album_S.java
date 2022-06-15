package Item_SINGLE_TABLE_STRATEGY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Album_S extends Item_S {


    private String artist;



}
