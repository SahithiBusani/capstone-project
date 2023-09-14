package com.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.application.model.Course;
import com.application.repository.CourseRepository;

@Service
public class CourseService 
{
	@Autowired
	private CourseRepository courseRepo;
	
	public Course saveCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public Course addNewCourse(Course course)
	{
		return courseRepo.save(course);
	}
	
	public List<Course> getAllCourses()
	{
		return (List<Course>)courseRepo.findAll();
	}
	
	public void updateEnrolledcount(String coursename, int enrolledcount)
	{
		courseRepo.updateEnrolledcount(enrolledcount, coursename);
	}
	
	public Course fetchCourseByCoursename(String coursename)
	{
		return courseRepo.findByCoursename(coursename);
	}
	
	public Course fetchCourseByCourseid(String courseid)
	{
		return courseRepo.findByCourseid(courseid);
	}
	
	public List<Course> fetchByInstructorname(String instructorname)
	{
		return (List<Course>)courseRepo.findByInstructorname(instructorname);
	}
	
	public List<Course> fetchByInstructorinstitution(String instructorinstitution)
	{
		return (List<Course>)courseRepo.findByInstructorinstitution(instructorinstitution);
	}
	
	public List<Course> fetchByEnrolleddate(String enrolleddate)
	{
		return (List<Course>)courseRepo.findByEnrolleddate(enrolleddate);
	}
	
	public List<Course> fetchByCoursetype(String coursetype)
	{
		return (List<Course>)courseRepo.findByCoursetype(coursetype);
	}
	
	public List<Course> fetchByYoutubeurl(String youtubeurl)
	{
		return (List<Course>)courseRepo.findByYoutubeurl(youtubeurl);
	}
	
	public List<Course> fetchByWebsiteurl(String websiteurl)
	{
		return (List<Course>)courseRepo.findByWebsiteurl(websiteurl);
	}
	
	public List<Course> fetchBySkilllevel(String skilllevel)
	{
		return (List<Course>)courseRepo.findBySkilllevel(skilllevel);
	}
	
	public List<Course> fetchByLanguage(String language)
	{
		return (List<Course>)courseRepo.findByLanguage(language);
	}
	public List<Course> searchCourses(
	        String keyword,
	        Long categoryId,
	        String sortField,
	        String sortOrder) {

	    Specification<Course> specification = Specification.where(null);

	    // Add filtering conditions based on keyword and categoryId
	    if (keyword != null && !keyword.isEmpty()) {
	        specification = specification.and((root, query, builder) ->
	                builder.or(
	                        builder.like(root.get("coursename"), "%" + keyword + "%"),
	                        builder.like(root.get("instructorname"), "%" + keyword + "%")
	                        // Add more fields to search as needed
	                )
	        );
	    }

	    if (categoryId != null) {
	        specification = specification.and((root, query, builder) ->
	                builder.equal(root.get("category").get("id"), categoryId)
	        );
	    }

	    // Add sorting logic
	    Sort courseSort = Sort.unsorted();
	    if (sortField != null && !sortField.isEmpty()) {
	        if ("coursename".equalsIgnoreCase(sortField)) {
	            courseSort = Sort.by("coursename");
	        } else if ("enrolleddate".equalsIgnoreCase(sortField)) {
	            courseSort = Sort.by("enrolleddate");
	        }
	   
	    }

	    // Execute the query with specifications and sorting
	    return courseRepo.findAll(specification, courseSort);
	}
}