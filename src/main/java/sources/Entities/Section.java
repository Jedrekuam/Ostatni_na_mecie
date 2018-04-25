package sources.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

import javax.persistence.*;

@SequenceGenerator(name="sec", initialValue=1, allocationSize=1)


@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refSec", scope=Section.class)
@Entity
@Table
public class Section {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec")
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column
    private String name;

    @Column
    private String activityType;
      
    @OneToMany
    private List<Criminal> criminals;
    
    @OneToOne
    private Operation operation;

    public Section() {}

    public Section(String name, String activityType) {
        this.name = name;
        this.activityType = activityType;
    }

    public Integer getId() {return id;}
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getActivityType() { return activityType; }
    public void setActivityType(String activityType) { this.activityType = activityType; }

    public List<Criminal> getCriminals() {return criminals;}
    public void setCriminals(List<Criminal> criminals) {this.criminals = criminals;}
    
    public Operation getOperation() {return operation;}
    public void setOperation(Operation operation) {this.operation = operation;}

    @Override
    public String toString()
    {
        return new String(id + " " + name + " " + activityType);
    }

}

