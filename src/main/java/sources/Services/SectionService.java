package sources.Services;

import sources.Entities.Section;

public interface SectionService {

    Iterable<Section> getSections();
    Section getSectionById(Integer id);
    Section saveSection(Section section);
    void deleteSection(Integer id);
    void deleteSections();

    Integer countSections();

    Boolean checkIfExist(Integer id);
    public Iterable<Section> listAllSectionsPaging(Integer pageNr, Integer howManyOnPage);

}