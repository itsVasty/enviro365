package com.example.enviro365.entity.waste;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.enviro365.controller.guidelines.DisposalGuidelineController;
import com.example.enviro365.controller.waste.WasteCategoryController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class WasteCategoryModelAssembler implements RepresentationModelAssembler<WasteCategory, EntityModel<WasteCategory>> {

    @Override
    public EntityModel<WasteCategory> toModel(WasteCategory wasteCategory) {

        return EntityModel.of(wasteCategory, //
                linkTo(methodOn(WasteCategoryController.class).get(wasteCategory.getId())).withSelfRel(),
                linkTo(methodOn(WasteCategoryController.class).get()).withRel("categories"),
                linkTo(methodOn(DisposalGuidelineController.class).get(wasteCategory.getGuideLineId())).withRel("disposal_guideline"));
    }
}