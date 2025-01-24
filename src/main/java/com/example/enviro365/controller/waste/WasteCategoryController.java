package com.example.enviro365.controller.waste;
import java.util.List;

import com.example.enviro365.controller.NotFoundException;
import com.example.enviro365.entity.waste.WasteCategory;
import com.example.enviro365.entity.waste.WasteCategoryModelAssembler;
import com.example.enviro365.repository.WasteCategoryRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WasteCategoryController {

    private final WasteCategoryRepository repository;
    private final WasteCategoryModelAssembler assembler;

    public WasteCategoryController(WasteCategoryRepository repository, WasteCategoryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/categories")
    public CollectionModel<EntityModel<WasteCategory>> get() {
        List<EntityModel<WasteCategory>> employees = repository.findAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(employees);
    }
    // end::get-aggregate-root[]


    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody WasteCategory newWasteCategory) {
        EntityModel<?> employeeModel = assembler.toModel(repository.save(newWasteCategory));

        return ResponseEntity.created(employeeModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(employeeModel);
    }

    // Single item

    @GetMapping("/categories/{id}")
    public EntityModel<WasteCategory> get(@PathVariable Long id) {

        WasteCategory wasteCategory = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(wasteCategory);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<?> put(@RequestBody WasteCategory newWasteCategory, @PathVariable Long id) {

        WasteCategory _wasteCategory =  repository.findById(id)
                .map(employee -> {
                    employee.setName(newWasteCategory.getName());
                    employee.setGuideLineId(newWasteCategory.getGuideLineId());
                    return repository.save(employee);
                })
                .orElseGet(() -> repository.save(newWasteCategory));

        EntityModel<?> employeeModel = assembler.toModel(_wasteCategory);
        return ResponseEntity.created(employeeModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(employeeModel);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}