package sources.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sources.Entities.Section;
import sources.Services.SectionService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SectionConroller {

    @Autowired
    private SectionService sectionService;

    // Pobieranie wszystkich grup
    @RequestMapping(value = "/sections", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Section> getSections(Model model) {
        return sectionService.getSections();
    }


    //Pobieranie grup, ale stronicowane
    @RequestMapping(value = "/section/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Section> listAllSectionsPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return sectionService.listAllSectionsPaging(pageNr, howManyOnPage.orElse(2));
    }

    // Pobieranie grupy o konkretnym id
    @RequestMapping(value = "/sections/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Section getSectionById(@PathVariable("id") Integer publicId) {
        return sectionService.getSectionById(publicId);
    }


    // Wyszukiwanie grup na podstawie imienia
   // @RequestMapping(value = "/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   // public Iterable<Section> getGroupByName(@RequestParam("nazwa") String publicNazwa) {
  //      return groupService.getGroupById(publicNazwa);
  //  }






    // Utworzenie nowej grupy w bazie
    @RequestMapping(value = "/section", method = RequestMethod.POST)
    public ResponseEntity<Section> createSection(@RequestBody Section section, UriComponentsBuilder ucBuilder) {
        sectionService.saveSection(section);
        return ResponseEntity.ok().body(section);
    }



    // Aktualizacja danych grupy
    @RequestMapping(value = "/section", method = RequestMethod.PUT)
    public ResponseEntity<?> editSection(@RequestBody Section section) {

        Section currentSection = sectionService.getSectionById(section.getId());

        if(currentSection == null)
            return new ResponseEntity("Unable to upate. Section with id " + section.getId() + " not found.", HttpStatus.NOT_FOUND);
        else {
            sectionService.saveSection(section);
            return new ResponseEntity<>(currentSection, HttpStatus.CREATED);
        }
    }

    // Usuwanie grupy o konkretnym id
    @RequestMapping(value = "/sections/{id}", method = RequestMethod.DELETE)
    public void deleteSection(@PathVariable("id") Integer publicId) {
        sectionService.deleteSection(publicId);
        //return new RedirectView("/api/groups", true);
    }

    // Usuwanie wszystkich grup
    @RequestMapping(value = "/sections", method = RequestMethod.DELETE)
    public void deleteSections() {
        sectionService.deleteSections();
        //return new RedirectView("/api/groups", true);
    }


    // Endpoint liczba elementów
    @RequestMapping(value = "/sections/number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countSections() {
        return sectionService.countSections();
    }

}