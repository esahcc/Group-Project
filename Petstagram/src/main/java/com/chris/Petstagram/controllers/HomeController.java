package com.chris.Petstagram.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.chris.Petstagram.models.Picture;
import com.chris.Petstagram.models.User;
import com.chris.Petstagram.services.PictureService;
import com.chris.Petstagram.services.UserService;
import com.chris.Petstagram.validators.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private UserService uService;
	@Autowired
	private UserValidator validator;
	@Autowired
	private PictureService pService;
	
	private static String UPLOADED_FOLDER = "src/main/resources/static/images/";
	
	@GetMapping("/")
	public String landing(@ModelAttribute("user")User user) {
		return "/user/landing.jsp";
	}
	
	@PostMapping("/registerUser")
	public String register(@Valid @ModelAttribute("user")User user, BindingResult result, HttpSession session) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "/user/landing.jsp";
		}
		User newUser = this.uService.registerUser(user);
		session.setAttribute("user_id", newUser.getId());
		return "redirect:/dashboard";
	}
	
	@PostMapping("/login")
	public String login(HttpSession session, @RequestParam("lemail")String email, @RequestParam("lpassword")String password, RedirectAttributes redirectAttr) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttr.addFlashAttribute("loginError", "Invalid Credentials");
			return "redirect:/";
		}
		User userToBeLoggedIn = this.uService.getUserByEmail(email);
		session.setAttribute("user_id", userToBeLoggedIn.getId());
		return "redirect:/dashboard";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model viewModel, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("user_id");
		User user = this.uService.getOneUser(userId);
		viewModel.addAttribute("currentUser", user);
		viewModel.addAttribute("user", this.uService.getOneUser((Long)session.getAttribute("user_id")));
		viewModel.addAttribute("pictures", this.pService.allPictures());
		return "/user/dashboard.jsp";
	}
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id")Long id, HttpSession session) {
		User userToLikePicture = this.uService.getOneUser((Long)session.getAttribute("user_id"));
		Picture pictureToLike = this.pService.getOnePicture(id);
		this.pService.likePicture(userToLikePicture, pictureToLike);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id")Long id, HttpSession session) {
		User userToLikePicture = this.uService.getOneUser((Long)session.getAttribute("user_id"));
		Picture pictureToUnlike = this.pService.getOnePicture(id);
		this.pService.unlikePicture(userToLikePicture, pictureToUnlike);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/show")
	public String show(Model viewModel, HttpSession session) {
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		Long userId = (Long)session.getAttribute("user_id");
		User user = uService.getOneUser(userId);
		viewModel.addAttribute("currentUser",user);
		viewModel.addAttribute("user", this.uService.getOneUser((Long)session.getAttribute("user_id")));
		return "/picture/showPicture.jsp";
	}
	
	@PostMapping("/upload")
	public String upload(@RequestParam("pic")MultipartFile file, @RequestParam("description")String desc, HttpSession session, RedirectAttributes redirectAttr) {
		if(file.isEmpty()) {
			redirectAttr.addFlashAttribute("message", "Upload field cannot be empty");
			return "redirect:/show";
		}
		try {
			Long userId = (Long)session.getAttribute("user_id");
			User user = uService.getOneUser(userId);
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			String url = "/images/" + file.getOriginalFilename();
			this.pService.uploadPic(user, url, desc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/dashboard";
	}
	
	@GetMapping("/profile/{id}")
	public String profile(@PathVariable("id")Long id, Model viewModel, HttpSession session) {
		User user = uService.getOneUser(id);
		viewModel.addAttribute("user", this.uService.getOneUser(id));
		viewModel.addAttribute("currentUser", user);
		viewModel.addAttribute("loggedInUser", (Long)session.getAttribute("user_id"));
		viewModel.addAttribute("pictureDetails", this.pService.getOnePicture(id));
		return "/user/profile.jsp";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id")Long id) {
		this.pService.deletePicture(id);
		return "redirect:/dashboard";
	}
}
