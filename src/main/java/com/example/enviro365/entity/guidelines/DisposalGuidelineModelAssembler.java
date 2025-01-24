package com.example.enviro365.entity.guidelines;

import com.example.enviro365.controller.guidelines.DisposalGuidelineController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DisposalGuidelineModelAssembler implements RepresentationModelAssembler<DisposalGuideline, EntityModel<DisposalGuideline>> {

    @Override
    public EntityModel<DisposalGuideline> toModel(DisposalGuideline disposalGuideline) {

        return EntityModel.of(disposalGuideline, //
                linkTo(methodOn(DisposalGuidelineController.class).get(disposalGuideline.getId())).withSelfRel(),
                linkTo(methodOn(DisposalGuidelineController.class).get()).withRel("guidelines"));
    }
}