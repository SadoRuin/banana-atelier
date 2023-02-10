package com.ssafy.banana.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.ssafy.banana.db.entity.Artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CurationRequest implements Serializable {
	private Long curationSeq;
	private LocalDateTime curationStartTime;
	private String curationName;
	private String curationSummary;
	private Long artistSeq;
	private int isAuction;
}
