package sources.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sources.Entities.Criminal;
import sources.Repositories.CriminalRepository;

@Service
public class CriminalServiceImpl implements CriminalService {

    @Autowired
    private CriminalRepository criminalRepository;

    @Override
    public Iterable<Criminal> listAllCriminalsPaging(Integer pageNr, Integer howManyOnPage) { return criminalRepository.findAll(new PageRequest(pageNr,howManyOnPage)); }

    @Override
    public Iterable<Criminal> getCriminals() { return criminalRepository.findAll(); }

    @Override
    public Criminal getCriminalById(Integer id) { return criminalRepository.findOne(id); }

    @Override
    public Criminal saveCriminal(Criminal criminal) {
        return criminalRepository.save(criminal);
    }

    @Override
    public void deleteCriminal(Integer id) {
        criminalRepository.delete(id);
    }

    @Override
    public void deleteCriminals() { criminalRepository.deleteAll(); }

    @Override
    public Iterable<Criminal> getCriminalByLastName(String lastName) { return criminalRepository.findByLastName(lastName); }


    @Override
    public Integer countCriminals() { return Integer.valueOf(String.valueOf(criminalRepository.count())); }

        @Override
    public Boolean checkIfExist(Integer id) {
        if (criminalRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
