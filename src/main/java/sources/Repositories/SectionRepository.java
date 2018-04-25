package sources.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sources.Entities.Section;

@Repository
public interface SectionRepository extends CrudRepository<Section, Integer>, PagingAndSortingRepository<Section, Integer> {
    Section findById(Integer id);

    @Query("select count(*) from Section p where p.id = ?1")
    Integer checkIfExist(Integer id);
}