package sources.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sources.Entities.Operation;
import sources.Repositories.OperationRepository;


@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public Iterable<Operation> listAllOperationsPaging(Integer pageNr, Integer howManyOnPage) { return operationRepository.findAll(new PageRequest(pageNr,howManyOnPage)); }

    @Override
    public Iterable<Operation> getOperations() { return operationRepository.findAll(); }

    @Override
    public Operation getOperationById(Integer id) { return operationRepository.findOne(id); }

    @Override
    public Operation saveOperation(Operation operation) {
        return operationRepository.save(operation);
    }

    @Override
    public void deleteOperation(Integer id) {
        operationRepository.delete(id);
    }

    @Override
    public void deleteOperations() { operationRepository.deleteAll(); }

    @Override
    public Integer countOperations() { return Integer.valueOf(String.valueOf(operationRepository.count())); }


    @Override
    public Boolean checkIfExist(Integer id) {
        if (operationRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
