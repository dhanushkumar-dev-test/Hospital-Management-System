package com.ty.HospitalManagementSystem.controller;

import com.ty.HospitalManagementSystem.Entity.Branch;
import com.ty.HospitalManagementSystem.service.BranchService;
import com.ty.HospitalManagementSystem.util.ResponseStructure;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/branches")
@Validated
@Tag(name = "Branch Management API", description = "Operations related to Branch entity")
public class BranchController {

    @Autowired
    private BranchService service;

    @PostMapping
    @Operation(summary = "Save Branch", description = "Creates a new branch")
    @ApiResponse(responseCode = "201", description = "Branch saved successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public ResponseEntity<ResponseStructure<Branch>> saveBranch(

            @Parameter(description = "Branch object to be created", required = true)
            @Valid @RequestBody Branch branch,

            @Parameter(description = "Hospital ID", required = true)
            @RequestParam int hid,

            @Parameter(description = "Address ID", required = true)
            @RequestParam int aid) {

        Branch savedBranch = service.saveBranch(hid, aid, branch);

        ResponseStructure<Branch> response = new ResponseStructure<>();
        response.setMessage("Branch saved successfully");
        response.setStatus(HttpStatus.CREATED.value());
        response.setData(savedBranch);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Branch", description = "Updates branch by ID")
    @ApiResponse(responseCode = "200", description = "Branch updated successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<ResponseStructure<Branch>> updateBranch(

            @Parameter(description = "Branch ID", required = true)
            @PathVariable int id,

            @Parameter(description = "Updated branch object", required = true)
            @RequestBody Branch branch) {

        Branch updatedBranch = service.updateBranch(id, branch);

        ResponseStructure<Branch> response = new ResponseStructure<>();
        response.setMessage("Branch updated successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(updatedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Branch", description = "Deletes branch by ID")
    @ApiResponse(responseCode = "200", description = "Branch deleted successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<ResponseStructure<Branch>> deleteBranch(

            @Parameter(description = "Branch ID", required = true)
            @PathVariable int id) {

        Branch deletedBranch = service.deleteBranch(id);

        ResponseStructure<Branch> response = new ResponseStructure<>();
        response.setMessage("Branch deleted successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(deletedBranch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Branch By ID", description = "Fetch branch using ID")
    @ApiResponse(responseCode = "200", description = "Branch fetched successfully")
    @ApiResponse(responseCode = "404", description = "Branch not found")
    public ResponseEntity<ResponseStructure<Branch>> getBranchById(

            @Parameter(description = "Branch ID", required = true)
            @PathVariable int id) {

        Branch branch = service.getbranchbyid(id);

        ResponseStructure<Branch> response = new ResponseStructure<>();
        response.setMessage("Branch fetched successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(branch);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/hospital/{hid}")
    @Operation(summary = "Get Branches By Hospital ID", description = "Fetch all branches belonging to a hospital")
    @ApiResponse(responseCode = "200", description = "Branches fetched successfully")
    @ApiResponse(responseCode = "404", description = "Hospital not found")
    public ResponseEntity<ResponseStructure<List<Branch>>> getBranchByHospitalId(

            @Parameter(description = "Hospital ID", required = true)
            @PathVariable int hid) {

        List<Branch> branches = service.getbranchbyhospitalid(hid);

        ResponseStructure<List<Branch>> response = new ResponseStructure<>();
        response.setMessage("Branches fetched successfully");
        response.setStatus(HttpStatus.OK.value());
        response.setData(branches);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}