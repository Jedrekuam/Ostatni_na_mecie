package sources.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import sources.Entities.Section;
import sources.Repositories.SectionRepository;

@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public Iterable<Section> listAllSectionsPaging(Integer pageNr, Integer howManyOnPage) {
        return sectionRepository.findAll(new PageRequest(pageNr,howManyOnPage));
    }

    @Override
    public Iterable<Section> getSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section getSectionById(Integer id) { return sectionRepository.findOne(id); }

    @Override
    public Section saveSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Integer id) {
        sectionRepository.delete(id);
    }

    @Override
    public void deleteSections() { sectionRepository.deleteAll(); }

    @Override
    public Integer countSections() { return Integer.valueOf(String.valueOf(sectionRepository.count())); }

    @Override
    public Boolean checkIfExist(Integer id) {
        if (sectionRepository.checkIfExist(id) > 0)
            return true;
        else
            return false;
    }


}
