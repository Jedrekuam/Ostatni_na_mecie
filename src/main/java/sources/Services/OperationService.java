package sources.Services;

import sources.Entities.Operation;

public interface OperationService {

    Iterable<Operation> getOperations();
    Operation getOperationById(Integer id);
    Operation saveOperation(Operation operation);
    void deleteOperation(Integer id);
    void deleteOperations();

    Integer countOperations();

    Boolean checkIfExist(Integer id);
    public Iterable<Operation> listAllOperationsPaging(Integer pageNr, Integer howManyOnPage);
}
