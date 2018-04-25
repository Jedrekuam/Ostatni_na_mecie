package sources.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sources.Entities.Agent;
import sources.Repositories.AgentRepository;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Override
    public Iterable<Agent> listAllAgentsPaging(Integer pageNr, Integer howManyOnPage) {
        return agentRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Agent> getAgents() {
        return agentRepository.findAll();
    }

    @Override
    public Agent getAgentById(Integer id) { return agentRepository.findOne(id); }

    @Override
    public Agent saveAgent(Agent agent) {
        return agentRepository.save(agent);
    }

    @Override
    public void deleteAgent(Integer id) {
        agentRepository.delete(id);
    }

    @Override
    public void deleteAgents() { agentRepository.deleteAll(); }


    @Override
    public Integer countAgents() { return Integer.valueOf(String.valueOf(agentRepository.count())); }

        @Override
    public Boolean checkIfExist(Integer id) {
        if (agentRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
