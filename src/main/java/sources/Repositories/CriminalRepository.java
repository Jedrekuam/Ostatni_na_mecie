package sources.Repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sources.Entities.Criminal;


@Repository
public interface CriminalRepository extends CrudRepository<Criminal, Integer>, PagingAndSortingRepository<Criminal, Integer> {
    Criminal findById(Integer id);

    @Query("select count(*) from Criminal p where p.id = ?1")
    Integer checkIfExist(Integer id);

    @Query("select p from Criminal p where p.lastName like %:lastName%")
    Iterable<Criminal> findByLastName(@Param("lastName") String lastName);


}