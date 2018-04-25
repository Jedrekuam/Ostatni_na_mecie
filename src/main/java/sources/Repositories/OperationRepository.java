package sources.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import sources.Entities.Operation;


@Repository
public interface OperationRepository extends CrudRepository<Operation, Integer>, PagingAndSortingRepository<Operation, Integer> {
        Operation findById(Integer id);

        @Query("select count(*) from Operation p where p.id = ?1")
        Integer checkIfExist(Integer id);
    }
