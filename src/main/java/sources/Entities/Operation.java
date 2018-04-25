package sources.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Date;

@SequenceGenerator(name="op", initialValue=1, allocationSize=1)


@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class,
        property="refOp", scope=Operation.class)
@Entity
@Table
public class Operation {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "op")
    @Column(nullable = false, updatable = false)
    private Integer id;

    @Column
    private String goal;

    @Column
    private Date operationDate;
    
    @OneToOne
    private Agent agent;
    
    @OneToOne
    private Section section;


    public Operation() {}

    public Operation(String goal, Date operationDate) {
        this.goal = goal;
        this.operationDate = operationDate;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

    public Date getOperationDate() { return operationDate; }
    public void setOperationDate(Date operationDate) { this.operationDate = operationDate; }

    public Agent getAgent() {return agent;}
    public void setAgent(Agent agent) {this.agent = agent;}
    
    public Section getSection() {return section;}
    public void setSection(Section section) {this.section = section;}
    
    @Override
    public String toString()
    {
        return new String(id + " " + goal + " " + operationDate.toString());
    }

}

