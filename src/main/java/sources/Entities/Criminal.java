package sources.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@SequenceGenerator(name="cr", initialValue=1, allocationSize=1)

@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refCr", scope=Criminal.class)
@Entity
@Table
public class Criminal {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cr")
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String activity;
    
    
    
    @ManyToOne
    private Section section; 
    
    

    public Criminal() {}

    public Criminal(String lastName, String activity) {
        this.lastName = lastName;
        this.activity = activity;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getActivity() { return activity; }
    public void setActivity(String activity) { this.activity = activity; }

    public Section getSection() { return section;}
    public void setSection(Section section) { this.section = section; }
    
    
    
    @Override
    public String toString()
    {
        return new String(id + " " + lastName + " " + activity);
    }

}

