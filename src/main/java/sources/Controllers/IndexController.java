package sources.Controllers;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sources.Entities.Agent;
import sources.Entities.Criminal;
import sources.Entities.Section;
import sources.Entities.Operation;
import sources.Services.AgentService;
import sources.Services.CriminalService;
import sources.Services.SectionService;
import sources.Services.OperationService;

/**
 * Homepage controller.
 */
@RestController
@RequestMapping("/")
@CrossOrigin
public class IndexController {

    @Autowired
    private CriminalService criminalService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private OperationService operationService;

    @Autowired
    private AgentService agentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "It works!";
    }

    @RequestMapping(value = "clearAll", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String clearAll() {
        criminalService.deleteCriminals();
        sectionService.deleteSections();
        operationService.deleteOperations();
        agentService.deleteAgents();

        return "Model Cleared!";
    }


    @RequestMapping(value = "generateModel", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE)
    public String generateModel() {

        Agent a1 = new Agent("Krzysztof", "Rutkowski");
        Agent a2 = new Agent("Marek", "Smarek");

        Criminal c1 = new Criminal("Lelek", "Zlecenia zabójstw");
        Criminal c2 = new Criminal("Mackiewicz", "On może wszystko");

        Section g1 = new Section("Grupa piątkowska", "Haracze, rabunki, taka sytuacja");
        Section g2 = new Section("Grupa specjalnej troski", "ebe ebe");

        Operation o1 = new Operation("Zdobycie zaufania szefa grupy", Date.valueOf("2018-04-04"));
        Operation o2 = new Operation("Zlikwidowanie świętego Mikołaja", Date.valueOf("2019-12-24"));

        agentService.saveAgent(a1);
        agentService.saveAgent(a2);
        
        criminalService.saveCriminal(c1);
        criminalService.saveCriminal(c2);
        
        sectionService.saveSection(g1);
        sectionService.saveSection(g2);
        
        operationService.saveOperation(o1);
        operationService.saveOperation(o2);

        return "Model Generated";
    }
}
