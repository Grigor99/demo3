package com.example.demo.controller;

import com.example.demo.model.ModuleBE;
import com.example.demo.model.NodeBE;
import com.example.demo.model.TestBE;
import com.example.demo.repo.NodeBERepo;
import com.example.demo.repo.TestBeRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    private final NodeBERepo nodeBERepol;

    private final TestBeRepo testBeRepo;
    public TestController(NodeBERepo nodeBERepol, TestBeRepo testBeRepo) {
        this.nodeBERepol = nodeBERepol;
        this.testBeRepo = testBeRepo;
    }
    //    @PersistenceContext()
//    private EntityManager entityManager;

//    @GetMapping
//    public List getAnswer() {
//
//        Map<Long, Long> nodeIdMap = new HashMap<>();
//        List<Long> subList = new ArrayList<>();
//        subList.add(55L);
//        subList.add(55L);
//
//
//        String ql = "SELECT n.id, n.nodeId FROM NodeBE n WHERE n.id IN :nodeIds ";
//
//
//        TypedQuery<Object[]> query = entityManager.createQuery(ql, Object[].class);
//
//        query.setParameter("nodeIds", subList);
//        System.out.println( query.getResultList());
//
//
//        return query.getResultList();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id){
        nodeBERepol.deleteById(id);
        return ResponseEntity.ok("done");
    }
    @PostMapping("")
    @Transactional
    public ResponseEntity<String> pinsert(){
        TestBE testBe = new TestBE();
        testBe.setName("name2");

        ModuleBE moduleBE = new ModuleBE();
        moduleBE.setName("ModuleName"); // Set the appropriate name
        testBe.setModule(moduleBE);

        testBeRepo.save(testBe);

        return ResponseEntity.ok("done");
    }




    ///ghgjgugug

}

