package sources.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sources.Entities.Operation;
import sources.Services.OperationService;

import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class OperationConroller {

    @Autowired
    private OperationService operationService;

    // Pobieranie wszystkich operacji
    @RequestMapping(value = "/operations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Operation> getOperations(Model model) {
        return operationService.getOperations();
    }


    //Pobieranie operacji, ale stronicowane
    @RequestMapping(value = "/operation/{page}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Operation> listAllOperationPaging(@PathVariable("page") Integer pageNr, @RequestParam("size") Optional<Integer> howManyOnPage) {
        return operationService.listAllOperationsPaging(pageNr, howManyOnPage.orElse(2));
    }

    // Pobieranie operacji o konkretnym id
    @RequestMapping(value = "/operations/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Operation getOperationById(@PathVariable("id") Integer publicId) {
        return operationService.getOperationById(publicId);
    }

    // Utworzenie nowej operacji w bazie
    @RequestMapping(value = "/operation", method = RequestMethod.POST)
    public ResponseEntity<Operation> createOperation(@RequestBody Operation operation, UriComponentsBuilder ucBuilder) {
        operationService.saveOperation(operation);
        return ResponseEntity.ok().body(operation);
    }



    // Aktualizacja danych operacji
    @RequestMapping(value = "/operation", method = RequestMethod.PUT)
    public ResponseEntity<?> editOperation(@RequestBody Operation operation) {

        Operation currentOperation = operationService.getOperationById(operation.getId());

        if(currentOperation == null)
            return new ResponseEntity("Unable to update. Operation with id " + operation.getId() + " not found.", HttpStatus.NOT_FOUND);
        else {
            operationService.saveOperation(operation);
            return new ResponseEntity<>(currentOperation, HttpStatus.CREATED);
        }
    }

    // Usuwanie operacji o konkretnym id
    @RequestMapping(value = "/operations/{id}", method = RequestMethod.DELETE)
    public void deleteOperation(@PathVariable("id") Integer publicId) {
        operationService.deleteOperation(publicId);
        //return new RedirectView("/api/songs", true);
    }

    // Usuwanie wszystkich operacji
    @RequestMapping(value = "/operations", method = RequestMethod.DELETE)
    public void deleteOperations() {
        operationService.deleteOperations();
        //return new RedirectView("/api/songs", true);
    }

    // Endpoint liczba elementów
    @RequestMapping(value = "/operations/number", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer countOperations() {
        return operationService.countOperations();
    }
}