package com.example.enviro365.entity.tips;

import com.example.enviro365.controller.tips.RecyclingTipController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecyclingTipModelAssembler implements RepresentationModelAssembler<RecyclingTip, EntityModel<RecyclingTip>> {

    @Override
    public EntityModel<RecyclingTip> toModel(RecyclingTip recyclingTip) {

        return EntityModel.of(recyclingTip, //
                linkTo(methodOn(RecyclingTipController.class).get(recyclingTip.getId())).withSelfRel(),
                linkTo(methodOn(RecyclingTipController.class).get()).withRel("tips"));
    }
}