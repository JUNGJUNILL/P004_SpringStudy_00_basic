package P004_Item_MappedSuperClass;


import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseEntity {


    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifieBy;
    private LocalDateTime lastModifiedDate;


    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifieBy() {
        return lastModifieBy;
    }

    public void setLastModifieBy(String lastModifieBy) {
        this.lastModifieBy = lastModifieBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
