/**
 * 
 */
package com.embl.people.repo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.embl.people.entity.Person;

/**
 * Repository to provide CRUD operations for Person Entity.
 * It also provides API for paging and sorting.
 * 
 * @author poonam
 *
 */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

	List<Person> findByFirstName(@Param("firstName")String firstName);
	List<Person> findByLastName(@Param("lastName")String lastName);
	List<Person> findByAge(@Param("age")Integer age);
	List<Person> findByFavColour(@Param("favColour")String favColour);
	
}
