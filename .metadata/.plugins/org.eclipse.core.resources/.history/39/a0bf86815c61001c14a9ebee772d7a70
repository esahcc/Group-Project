package com.chris.Petstagram.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chris.Petstagram.models.Picture;
import com.chris.Petstagram.models.User;
import com.chris.Petstagram.repositories.PictureRepository;

@Service
public class PictureService {
	@Autowired
	private PictureRepository pRepo;
	
	public void uploadPic(User user, String url, String desc) {
		Picture newPic = new Picture(user, url, desc);
		this.pRepo.save(newPic);
	}
	
	public List<Picture> userPictures(User user){
		return this.pRepo.findAllByUser(user);
	}
	
	public List<Picture> allPictures(){
		return this.pRepo.findAll();
	}
	
	public Picture getOnePicture(Long id) {
		return this.pRepo.findById(id).orElse(null);
	}
	
	public String deletePicture(Long id) {
		this.pRepo.deleteById(id);
		return "Your picture has been deleted";
	}
	
	public void likePicture(User user, Picture picture) {
		List<User> usersWhoLiked = picture.getLikers();
		usersWhoLiked.add(user);
		this.pRepo.save(picture);
	}
	
	public void unlikePicture(User user, Picture picture) {
		List<User> usersWhoLiked = picture.getLikers();
		usersWhoLiked.remove(user);
		this.pRepo.save(picture);
	}
}
