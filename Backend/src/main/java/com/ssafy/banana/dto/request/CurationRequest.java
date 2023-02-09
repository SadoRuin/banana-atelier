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
	private Long id;
	private LocalDateTime curationStartTime;
	private LocalDateTime curationEndTime;
	private String curationName;
	private String curationSummary;
	private Artist artist;
}
