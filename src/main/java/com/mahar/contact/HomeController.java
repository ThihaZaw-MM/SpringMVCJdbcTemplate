package com.mahar.contact;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mahar.contact.dao.ContactDAO;
import com.mahar.contact.dao.UserDAO;
import com.mahar.contact.model.Contact;
import com.mahar.contact.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private UserDAO userDAO;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value="/")
	public ModelAndView listContact(ModelAndView model) throws IOException{
		List<Contact> listContact = contactDAO.list();
		model.addObject("listContact", listContact);
		model.setViewName("home");

		return model;
	}
	
	@RequestMapping(value = "/newContact", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Contact newContact = new Contact();
		List<User> userList = userDAO.list();
		model.addObject("userList", userList);
		model.addObject("contact", newContact);
		model.setViewName("ContactForm");
		return model;
	}
	
	@RequestMapping(value = "/saveContact", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Contact contact) {
		
		contactDAO.saveOrUpdate(contact);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/deleteContact", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		contactDAO.delete(contactId);
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/editContact", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {
		int contactId = Integer.parseInt(request.getParameter("id"));
		Contact contact = contactDAO.get(contactId);
		ModelAndView model = new ModelAndView("ContactForm");
		model.addObject("contact", contact);

		return model;
	}
}
