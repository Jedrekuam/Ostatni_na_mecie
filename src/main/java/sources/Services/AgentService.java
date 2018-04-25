package sources.Services;

import sources.Entities.Agent;

public interface AgentService {

    Iterable<Agent> getAgents();
    Agent getAgentById(Integer id);
    Agent saveAgent(Agent agent);
    void deleteAgent(Integer id);
    void deleteAgents();

    Integer countAgents();

    Boolean checkIfExist(Integer id);
    public Iterable<Agent> listAllAgentsPaging(Integer pageNr, Integer howManyOnPage);

}