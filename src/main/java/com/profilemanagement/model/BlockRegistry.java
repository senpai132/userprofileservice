package com.profilemanagement.model;

import javax.persistence.*;

@Entity
@Table(name = "blockregistry")
public class BlockRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
  
    @Column(name = "asker")
    public String initiator;

    @Column(name = "target")
    public String target;

    public BlockRegistry() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
