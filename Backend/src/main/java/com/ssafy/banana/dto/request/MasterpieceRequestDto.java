package com.ssafy.banana.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MasterpieceRequestDto {

    private Long userSeq;
    private Long artSeq;
    private boolean isRepresent;
}
