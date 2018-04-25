package sources.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@SequenceGenerator(name="ag", initialValue=1, allocationSize=1)


@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refAgent", scope=Agent.class)
@Entity
@Table
public class Agent {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ag")
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
    
    
    
    @OneToOne(cascade=CascadeType.REMOVE)
    private Operation operation;
    
    

    public Agent() {}

    public Agent(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Operation getOperation() { return operation;}
    public void setOperation(Operation operation) {this.operation = operation;}
    
    
    @Override
    public String toString()
    {
        return new String(id + " " + firstName + " " + lastName);
    }

}

