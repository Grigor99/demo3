package com.example.demo.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "NODETYPE", discriminatorType = DiscriminatorType.STRING)
public class NodeBE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MODULE_ID_FK")
    private ModuleBE module;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModuleBE getModule() {
        return module;
    }

    public void setModule(ModuleBE module) {
        this.module = module;
    }

    public String getNodetype() {
        return nodetype;
    }

    public void setNodetype(String nodetype) {
        this.nodetype = nodetype;
    }

    @Column(name = "NODETYPE")
    private String nodetype;

    public NodeBE() {
    }

    public NodeBE(Long id, ModuleBE module, String nodetype) {
        this.id = id;
        this.module = module;
        this.nodetype = nodetype;
    }
}
