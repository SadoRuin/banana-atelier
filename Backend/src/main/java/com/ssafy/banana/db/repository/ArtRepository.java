package com.ssafy.banana.db.repository;

import com.ssafy.banana.db.entity.Art;
import com.ssafy.banana.dto.response.ArtResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends JpaRepository<Art, Long> {

    @Query("select a, u from Art a join User u on a.artist.id = u.id")
    List<ArtResponseDto> findAllArts();

    @Query("select a, u from Art a join User u on a.artist.id = u.id where a.artist.id = :userSeq")
    List<ArtResponseDto> findMyArts(@Param("userSeq") Long userSeq);


    @Query("select a, u " +
            "from User u " +
            "join MyArt ma on u.id = ma.user.id " +
            "join Art a on ma.art.id = a.id " +
            "where u.id = :userSeq")
    List<ArtResponseDto> findLikedArt(@Param("userSeq") Long userSeq);
}
