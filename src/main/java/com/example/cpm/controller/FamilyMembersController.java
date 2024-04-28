package com.example.cpm.controller;

import com.example.cpm.exception.InvalidInputException;
import com.example.cpm.exception.ResourceNotFoundException;
import com.example.cpm.model.FamilyMembers;
import com.example.cpm.service.FamilyMembersService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/familyMembers/")
public class FamilyMembersController {

    private final FamilyMembersService familyMembersService;

    @GetMapping
    public List<FamilyMembers> getAllFamilyMembers(){
        return familyMembersService.getAllFamilyMembers();
    }

    @GetMapping("{id}")
    public Optional<FamilyMembers> getFamilyMembersById(@PathVariable Long id) {
        return familyMembersService.findFamilyMembersById(id);
    }

    @PostMapping
    public FamilyMembers createFamilyMembers(@RequestBody FamilyMembers familyMembers) {

        if (StringUtils.isBlank(familyMembers.getFirstName())){
            throw new InvalidInputException("FamilyMembers first name from Request Body is null!");
        }
        if (StringUtils.isBlank(familyMembers.getLastName())){
            throw new InvalidInputException("FamilyMembers last name from Request Body is null!");
        }
        if (familyMembers.getCustomer() == null){
            throw new InvalidInputException("FamilyMembers customer email from Request Body is null!");
        }
        FamilyMembers createdFamilyMembers = familyMembersService.saveFamilyMembers(familyMembers);

        return createdFamilyMembers;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFamilyMembers(@PathVariable Long id) {
        familyMembersService.deleteFamilyMembers(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping
    public FamilyMembers updateFamilyMembers(@RequestBody FamilyMembers familyMembers) {

        Optional<FamilyMembers> optionalFamilyMembers = familyMembersService.findFamilyMembersById(familyMembers.getId());

        if (optionalFamilyMembers.isPresent()) {
            FamilyMembers updatedFamilyMembers = familyMembersService.updateFamilyMembers(familyMembers);
            return updatedFamilyMembers;
        } else {
            throw new ResourceNotFoundException(String.format("FamilyMembers with id %o not found", familyMembers.getId()));
        }
    }
}
