package by.itr.fanfictionsapp.services.dto;

import by.itr.fanfictionsapp.models.Fanfiction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FanfictionResponseDTO {

    private Long totalRecords;
    private List<FanfictionDTO> fanfictions;

    public FanfictionResponseDTO(Page<Fanfiction> fanfictions){
        this.fanfictions = StreamSupport.stream(fanfictions.spliterator(), false)
                .map(FanfictionDTO::new)
                .collect(Collectors.toList());
        this.totalRecords = fanfictions.getTotalElements();
    }

}
