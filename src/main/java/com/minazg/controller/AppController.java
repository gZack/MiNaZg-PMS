package com.minazg.controller;

import java.io.*;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.minazg.exception.UserNotFoundException;
import com.minazg.model.UserRole;
import com.minazg.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.minazg.model.User;
import com.minazg.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("roles")
public class AppController {

	@Autowired
	UserRoleService userRoleService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserRole> initializeProfiles(Model model) {

		return userRoleService.findAll();
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", getPrincipal());

		return "user/userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(@ModelAttribute("user") User user, Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user/registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@PostMapping(value = { "/newuser" })
	public String saveUser(@Valid User user, BindingResult result,
						   ModelMap model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "user/registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [sso] should be implementing custom @Unique annotation
		 * and applying it on field [sso] of Model class [User].
		 *
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 *
		 *//*
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
			result.addError(ssoError);
			return "user/registration";
		}*/

		MultipartFile productImage = user.getUserProfPic();

		String uploadLocation = messageSource
				.getMessage("upload.location",null,null);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		File tempFile = new File(rootDirectory+"static\\img\\"+ user.getId() + ".png");
		File permanentFile = new File(uploadLocation + user.getId() + ".png");

		if (productImage!=null && !productImage.isEmpty()) {

			try {

				copyFileUsingFileStreams(productImage.getInputStream(), tempFile);

				productImage.transferTo(permanentFile);

			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		//return "success";
		return "redirect:/list";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		if(user == null){
			throw new UserNotFoundException("Unable to find user with user name: " + ssoId);
		}
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		model.addAttribute("loggedinuser", getPrincipal());
		return "user/edit";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("user") User user, BindingResult result,
							 ModelMap model, @PathVariable String ssoId,
							 HttpServletRequest request) {

		if (result.hasErrors()) {
			return "user/edit";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING SSO_ID in UI which is a unique key to a User.
		if(!userService.isUserSSOUnique(user.getId(), user.getSsoId())){
			FieldError ssoError =new FieldError("user","ssoId",messageSource.getMessage("non.unique.ssoId", new String[]{user.getSsoId()}, Locale.getDefault()));
		    result.addError(ssoError);
			return "registration";
		}*/

		MultipartFile productImage = user.getUserProfPic();

		String uploadLocation = messageSource
				.getMessage("upload.location",null,null);

		String rootDirectory = request.getSession().getServletContext().getRealPath("/");

		File tempFile = new File(rootDirectory+"static\\img\\"+ user.getId() + ".png");
		File permanentFile = new File(uploadLocation + user.getId() + ".png");

		if (productImage!=null && !productImage.isEmpty()) {

			try {

				copyFileUsingFileStreams(productImage.getInputStream(), tempFile);

				productImage.transferTo(permanentFile);

			} catch (Exception e) {
				throw new RuntimeException("Product Image saving failed", e);
			}
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " updated successfully");
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:/list";
	}

	/**
	 * This method will delete an user by it's SSOID value.
	 */
	@RequestMapping(value = { "/delete-user-{ssoId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String ssoId) {
		userService.deleteUserBySSO(ssoId);
		return "redirect:/list";
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (userService.isUserAuthenticated()) {
			return "login";
		} else {
			return "redirect:/list";
		}
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			//persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}



	private static void copyFileUsingFileStreams(InputStream input, File dest)
			throws IOException {
		OutputStream output = null;
		try {
			output = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
		} finally {
			input.close();
			output.close();
		}
	}

}
