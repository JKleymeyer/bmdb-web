package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Credit;
import com.bmdb.db.CreditRepository;

@CrossOrigin
@RestController
@RequestMapping("/credit")
public class CreditController {

	@Autowired
	private CreditRepository creditRepo;

	// List of all credits
	@GetMapping("/")
	public JsonResponse listCredit() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	// Return one credit by Id
	@GetMapping("/{id}")
	public JsonResponse getCredit(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.findById(id));
		} catch (Exception e) {

		}
		return jr;
	}

	// Adding a new credit
	@PostMapping("/")
	public JsonResponse addCredit(@RequestBody Credit c) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(creditRepo.save(c));
		} catch (Exception e) {

		}
		return jr;

	}

	// update an Actor
	@PutMapping("/")
	public JsonResponse updateCredit(@RequestBody Credit c) {
		JsonResponse jr = null;
		try {
			if (creditRepo.existsById(c.getId())) {
				jr = JsonResponse.getInstance(creditRepo.save(c));
			} else {
				// record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " + c.getId() + " doesn't exist");
			}
		} catch (Exception e) {

		}
		return jr;
	}

	// Delete an Actor
	@DeleteMapping("/{id}")
	public JsonResponse deleteCredit(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			if (creditRepo.existsById(id)) {
				creditRepo.deleteById(id);
				jr = JsonResponse.getInstance("Delete successful");
			} else {
				// record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " + id + " doesn't exist");
			}
		} catch (Exception e) {

		}
		return jr;
	}

}
