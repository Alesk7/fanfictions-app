package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Setter
@Getter
public class Tag {

    @Id
    private String tag;

    private Integer weight;

    @ManyToMany(targetEntity = Fanfiction.class, fetch = FetchType.LAZY)
    private List<Fanfiction> fanfictions;

}
