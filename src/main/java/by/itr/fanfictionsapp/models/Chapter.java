package by.itr.fanfictionsapp.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Chapter {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Lob
    private String textBlock;

    private String imageURL;

}
