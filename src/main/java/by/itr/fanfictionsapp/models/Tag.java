package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Tag {

    @Id
    private String tag;

    private Integer weight;

    @ManyToMany(targetEntity = Fanfiction.class, fetch = FetchType.LAZY)
    private List<Fanfiction> fanfictions;

    public Tag(String tag, int weight){
        this.tag = tag;
        this.weight = weight;
    }

}
