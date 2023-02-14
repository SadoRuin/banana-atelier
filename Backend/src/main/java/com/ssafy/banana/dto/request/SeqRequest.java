package com.ssafy.banana.dto.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel("PK만 데이터로 사용하는 요청 DTO")
public class SeqRequest implements Serializable {
	private long seq;
}