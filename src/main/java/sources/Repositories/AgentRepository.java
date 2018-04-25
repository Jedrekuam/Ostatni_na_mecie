package sources.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sources.Entities.Agent;

@Repository
public interface AgentRepository extends CrudRepository<Agent, Integer>, PagingAndSortingRepository<Agent, Integer> {
    Agent findById(Integer id);

    @Query("select count(*) from Agent p where p.id = ?1")
    Integer checkIfExist(Integer id);

    @Query("select p from Agent p where p.lastName like %:lastName%")
    Iterable<Agent> findByName(@Param("lastName") String lastName);
}