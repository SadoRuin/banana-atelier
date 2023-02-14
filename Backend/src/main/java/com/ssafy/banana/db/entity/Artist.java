package com.ssafy.banana.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "artist")
public class Artist {
	@Id
	@Column(name = "user_seq", nullable = false)
	private Long id;

	@MapsId
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user_seq", nullable = false)
	@JsonIgnore
	private User user;

	@Size(max = 100)
	@Column(name = "instagram_link", length = 100)
	private String instagramLink;

	@Size(max = 100)
	@Column(name = "twitter_link", length = 100)
	private String twitterLink;

	@Size(max = 100)
	@Column(name = "youtube_link", length = 100)
	private String youtubeLink;

	@Size(max = 100)
	@Column(name = "blog_link", length = 100)
	private String blogLink;

	@Size(max = 500)
	@Column(name = "artist_intro", length = 500)
	private String artistIntro;

}
