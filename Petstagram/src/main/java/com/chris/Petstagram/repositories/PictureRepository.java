package com.chris.Petstagram.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.chris.Petstagram.models.Picture;
import com.chris.Petstagram.models.User;

@Repository
public interface PictureRepository extends CrudRepository<Picture,Long> {
	
	List<Picture> findAllByUser(User user);
	List<Picture> findAll();
}
