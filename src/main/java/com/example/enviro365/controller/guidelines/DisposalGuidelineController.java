package com.example.enviro365.controller.guidelines;

import com.example.enviro365.controller.NotFoundException;
import com.example.enviro365.entity.guidelines.DisposalGuideline;
import com.example.enviro365.entity.guidelines.DisposalGuidelineModelAssembler;
import com.example.enviro365.entity.waste.WasteCategory;
import com.example.enviro365.entity.waste.WasteCategoryModelAssembler;
import com.example.enviro365.repository.DisposalGuidelineRepository;
import com.example.enviro365.repository.WasteCategoryRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DisposalGuidelineController {

    private final DisposalGuidelineRepository repository;
    private final DisposalGuidelineModelAssembler assembler;

    public DisposalGuidelineController(DisposalGuidelineRepository repository, DisposalGuidelineModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/guidelines")
    public CollectionModel<EntityModel<DisposalGuideline>> get() {
        List<EntityModel<DisposalGuideline>> guidelines = repository.findAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(guidelines);
    }
    // end::get-aggregate-root[]


    @PostMapping("/guidelines")
    public ResponseEntity<?> create(@RequestBody DisposalGuideline newGuideline) {
        EntityModel<?> guidelineModel = assembler.toModel(repository.save(newGuideline));

        return ResponseEntity.created(guidelineModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(guidelineModel);
    }

    // Single item

    @GetMapping("/guidelines/{id}")
    public EntityModel<?> get(@PathVariable Long id) {

        DisposalGuideline disposalGuideline = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(disposalGuideline);
    }

    @PutMapping("/guidelines/{id}")
    public ResponseEntity<?> put(@RequestBody DisposalGuideline newGuideline, @PathVariable Long id) {

        DisposalGuideline _disposalGuideline =  repository.findById(id)
                .map(guideline -> {
                    guideline.setName(newGuideline.getName());
                    guideline.setGuideLine(newGuideline.getGuideLine());
                    return repository.save(guideline);
                })
                .orElseGet(() -> repository.save(newGuideline));

        EntityModel<?> employeeModel = assembler.toModel(_disposalGuideline);
        return ResponseEntity.created(employeeModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(employeeModel);
    }

    @DeleteMapping("/guidelines/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}