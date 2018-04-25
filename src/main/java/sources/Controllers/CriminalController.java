package sources.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sources.Entities.Criminal;
import sources.Services.CriminalService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CriminalController {

    @Autowired
    private CriminalService criminalService;

    @RequestMapping(value = "/criminals", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Criminal> getCriminals(Model model) {
        return criminalService.getCriminals();
    }

    @RequestMapping(value = "/criminal/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Criminal> listAllCriminalsPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return criminalService.listAllCriminalsPaging(pageNr, howManyOnPage.orElse(2));
    }

    @RequestMapping(value = "/criminals/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Criminal getCriminalById(@PathVariable("id") Integer publicId) {
        return criminalService.getCriminalById(publicId);
    }



    @RequestMapping(value = "/criminal", method = RequestMethod.POST)
    public ResponseEntity<Criminal> createCriminal(@RequestBody Criminal criminal) {
        criminalService.saveCriminal(criminal);
        return ResponseEntity.ok().body(criminal);
    }

    @RequestMapping(value = "/criminal", method = RequestMethod.PUT)
    public ResponseEntity<Void> editCriminal(@RequestBody Criminal criminal) {
        if(!criminalService.checkIfExist(criminal.getId()))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else {
            criminalService.saveCriminal(criminal);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @RequestMapping(value = "/criminals/{id}", method = RequestMethod.DELETE)
    public void deleteCriminal(@PathVariable("id") Integer publicId) {
        criminalService.deleteCriminal(publicId);
        //return new RedirectView("/api/bands", true);
    }

    @RequestMapping(value = "/criminals", method = RequestMethod.DELETE)
    public void deleteCriminals() {
        criminalService.deleteCriminals();
        //return new RedirectView("/api/bands", true);
    }

    // Endpoint liczba elementów
    @RequestMapping(value = "/criminals/number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countCriminals() {
        return criminalService.countCriminals();
    }
}