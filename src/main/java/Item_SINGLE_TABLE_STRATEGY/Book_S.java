package Item_SINGLE_TABLE_STRATEGY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book_S extends Item_S {

    private String author;
    private String isbn;
}
