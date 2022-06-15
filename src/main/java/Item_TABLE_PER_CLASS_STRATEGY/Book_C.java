package Item_TABLE_PER_CLASS_STRATEGY;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
public class Book_C extends Item_C {

    private String author;
    private String isbn;
}
