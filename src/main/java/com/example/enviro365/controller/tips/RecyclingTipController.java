package com.example.enviro365.controller.tips;

import com.example.enviro365.controller.NotFoundException;
import com.example.enviro365.entity.tips.RecyclingTip;
import com.example.enviro365.entity.tips.RecyclingTipModelAssembler;
import com.example.enviro365.repository.RecyclingTipRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecyclingTipController {

    private final RecyclingTipRepository repository;
    private final RecyclingTipModelAssembler assembler;

    public RecyclingTipController(RecyclingTipRepository repository, RecyclingTipModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/tips")
    public CollectionModel<EntityModel<RecyclingTip>> get() {
        List<EntityModel<RecyclingTip>> tips = repository.findAll().stream().map(assembler::toModel).toList();
        return CollectionModel.of(tips);
    }
    // end::get-aggregate-root[]


    @PostMapping("/tips")
    public ResponseEntity<?> create(@RequestBody RecyclingTip newTip) {
        EntityModel<?> tipModel = assembler.toModel(repository.save(newTip));

        return ResponseEntity.created(tipModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tipModel);
    }

    // Single item

    @GetMapping("/tips/{id}")
    public EntityModel<?> get(@PathVariable Long id) {

        RecyclingTip recyclingTip = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));

        return assembler.toModel(recyclingTip);
    }

    @PutMapping("/tips/{id}")
    public ResponseEntity<?> put(@RequestBody RecyclingTip newTip, @PathVariable Long id) {

        RecyclingTip _recyclingTip =  repository.findById(id)
                .map(tip -> {
                    tip.setName(newTip.getName());
                    tip.setTip(newTip.getTip());
                    return repository.save(tip);
                })
                .orElseGet(() -> repository.save(newTip));

        EntityModel<?> tipModel = assembler.toModel(_recyclingTip);
        return ResponseEntity.created(tipModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(tipModel);
    }

    @DeleteMapping("/tips/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}