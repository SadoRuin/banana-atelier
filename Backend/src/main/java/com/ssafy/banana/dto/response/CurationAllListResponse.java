package com.ssafy.banana.dto.response;

import java.time.LocalDateTime;

import com.ssafy.banana.db.entity.Artist;
import com.ssafy.banana.db.entity.Curation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CurationAllListResponse {
	private String user_nickName;
	private String curationName;
	private String curationThumbnail;
	private int curationBmCount;
	private String curationHit;
	private LocalDateTime curationStartTime;


	public CurationAllListResponse(Curation curation, Artist artist){
		this.curationStartTime = curation.getCurationStartTime();
		this.curationName = curation.getCurationName();
		this.curationHit = curation.getCurationHit();
		this.curationBmCount = curation.getCurationBmCount();
		this.user_nickName = artist.getUser().getNickname();
		this.curationThumbnail = curation.getCurationThumbnail();
	}
}