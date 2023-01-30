package com.ssafy.banana.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class ArtRequestDto {
    private String artImg;
    private String artName;
    private String artDescription;
    private Long artCategorySeq;
    private Long userSeq;
}
