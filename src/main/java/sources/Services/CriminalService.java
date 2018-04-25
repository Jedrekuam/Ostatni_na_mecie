package sources.Services;


import sources.Entities.Criminal;

public interface CriminalService {

    Iterable<Criminal> getCriminals();
    Criminal getCriminalById(Integer id);
    Criminal saveCriminal(Criminal criminal);
    void deleteCriminal(Integer id);
    void deleteCriminals();

    Iterable<Criminal> getCriminalByLastName(String lastName);

    Integer countCriminals();

    Boolean checkIfExist(Integer id);
    public Iterable<Criminal> listAllCriminalsPaging(Integer pageNr, Integer howManyOnPage);
}
