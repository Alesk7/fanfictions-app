package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagDTO {

    private String tag;
    private Integer weight;

    public TagDTO(Tag tag){
        this.tag = tag.getTag();
        this.weight = tag.getWeight();
    }

}
