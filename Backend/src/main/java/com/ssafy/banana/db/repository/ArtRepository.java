package com.ssafy.banana.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.dto.response.ArtResponse;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) from Art a join User u on a.artist.id = u.id")
	List<ArtResponse> findAllArts();

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) from Art a join User u on a.artist.id = u.id where a.artist.id = :userSeq")
	List<ArtResponse> findMyArts(@Param("userSeq") Long userSeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) " +
		"from User u " +
		"join MyArt ma on u.id = ma.user.id " +
		"join Art a on ma.art.id = a.id " +
		"where u.id = :userSeq")
	List<ArtResponse> findLikedArt(@Param("userSeq") Long userSeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) from Art a, User u where a.artCategory.id = :artCategorySeq")
	List<ArtResponse> findArtsbyCategory(@Param("artCategorySeq") Long artCategorySeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) from Art a join User u on a.artist.id = u.id order by a.artLikeCount desc")
	List<ArtResponse> findAllOrderByArtLikeCount();
}
