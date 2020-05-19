package me.zw.workbook.repository;

import me.zw.workbook.domain.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@RepositoryRestResource(path = "journal", collectionResourceRel = "entry")
public interface JournalRepository extends JpaRepository<JournalEntry, Long> {

    List<JournalEntry> findByCreatedAfter(@Param("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date);

    List<JournalEntry> findByCreatedBetween(@Param("after") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date after,
                                            @Param("before") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date before);

    List<JournalEntry> findByTitleContaining(@Param("word") String word);

    List<JournalEntry> findBySummaryContaining(@Param("word") String word);
}
