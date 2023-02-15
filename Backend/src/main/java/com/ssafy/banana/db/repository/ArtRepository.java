package com.ssafy.banana.db.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.dto.response.ArtResponse;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id")
	List<ArtResponse> findAllArts();

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "where a.artRegDate >= :twoWeeksAgo "
		+ "order by a.artRegDate desc ")
	List<ArtResponse> findNewArts(@Param("twoWeeksAgo") LocalDateTime twoWeeksAgo);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "where a.artist.id = :userSeq")
	List<ArtResponse> findMyArts(@Param("userSeq") Long userSeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "where a.artist.id = :userSeq "
		+ "and a.isRepresent = true")
	List<ArtResponse> findMasterpieces(@Param("userSeq") Long userSeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from User u join MyArt ma "
		+ "on u.id = ma.user.id "
		+ "join Art a "
		+ "on ma.art.id = a.id "
		+ "where u.id = :userSeq")
	List<ArtResponse> findLikedArt(@Param("userSeq") Long userSeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "where a.artCategory.id = :artCategorySeq")
	List<ArtResponse> findArtsbyCategory(@Param("artCategorySeq") Long artCategorySeq);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "where a.artRegDate >= :twoWeeksAgo "
		+ "order by a.artLikeCount desc")
	List<ArtResponse> findAllOrderByArtLikeCountAndArtRegDate(@Param("twoWeeksAgo") LocalDateTime twoWeeksAgo);

	@Query("select new com.ssafy.banana.dto.response.ArtResponse(a, u) "
		+ "from Art a join User u "
		+ "on a.artist.id = u.id "
		+ "order by a.artHit desc, a.id asc ")
	List<ArtResponse> findAllOrderByArtHit();

	@Query("select count(a.id) "
		+ "from Art a "
		+ "where a.artist.id = :userSeq")
	int countArtByArtistSeq(@Param("userSeq") Long userSeq);

	Optional<List<Art>> findAllByArtist_Id(long userSeq);
}
