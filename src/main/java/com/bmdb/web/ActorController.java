package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Actor;
import com.bmdb.db.ActorRepository;

@CrossOrigin
@RestController
@RequestMapping("/actor")
public class ActorController {

	@Autowired
	private ActorRepository actorRepo;

	// List of all actors
	@GetMapping("/")
	public JsonResponse listActors() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	// Return one actor by Id
	@GetMapping("/{id}")
	public JsonResponse getActor(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.findById(id));
		} catch (Exception e) {

		}
		return jr;
	}

	// Adding a new Actor
	@PostMapping("/")
	public JsonResponse addActor(@RequestBody Actor a) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(actorRepo.save(a));
		} catch (Exception e) {

		}
		return jr;

	}

	// update an Actor
	@PutMapping("/")
	public JsonResponse updateActor(@RequestBody Actor a) {
		JsonResponse jr = null;
		try {
			if (actorRepo.existsById(a.getId())) {
				jr = JsonResponse.getInstance(actorRepo.save(a));
			} else {
				// record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " + a.getId() + " doesn't exist");
			}
		} catch (Exception e) {

		}
		return jr;
	}

	//Delete an Actor
	@DeleteMapping("/{id}")
	public JsonResponse deleteActor(@PathVariable int id) {
		JsonResponse jr = null;
		try{
			if (actorRepo.existsById(id)) {
				actorRepo.deleteById(id);
			jr = JsonResponse.getInstance("Delete successful");
			} else {
				//record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " +id+ " doesn't exist");
			}
		} catch (Exception e) {
			
		}
		return jr;
		}
	
}
