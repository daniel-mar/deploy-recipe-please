package com.hackbright.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hackbright.models.Recipe;
import com.hackbright.models.LoginUser;
import com.hackbright.models.User;
import com.hackbright.services.RecipeService;
import com.hackbright.services.UserService;

@Controller
public class HomeController {

		// Injecting out UserService
		
		private final UserService userServ;
		private final RecipeService recipeServ;
		
		//pass them in as dependency
		public HomeController( UserService userServ, RecipeService recipeServ) {
			this.userServ = userServ;
			this.recipeServ = recipeServ;
		}
	
		
	//=======================================================================
	//Render Login / Register Page
	//=======================================================================
	
	@GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
	
	//=======================================================================
	//Process Register Route
	//=======================================================================
	
	@PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        
	 	userServ.register(newUser, result);
	 	
        if(result.hasErrors()) {
            // Be sure to send in the empty LoginUser before 
            // re-rendering the page.
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", newUser.getId());
        return "redirect:/dashboard";
    
    }
	
	
	//=======================================================================
	//Process Login Route
	//=======================================================================
	@PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    	User user = userServ.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
    
        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("user_id", user.getId());
        return "redirect:/dashboard";
    }
    
	
	//=======================================================================
	//Logout Route
	//=======================================================================
		
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); //is clearing your session out
		return "redirect:/";
	}
	
	
		//=======================================================================
		//Render Dashboard Route
		//=======================================================================
		
		@GetMapping("/dashboard")
		public String dashboard(HttpSession session, Model model,
				RedirectAttributes flash) {
			Long userId = (Long) session.getAttribute("user_id");
			if(userId == null) {
				// below flash, pass in the index.jsp file
				flash.addFlashAttribute("login","Please login or register before entering the site!");
				return "redirect:/";}
			
			// go back to userServices and look for getUserInfo method, pull info out and pass to front end.
			
			User user = userServ.getUserInfo(userId); 
			// #1 Now Controller has thing to call on, which is geteUserInfo()
			
			//Go to userService. Come back here from the Resp -> Service -> Controller...the user in User is fully formed instance from DB. 
			// getUserInfo() was created in User Services under find One user by ID.
			
			model.addAttribute("loggedUser", user);
			// loggedUser to show on HTML, and user is what we just queried for.
			
			// can also be done as model.addAttribute("loggedUser", userServ.getUserInfo(userId)
			
			//show all Recipe to JSP
			List<Recipe> recipes= recipeServ.getAllRecipes();
			model.addAttribute("recipes", recipes);
			
			return "dashboard.jsp";
		}
		
		//=======================================================================
		// Create a new Recipe Route
		//=======================================================================
	   @GetMapping("/newRecipe")
	   public String newRecipe(HttpSession session, RedirectAttributes flash, Model model ) {
		   Long userId= (Long) session.getAttribute("user_id");
		   
		   // below prevent unauthorized Adding of a new Recipe page before log in or register
			if(userId == null) {
				// below we add flash, pass in the newRecipe.jsp file
				flash.addFlashAttribute("login","Please login or register prior to creating a recipe");
				return "redirect:/";
	   }
			
			model.addAttribute("userId",userId); // userId that we pulled out from session
			model.addAttribute("recipe", new Recipe()); // new Recipe() is a new instance of a recipe
			
			return "newRecipe.jsp";
	}

	   
	   @PostMapping("/createRecipe")
	   public String createRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, 
	           BindingResult result, HttpSession session, Model model) {
		   if(result.hasErrors()) {
			   Long userId = (Long) session.getAttribute("user_id");
			   model.addAttribute("userId", userId);
			   
			   return "newRecipe.jsp";
		   }else {
			   recipeServ.createRecipe(recipe);
			   return "redirect:/dashboard";
		   }
		
	   }
	   
	   
		//=======================================================================
		//Show One Recipe - Route
		//=======================================================================
	   
	   @GetMapping("/oneRecipe/{id}")
	   public String showOne(@PathVariable("id")Long recipeId, 
			   HttpSession session,
			   Model model,
			   RedirectAttributes flash) {
		   
		   Long userId= (Long) session.getAttribute("user_id");
			if(userId == null) {
				
				flash.addFlashAttribute("login","Please login or register before entering the site!");
				return "redirect:/";} 
		   
		   Recipe recipe = recipeServ.findOneRecipe(recipeId);
		   model.addAttribute("recipe", recipe);
		   
		   
		   return "oneRecipe.jsp";
	   }
	   
	   
		 //=======================================================================
		 //Edit Routes
		 //=======================================================================
	   
		@GetMapping("/editRecipe/{id}")
	 	public String editRecipe(@PathVariable("id")Long recipeId, 
			   HttpSession session,
			   Model model,
			   RedirectAttributes flash) {
		   
		   Long userId= (Long) session.getAttribute("user_id");
			if(userId == null) {
				
				flash.addFlashAttribute("login","Please login or register before editing a recipe");
				return "redirect:/";} 
		   
		   Recipe recipe = recipeServ.findOneRecipe(recipeId);
		   model.addAttribute("recipe", recipe);
		   
		   return "editRecipe.jsp";
	 }
		@PutMapping("updateRecipe/{id}")
		public String updateRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult result) {
			   if(result.hasErrors()) {
				   return "editRecipe.jsp";
			   }else {
				   recipeServ.updateRecipe(recipe);
				   return "redirect:/dashboard";
			   }

		}
		
		
		 //=======================================================================
		 //Delete Route
		 //=======================================================================
		 //	Note: @DeleteMapping won't work without the form:form, we can have same effect with this route!
		
		@GetMapping("/delete/{id}")
		public String deleteRecipe(@PathVariable("id") Long recipeId) {
			recipeServ.deleteRecipe(recipeId);
			return "redirect:/dashboard";
		}
		
	}

	
	