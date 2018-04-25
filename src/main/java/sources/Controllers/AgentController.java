package sources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sources.Entities.Agent;
import sources.Services.AgentService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AgentController {

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "/agents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Agent> getAgents(Model model) {
        return agentService.getAgents();
    }

    @RequestMapping(value = "/agent/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Agent> listAllAgentsPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return agentService.listAllAgentsPaging(pageNr, howManyOnPage.orElse(2));
    }

    @RequestMapping(value = "/agents/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Agent getAgentById(@PathVariable("id") Integer publicId) {
        return agentService.getAgentById(publicId);
    }


    @RequestMapping(value = "/agent", method = RequestMethod.POST)
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) {
        agentService.saveAgent(agent);
        return ResponseEntity.ok().body(agent);
    }

    @RequestMapping(value = "/agent", method = RequestMethod.PUT)
    public ResponseEntity<Void> editAgent(@RequestBody Agent agent) {
        if(!agentService.checkIfExist(agent.getId()))
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        else {
            agentService.saveAgent(agent);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/agents/{id}", method = RequestMethod.DELETE)
    public void deleteAgent(@PathVariable("id") Integer publicId) {
        agentService.deleteAgent(publicId);
        //return new RedirectView("/api/agents", true);
    }

    @RequestMapping(value = "/agents", method = RequestMethod.DELETE)
    public void deleteAgents() {
        agentService.deleteAgents();
      //return new RedirectView("/api/agents", true);
    }

    // Endpoint liczba elementów
    @RequestMapping(value = "/agents/number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countAgents() {
        return agentService.countAgents();
    }
}