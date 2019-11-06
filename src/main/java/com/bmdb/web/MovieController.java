package com.bmdb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bmdb.business.Movie;
import com.bmdb.db.MovieRepository;
import com.bmdb.web.JsonResponse;

@CrossOrigin
@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieRepository movieRepo;

	// List of all movies
	@GetMapping("/")
	public JsonResponse listMovies() {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.findAll());

		} catch (Exception e) {
			jr = JsonResponse.getInstance(e);
		}
		return jr;
	}

	// Return one movie by Id
	@GetMapping("/{id}")
	public JsonResponse getMovie(@PathVariable int id) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.findById(id));
		} catch (Exception e) {

		}
		return jr;

	}

	// Adding a new movie
	@PostMapping("/")
	public JsonResponse addMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try {
			jr = JsonResponse.getInstance(movieRepo.save(m));
		} catch (Exception e) {

		}
		return jr;

	}

	//update a movie
	@PutMapping("/")
	public JsonResponse updateMovie(@RequestBody Movie m) {
		JsonResponse jr = null;
		try{
			if (movieRepo.existsById(m.getId())) {
			jr = JsonResponse.getInstance(movieRepo.save(m));
			} else {
				//record doesn't exist
				jr = JsonResponse.getInstance("Error updating Stuffy. Id " +m.getId()+ " doesn't exist");
			}
		} catch (Exception e) {
			
		}
		return jr;
		}
	
	//Delete a movie
	@DeleteMapping("/{id}")
	public JsonResponse deleteMovie(@PathVariable int id) {
		JsonResponse jr = null;
		try{
			if (movieRepo.existsById(id)) {
				movieRepo.deleteById(id);
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
