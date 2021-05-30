package kodlama.io.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlama.io.hrms.business.abstracts.CandidatesService;
import kodlama.io.hrms.core.utilities.results.DataResult;
import kodlama.io.hrms.core.utilities.results.Result;
import kodlama.io.hrms.entities.concretes.Candidates;

@RestController
@RequestMapping("/api/candidates")
public class CandidatesController {

    private CandidatesService candidatesService;

    @Autowired
    public CandidatesController(CandidatesService candidatesService) {
        super();
        this.candidatesService = candidatesService;
    }

    @GetMapping("/getall")
    public DataResult<List<Candidates>> getAll(){
		return this.candidatesService.getAll();
    	
    }

    @PostMapping("/add")
    public Result add(@RequestBody Candidates candidate) {
        return this.candidatesService.add(candidate);
    }

}