package com.foodcourt.msusers.infrastructure.input.rest;

import com.foodcourt.msusers.aplication.dto.request.OwnerRequest;
import com.foodcourt.msusers.aplication.handler.IOwnerHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/owner")
public class OwnerRestController {

    private final IOwnerHandler ownerHandler;

    public OwnerRestController(IOwnerHandler ownerHandler) {
        this.ownerHandler = ownerHandler;
    }

    @PostMapping("/saveOwner")
    public ResponseEntity<Void> addNewOwner (@RequestBody OwnerRequest ownerRequest){
        ownerHandler.saveOwner(ownerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
